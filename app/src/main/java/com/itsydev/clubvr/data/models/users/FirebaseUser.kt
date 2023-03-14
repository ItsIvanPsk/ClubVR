package com.itsydev.clubvr.data.models.users

data class FirebaseUser(
    val id: Int
)

data class UserBo(
    val id: Int,
    val username: String,
    val name: String,
    val surname: String,
    val mail: String,
    val telf: String,
    val len: String,
    val userLevel: Int,
    val userPoints: Int,
    val admin: Boolean
)

data class UserDto(
    val id: String,
    val username: String,
    val name: String,
    val surname: String,
    val mail: String,
    val telf: String,
    val len: String,
    val userLevel: String,
    val userPoints: String,
    val admin: String
)
