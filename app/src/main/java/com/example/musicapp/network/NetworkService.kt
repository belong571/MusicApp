package com.example.musicapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkService {

    private val retrofit = Retrofit.Builder()
        .client(
            OkHttpClient.Builder()
                .callTimeout(5, TimeUnit.SECONDS)
                .build()
        )
        .baseUrl("https://itunes.apple.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(ApiService::class.java)

}
