package com.example.weatherapp.service

import com.google.gson.annotations.SerializedName

/**
 * This file contains programmes that may be subjected to copyright infringements.
 * Reusing these programmes, re-publishing is acceptable, while earning out of it is an
 * offensive crime and will be subjected to imprisonment and penalty.
 *
 * We will be watching.
 *
 * Capitan Ghost
 *
 * Ghosts Faction © 2023
 */

class ForecastData(
    @SerializedName("list")
    val list: List<WeatherData>,
    
)