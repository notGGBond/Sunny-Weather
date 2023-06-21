package com.example.myapplication.logic.network

import com.example.myapplication.SunnyWeatherApplication
import com.example.myapplication.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query:String):Call<PlaceResponse>

}

