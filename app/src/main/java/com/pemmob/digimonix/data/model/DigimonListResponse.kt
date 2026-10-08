package com.pemmob.digimonix.data.model

import com.google.gson.annotations.SerializedName

data class DigimonItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("href")
    val href: String? = null,
    @SerializedName("image")
    val image: String? = null
)

data class Pageable(
    @SerializedName("currentPage")
    val currentPage: Int? = 0,
    @SerializedName("elementsOnPage")
    val elementsOnPage: Int? = 0,
    @SerializedName("totalElements")
    val totalElements: Int? = 0,
    @SerializedName("totalPages")
    val totalPages: Int? = 0,
    @SerializedName("previousPage")
    val previousPage: String? = null,
    @SerializedName("nextPage")
    val nextPage: String? = null
)

data class DigimonListResponse(
    @SerializedName("content")
    val content: List<DigimonItem> = emptyList(),
    @SerializedName("pageable")
    val pageable: Pageable? = null
)
