package com.example.networkingplay

import com.example.networkingplay.util.GsonCreatorHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

fun createOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(60L, TimeUnit.SECONDS)
    .readTimeout(60L, TimeUnit.SECONDS)
    .addInterceptor(
        HttpLoggingInterceptor()
            .apply {
                level =
                    if (true) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }
    )
    .build()

@Suppress("unused")
inline fun <reified T> createWebService(okHttpClient: OkHttpClient, baseUrl: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonCreatorHelper.gsonConverterFactory)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}