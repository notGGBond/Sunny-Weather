package com.example.myapplication.logic

import androidx.lifecycle.liveData
import com.example.myapplication.logic.model.Place
import com.example.myapplication.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers

object Repository {

    fun searchPlaces(query:String)= liveData(Dispatchers.IO) {
        val result=try {
            val placeResponse=SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status=="ok")
            {
                val places=placeResponse.places
                Result.success(places)
            }
            else
            {
                Result.failure(RuntimeException("response status is${placeResponse.status}"))

            }

        }catch (e:java.lang.Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}