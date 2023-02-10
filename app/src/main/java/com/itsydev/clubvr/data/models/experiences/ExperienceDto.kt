package com.itsydev.clubvr.data.models.experiences

import com.itsydev.clubvr.data.models.experiences.Category
import com.itsydev.clubvr.data.models.experiences.CompatibleHeadset
import com.itsydev.clubvr.data.models.experiences.ExperienceImages
import com.itsydev.clubvr.data.models.experiences.Warning

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