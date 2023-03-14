package com.itsydev.clubvr.data.models.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = false) @SerializedName("id") val id: String = "",
    @SerializedName("username") @ColumnInfo var username: String = "",
    @SerializedName("name") @ColumnInfo var name: String = "",
    @SerializedName("surname") @ColumnInfo var surname: String = "",
    @SerializedName("mail") @ColumnInfo var mail: String = "",
    @SerializedName("telf") @ColumnInfo var telf: String = "",
    @SerializedName("len") @ColumnInfo var lenguage: String = "",
    @SerializedName("userLevel") @ColumnInfo var userLevel: String = "",
    @SerializedName("userPoints") @ColumnInfo var userPoints: String = "",
    @SerializedName("admin") @ColumnInfo var admin: String = "",
)