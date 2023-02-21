package com.itsydev.clubvr

import com.itsydev.clubvr.data.models.users.UserDto
import com.itsydev.clubvr.data.models.users.UserEntity

fun UserEntity.toDto() = UserDto(
    id = id,
    username = username,
    name = name,
    surname = surname,
    mail = mail,
    telf = telf,
    len = lenguage,
    userLevel = userLevel,
    userPoints = userPoints,
    admin = admin
)