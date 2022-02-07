package com.example.willywonka.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConf {
    private val url = "https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/"
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

        fun getApiService(): APIServiceInterface {

            return getRetrofit().create(APIServiceInterface::class.java)
        }

}