package com.itsydev.clubvr

data class UserBo(
    val id: Int,
    val username: String,
    val name: String,
    val surname: String,
    val mail: String,
    val telf: String,
    val len: String,
    val userLevel: Int,
    val userPoints: Int
)
