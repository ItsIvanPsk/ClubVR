package com.itsydev.clubvr.utils

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