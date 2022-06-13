package com.example.networkingplay.repositorySimple

import com.example.networkingplay.proxy.Movie
import com.example.networkingplay.proxy.QuoteList
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("volley_array.json")
    fun getMovies(): Call<List<Movie>>

    @GET("/quotes")
    fun getQuotes2() : Call<QuoteList>

    companion object {
        //var BASE_URL = "https://velmm.com/apis/"
        var BASE_URL = "https://quotable.io/"

        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}