package com.example.networkingplay.repositoryWithParams

import com.example.networkingplay.proxy.Change
import com.example.networkingplay.repositorySimple.ApiInterface
import com.example.networkingplay.proxy.QuoteList
import com.example.networkingplay.util.GsonCreatorHelper
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface2 {

    //simple
    @GET("/quotes")
    fun getQuotes() : Call<QuoteList>

    //replacement blocks
    @GET("/{quotes}")
    fun getQuotes2(@Path("quotes") name:String ) : Call<QuoteList>

    //query parameters
    @GET
    fun getQuotes3(@Query("/quotes") name:String ) : Call<QuoteList>

    companion object{
        var BASE_URL = "https://quotable.io/"

        fun create() : ApiInterface2 {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonCreatorHelper.gsonConverterFactory)
                .baseUrl(ApiInterface.BASE_URL)
                .build()
            return retrofit.create(ApiInterface2::class.java)
        }
    }
}

interface GerritApi {

    @GET("changes/")
    fun loadChanges(@Query("q") status:String) : Call<List<Change>>

    @GET("changes/")
    fun loadChanges2(@Query("q") status:String) : Call<List<Change>>

    companion object {
        var BASE_URL = "https://git.eclipse.org/r/"

        fun create() : GerritApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonCreatorHelper.gsonConverterFactory)
                .baseUrl(ApiInterface.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(GerritApi::class.java)
        }
    }
}