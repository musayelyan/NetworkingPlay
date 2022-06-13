package com.example.networkingplay.repositoryWithCoroutines

import com.example.networkingplay.createOkHttpClient
import com.example.networkingplay.proxy.QuoteList
import com.example.networkingplay.repositorySimple.ApiInterface
import com.example.networkingplay.util.GsonCreatorHelper
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.GET

interface ApiInterface3 {

    @GET("/quotes")
    suspend fun getQuotes(): Response<QuoteList>

    companion object {
        var BASE_URL = "https://git.eclipse.org/r/"

        fun create(): ApiInterface3 {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonCreatorHelper.gsonConverterFactory)
                .baseUrl(ApiInterface.BASE_URL)
                .client(createOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(ApiInterface3::class.java)
        }
    }
}