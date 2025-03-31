package com.example.weatherapp.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This file contains programmes that may be subjected to copyright infringements.
 * Reusing these programmes, re-publishing is acceptable, while earning out of it is an
 * offensive crime and will be subjected to imprisonment and penalty.
 *
 * We will be watching.
 *
 * Capitan Ghost
 *
 * Ghosts Faction © 2023.
 */

interface Service {
    @GET("data/2.5/weather?")
    fun getWeatherData(@Query("lat") lat: String, @Query("lon") lon: String, @Query("appid") apiKey: String ):Call<WeatherData>
    @GET("data/2.5/forecast")
    fun getForecastDAta(@Query("lat") lat:String, @Query("lon") lon: String,@Query("appid") appid: String) : Call<ForecastData>
}