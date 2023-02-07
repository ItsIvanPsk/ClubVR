package com.itsydev.clubvr

import com.google.firebase.firestore.auth.User

fun UserEntity.toDto() = UserDto(
    id = id,
    username = username,
    name = name,
    surname = surname,
    mail = mail,
    telf = telf,
    len = lenguage,
    userLevel = userLevel,
    userPoints = userPoints
)