package com.example.weatherapp.service

import com.example.weatherapp.WeatherViewModel
import com.example.weatherapp.timeConv
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.util.*

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

class WeatherData(
    @SerializedName("main")
    val main: Main,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("weather")
    val weather: Array<Weather>,
    @SerializedName("visibility")
    val visibility: Int?,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("rain")
    val rain: Rain,
    @SerializedName("snow")
    val snow: Snow,
    @SerializedName("timeZone")
    val timeZone: Int?,
    @SerializedName("id")
    val cityId: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("pop")
    val pop: Float?,
    @SerializedName("dt_txt")
    val dt_txt: String?


) {
    class Main(
        @SerializedName("temp")
        val temp: Float?,

        @SerializedName("humidity")
        val humidity: Int?,

        @SerializedName("pressure")
        val pressure: Int?,

        @SerializedName("temp_min")
        val temp_min: Float?,

        @SerializedName("temp_max")
        val temp_max: Float?
    )

    class Sys(
        @SerializedName("type")
        val type: Int?,

        @SerializedName("sunrise")
        val sunrise: Int?,

        @SerializedName("sunset")
        val sunset: Int?,

        @SerializedName("id")
        val id: Int?,

        @SerializedName("country")
        val country: String?

    )

    class Weather(
        @SerializedName("description")
        val description: String?,

        @SerializedName("main")
        val main: String?,

        @SerializedName("icon")
        val icon: String?

    )

    class Wind(
        @SerializedName("speed")
        val speed: Float?,

        @SerializedName("deg")
        val deg: Float?,

        @SerializedName("gust")
        val gust: Float?
    )


    class Rain(
        @SerializedName("1h")
        val oneHour: Float?,

        @SerializedName("3h")
        val threeHour: Float?
    )

    class Snow(
        @SerializedName("1h")
        val oneHour: Float?,

        @SerializedName("3h")
        val threeHour: Float?
    )

    class Coord(
        @SerializedName("lat")
        val lat: Float?,

        @SerializedName("lon")
        val lon: Float?
    )

}
