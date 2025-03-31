package com.example.weatherapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.service.WeatherData


@Entity(tableName = "locationDb")
data class Location(
    @PrimaryKey()
    @ColumnInfo(name = "id")
    val id : Int,
    val lat : String,
    val lon : String
)

@Entity (tableName = "cWdb")
 data class WeatherInfo(
    @PrimaryKey()
    @ColumnInfo(name = "id")
    val id:Int ,
    val row : WeatherData,
 )

@Entity(tableName = "timeTable")
data class Time(
    @PrimaryKey()
    @ColumnInfo(name = "id")
    val id : Int ,
    val time : Long
)