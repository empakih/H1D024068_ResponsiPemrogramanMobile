package com.pemmob.digimonix.data.model

import com.google.gson.annotations.SerializedName

data class DigimonDetail(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("xAntibody")
    val xAntibody: Boolean? = false,
    @SerializedName("images")
    val images: List<DigimonImage> = emptyList(),
    @SerializedName("levels")
    val levels: List<DigimonLevel> = emptyList(),
    @SerializedName("types")
    val types: List<DigimonType> = emptyList(),
    @SerializedName("attributes")
    val attributes: List<DigimonAttribute> = emptyList(),
    @SerializedName("fields")
    val fields: List<DigimonField> = emptyList(),
    @SerializedName("releaseDate")
    val releaseDate: String? = null,
    @SerializedName("descriptions")
    val descriptions: List<DigimonDescription> = emptyList(),
    @SerializedName("skills")
    val skills: List<DigimonSkill> = emptyList()
) {
    val primaryImageUrl: String?
        get() = images.firstOrNull()?.href

    val primaryLevel: String
        get() = levels.firstOrNull()?.level ?: "Unknown Level"

    val primaryAttribute: String
        get() = attributes.firstOrNull()?.attribute ?: "Unknown Attribute"

    val primaryType: String
        get() = types.firstOrNull()?.type ?: "Unknown Type"

    val englishDescription: String?
        get() = descriptions.firstOrNull { it.language?.equals("en_us", ignoreCase = true) == true }?.description
            ?: descriptions.firstOrNull()?.description
}

data class DigimonImage(
    @SerializedName("href")
    val href: String? = null,
    @SerializedName("transparent")
    val transparent: Boolean? = false
)

data class DigimonLevel(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("level")
    val level: String? = null
)

data class DigimonType(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("type")
    val type: String? = null
)

data class DigimonAttribute(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("attribute")
    val attribute: String? = null
)

data class DigimonField(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("field")
    val field: String? = null,
    @SerializedName("image")
    val image: String? = null
)

data class DigimonDescription(
    @SerializedName("origin")
    val origin: String? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("description")
    val description: String? = null
)

data class DigimonSkill(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("skill")
    val skill: String? = null,
    @SerializedName("translation")
    val translation: String? = null,
    @SerializedName("description")
    val description: String? = null
)
