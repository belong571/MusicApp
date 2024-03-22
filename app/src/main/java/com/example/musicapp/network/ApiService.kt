package com.example.musicapp.network

import com.example.musicapp.bean.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/search")
    suspend fun getSongList(@Query("term") term:String, @Query("limit") limit:String, @Query("country") country:String): Response
}