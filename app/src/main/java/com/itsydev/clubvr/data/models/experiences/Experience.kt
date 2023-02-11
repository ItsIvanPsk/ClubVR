package com.itsydev.clubvr.data.models.experiences

data class Experience(
    val id: Int,
)

data class ExperienceDto(
    val id: Int,
    val name: String,
    val description: List<String>,
    val categories: List<Category>,
    val img: List<ExperienceImages>,
    val rating: Double,
    val warnings: List<Warning>,
    val headsets_compatible: List<CompatibleHeadset>
)

data class ExperienceBo(
    val id: Int,
    val name: String,
    val description: List<String>,
    val categories: List<Category>,
    val img: List<ExperienceImages>,
    val rating: Double,
    val warnings: List<Warning>,
    val headsets_compatible: List<CompatibleHeadset>
)

data class Category(
    val id: Int
)

data class Warning(
    val id: Int
)

data class CompatibleHeadset(
    val id: Int
)

data class ExperienceImages (
    var id: Int,
    var url: String
)