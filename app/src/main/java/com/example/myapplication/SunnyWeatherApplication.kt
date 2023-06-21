package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication:Application() {


    companion object{
        const val TOKEN="FpuqtJ9JRK3wbQrn"
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

    }


    override fun onCreate() {
        super.onCreate()
        context=applicationContext

    }
}