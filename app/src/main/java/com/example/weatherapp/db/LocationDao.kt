package com.example.weatherapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

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
 *
 */

@Dao
interface LocationDao {

    @Query("SELECT * FROM locationDb WHERE id = :note_id")
    suspend fun getNote(note_id:Int) : Location

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Location)

    @Update
    suspend fun updateNote(note: Location)

    //ForeCastData

    @Query("SELECT * FROM cWdb WHERE id = 0")
    suspend fun getWeather() : WeatherInfo

    @Query("SELECT * FROM cWdb WHERE id BETWEEN 1 AND 40")
    suspend fun getForeCast() :List<WeatherInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(row: WeatherInfo)

    @Update
    suspend fun updateWeather(row: WeatherInfo)

    //Time
    @Query("SELECT * FROM timeTable WHERE id = :time ")
    suspend fun getTime(time : Int): Time

    @Query("SELECT * FROM timeTable")
    suspend fun getClock():List<Time>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTime(time: Time)

}


