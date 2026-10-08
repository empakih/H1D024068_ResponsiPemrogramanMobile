package com.pemmob.digimonix.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemmob.digimonix.data.model.DigimonItem
import com.pemmob.digimonix.data.model.UiState
import com.pemmob.digimonix.data.repository.DigimonRepository
import com.pemmob.digimonix.data.repository.DigimonRepositoryImpl
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: DigimonRepository = DigimonRepositoryImpl()
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<DigimonItem>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<DigimonItem>>> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore: StateFlow<Boolean> = _isLoadingMore.asStateFlow()

    private var searchJob: Job? = null
    
    // Pagination state
    private var currentPage = 0
    private var isLastPage = false
    private val currentList = mutableListOf<DigimonItem>()
    private val pageSize = 20

    init {
        loadInitialDigimonList()
    }

    fun loadInitialDigimonList() {
        if (_searchQuery.value.isNotBlank()) return
        
        viewModelScope.launch {
            currentPage = 0
            isLastPage = false
            currentList.clear()
            _uiState.value = UiState.Loading
            
            repository.getDigimonList(page = currentPage, pageSize = pageSize)
                .onSuccess { response ->
                    currentList.addAll(response)
                    _uiState.value = UiState.Success(currentList.toList())
                    // Jika data kurang dari pageSize, anggap sudah page terakhir (simplifikasi)
                    if (response.size < pageSize) isLastPage = true
                }
                .onFailure { throwable ->
                    _uiState.value = UiState.Error(
                        throwable.localizedMessage ?: "Gagal memuat data Digimon. Silakan coba lagi."
                    )
                }
        }
    }

    fun loadMore() {
        if (isLastPage || _isLoadingMore.value || _searchQuery.value.isNotBlank()) return

        viewModelScope.launch {
            _isLoadingMore.value = true
            currentPage++
            repository.getDigimonList(page = currentPage, pageSize = pageSize)
                .onSuccess { response ->
                    if (response.isEmpty()) {
                        isLastPage = true
                    } else {
                        currentList.addAll(response)
                        _uiState.value = UiState.Success(currentList.toList())
                        if (response.size < pageSize) isLastPage = true
                    }
                    _isLoadingMore.value = false
                }
                .onFailure {
                    // Revert page jika gagal
                    currentPage--
                    _isLoadingMore.value = false
                }
        }
    }

    fun onSearchQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
        searchJob?.cancel()

        if (newQuery.isBlank()) {
            _uiState.value = UiState.Success(currentList.toList())
            return
        }

        searchJob = viewModelScope.launch {
            delay(500) // Debounce search
            _uiState.value = UiState.Loading
            repository.searchDigimon(newQuery.trim())
                .onSuccess { searchResults ->
                    _uiState.value = UiState.Success(searchResults)
                }
                .onFailure { throwable ->
                    _uiState.value = UiState.Error(
                        throwable.localizedMessage ?: "Digimon dengan nama '$newQuery' tidak ditemukan."
                    )
                }
        }
    }

    fun clearSearch() {
        _searchQuery.value = ""
        searchJob?.cancel()
        if (currentList.isEmpty()) {
             loadInitialDigimonList()
        } else {
             _uiState.value = UiState.Success(currentList.toList())
        }
    }

    fun retry() {
        if (_searchQuery.value.isNotBlank()) {
            onSearchQueryChanged(_searchQuery.value)
        } else {
            loadInitialDigimonList()
        }
    }
}
