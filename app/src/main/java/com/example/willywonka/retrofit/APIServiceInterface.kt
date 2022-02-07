package com.example.willywonka.retrofit

import com.example.willywonka.gson.OompaLoompaWorkers
import com.example.willywonka.gson.Worker
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET;
import retrofit2.http.Path
import retrofit2.http.Query

interface APIServiceInterface {
    @GET("oompa-loompas")
    fun getWorkers() : Call<OompaLoompaWorkers>

    @GET("oompa-loompas/{id}")
    fun getWorker(@Path("id") workerId : Int) : Call<Worker>
}