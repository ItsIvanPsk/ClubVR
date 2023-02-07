package com.itsydev.clubvr.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = false)  @ColumnInfo val id: String = "",
    @ColumnInfo val username: String,
    @ColumnInfo val name: String,
    @ColumnInfo val surname: String,
    @ColumnInfo val mail: String,
    @ColumnInfo val telf: String,
    @ColumnInfo val len: String,
    @ColumnInfo val userLevel: String,
    @ColumnInfo val userPoints: String
)