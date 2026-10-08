package com.pemmob.digimonix.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemmob.digimonix.data.model.DigimonDetail
import com.pemmob.digimonix.data.model.UiState
import com.pemmob.digimonix.data.repository.DigimonRepository
import com.pemmob.digimonix.data.repository.DigimonRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: DigimonRepository = DigimonRepositoryImpl()
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<DigimonDetail>>(UiState.Loading)
    val uiState: StateFlow<UiState<DigimonDetail>> = _uiState.asStateFlow()

    private var currentDigimonId: Int? = null

    fun loadDigimonDetail(id: Int) {
        currentDigimonId = id
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getDigimonDetail(id)
                .onSuccess { detail ->
                    _uiState.value = UiState.Success(detail)
                }
                .onFailure { throwable ->
                    _uiState.value = UiState.Error(
                        throwable.localizedMessage ?: "Gagal memuat detail Digimon. Periksa koneksi internet Anda."
                    )
                }
        }
    }

    fun retry() {
        currentDigimonId?.let { loadDigimonDetail(it) }
    }
}
