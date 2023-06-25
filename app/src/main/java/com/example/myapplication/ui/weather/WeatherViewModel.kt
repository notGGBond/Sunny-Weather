package com.example.myapplication.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.example.myapplication.logic.Repository
import com.example.myapplication.logic.model.Location

class WeatherViewModel : ViewModel() {

    private val locationLiveData = MutableLiveData<Location>()

    var locationLng = ""

    var locationLat = ""

    var placeName = ""

    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->
        Repository.refreshWeather(location.lng, location.lat, placeName)
    }

    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }

}