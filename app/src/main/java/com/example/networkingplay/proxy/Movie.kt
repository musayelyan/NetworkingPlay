package com.example.networkingplay.proxy


import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("title")
    val title: String?
)