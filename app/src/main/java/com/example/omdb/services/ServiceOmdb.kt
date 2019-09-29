package com.example.omdb.services

import com.example.omdb.models.ResponseEpisodes
import com.example.omdb.models.Serie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceOmdb {

    @GET("default.aspx")
    fun searchSerie(@Query("t") t: String, @Query("apikey") apikey: String): Call<Serie>

    @GET("default.aspx")
    fun searchSeason(@Query("t") t: String, @Query("Season") season: String, @Query("apikey") apikey: String): Call<ResponseEpisodes>

}