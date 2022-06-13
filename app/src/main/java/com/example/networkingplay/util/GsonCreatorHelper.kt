package com.example.networkingplay.util

import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory

object GsonCreatorHelper {

    val gsonConverterFactory: GsonConverterFactory = GsonConverterFactory.create(
        GsonBuilder()
        .setLenient()
        .enableComplexMapKeySerialization()
        .create())
}