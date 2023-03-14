package com.itsydev.clubvr.utils

import com.itsydev.clubvr.data.models.users.UserBo
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

fun UserBo.toDto() = UserDto(
    id = id.toString(),
    username = username,
    name = name,
    surname = surname,
    mail = mail,
    telf = telf,
    len = len,
    userLevel = userLevel.toString(),
    userPoints = userPoints.toString(),
    admin = admin.toString()
)