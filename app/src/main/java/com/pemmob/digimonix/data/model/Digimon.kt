package com.pemmob.digimonix.data.model

// Keep unified representation if needed across components
data class Digimon(
    val id: Int,
    val name: String,
    val image: String? = null,
    val levels: List<DigimonLevel> = emptyList(),
    val types: List<DigimonType> = emptyList(),
    val attributes: List<DigimonAttribute> = emptyList()
)