package com.itsydev.clubvr.data.models.news

data class NewsBo(
    val id: Int,
    val title: List<String>,
    val subtitle: List<String>,
    val img: List<String>,
    val description: List<String>,
)