package com.pemmob.digimonix.data.network

import com.pemmob.digimonix.data.model.DigimonDetail
import com.pemmob.digimonix.data.model.DigimonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DigiApiService {

    @GET("digimon")
    suspend fun getDigimonList(
        @Query("page") page: Int = 0,
        @Query("pageSize") pageSize: Int = 50
    ): DigimonListResponse

    @GET("digimon")
    suspend fun searchDigimon(
        @Query("name") name: String,
        @Query("page") page: Int = 0,
        @Query("pageSize") pageSize: Int = 50
    ): DigimonListResponse

    @GET("digimon/{id}")
    suspend fun getDigimonDetail(
        @Path("id") id: Int
    ): DigimonDetail
}
