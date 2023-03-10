package com.itsydev.clubvr.data.models.headsets

data class Headset(
    private val id: Int
)

data class HeadsetBo(
    val id: Int,
    val name: String,
    val img: List<HeadsetImages>
)

data class HeadsetImages(
    val id: Int,
    val url: String,
)
