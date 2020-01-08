package com.valuelab.photoapp.model

import com.google.gson.annotations.SerializedName

data class UserAlbums(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("userId") val userId: Int
)