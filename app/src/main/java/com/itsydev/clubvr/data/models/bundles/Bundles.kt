package com.itsydev.clubvr.data.models.bundles

data class Bundles(
    val id: Int
)

data class BundlesBo(
    val id: Int,
    val type: Int,
    val userLevel: Int,
    val name: List<String>,
    val description: List<String>,
)
