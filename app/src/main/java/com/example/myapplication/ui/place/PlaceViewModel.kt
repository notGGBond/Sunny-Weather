package com.example.myapplication.ui.place

import androidx.lifecycle.*
import com.example.myapplication.logic.Repository
import com.example.myapplication.logic.dao.PlaceDao

import com.example.myapplication.logic.model.Place

class PlaceViewModel : ViewModel() {


    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData =Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }
    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavedPlace() = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()


}





