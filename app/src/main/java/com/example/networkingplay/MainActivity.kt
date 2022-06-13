package com.example.networkingplay

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.networkingplay.databinding.ActivityMainBinding
import com.example.networkingplay.proxy.Change
import com.example.networkingplay.proxy.QuoteList
import com.example.networkingplay.repositorySimple.ApiInterface
import com.example.networkingplay.repositoryWithCoroutines.ApiInterface3
import com.example.networkingplay.repositoryWithParams.ApiInterface2
import com.example.networkingplay.repositoryWithParams.GerritApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val retrofitInstance by lazy { ApiInterface2.create() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.doRequestButton.setOnClickListener {
           // doRetrofitParameterized()
           // doRetrofitGerritApi()
            doRetrofitWithCoroutines()
        }
    }

    private fun doRetrofitGerritApi(){
        val gerritApi = GerritApi.create()
        gerritApi.loadChanges("open").enqueue(object:Callback<List<Change>>{
            override fun onResponse(call: Call<List<Change>>, response: Response<List<Change>>) {
                Log.d("Art", "success:")
                Log.d("Art", "${gerritApi.loadChanges("open").request()}")
            }

            override fun onFailure(call: Call<List<Change>>, t: Throwable) {
                Log.d("Art", "failure:")
            }

        })
    }

    private fun doRetrofitParameterized() {
        retrofitInstance.getQuotes2("quotes").enqueue(object : Callback<QuoteList> {
            override fun onResponse(call: Call<QuoteList>, response: Response<QuoteList>) {
                Log.d("Art", "success: ${response.body()?.results?.size}")
                Log.d("Art", "${retrofitInstance.getQuotes2("quotes").request()}")
            }

            override fun onFailure(call: Call<QuoteList>, t: Throwable) {
                Log.d("Art", "failure $t")
            }

        })
    }

    private fun doRetrofitSimple() {
        val retrofitInstance = ApiInterface.create()
        retrofitInstance.getQuotes2().enqueue(object : Callback<QuoteList> {
            override fun onResponse(
                call: Call<QuoteList>,
                response: Response<QuoteList>
            ) {
                Log.d("Art", "success: ${response.body()?.results?.size}")
            }

            override fun onFailure(call: Call<QuoteList>, t: Throwable) {
                Log.d("Art", "failure $t")

            }

        })
    }


    //with coroutines example
    private fun doRetrofitWithCoroutines() {
        val quotesApi = ApiInterface3.create()

        GlobalScope.launch {
            val result = quotesApi.getQuotes()
            if (result != null) {
                Log.d("Art", "success")
            }
        }

    }

}