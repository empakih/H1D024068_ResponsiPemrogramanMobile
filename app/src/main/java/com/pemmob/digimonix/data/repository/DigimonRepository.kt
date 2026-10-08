package com.pemmob.digimonix.data.repository

import com.pemmob.digimonix.data.model.DigimonDetail
import com.pemmob.digimonix.data.model.DigimonItem
import com.pemmob.digimonix.data.network.ApiClient
import com.pemmob.digimonix.data.network.DigiApiService

interface DigimonRepository {
    suspend fun getDigimonList(page: Int = 0, pageSize: Int = 50): Result<List<DigimonItem>>
    suspend fun searchDigimon(name: String): Result<List<DigimonItem>>
    suspend fun getDigimonDetail(id: Int): Result<DigimonDetail>
}

class DigimonRepositoryImpl(
    private val apiService: DigiApiService = ApiClient.apiService
) : DigimonRepository {

    override suspend fun getDigimonList(page: Int, pageSize: Int): Result<List<DigimonItem>> {
        return runCatching {
            val response = apiService.getDigimonList(page = page, pageSize = pageSize)
            response.content
        }
    }

    override suspend fun searchDigimon(name: String): Result<List<DigimonItem>> {
        return runCatching {
            val response = apiService.searchDigimon(name = name)
            response.content
        }
    }

    override suspend fun getDigimonDetail(id: Int): Result<DigimonDetail> {
        return runCatching {
            apiService.getDigimonDetail(id = id)
        }
    }
}
