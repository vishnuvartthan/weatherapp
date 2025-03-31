package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.db.WeatherDataBase

class TheApplication: Application() {
    val database : WeatherDataBase by lazy {
        WeatherDataBase.getDatabase(this)
    }
}