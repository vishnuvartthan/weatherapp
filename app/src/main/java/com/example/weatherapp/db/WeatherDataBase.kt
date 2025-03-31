package com.example.weatherapp.db

import android.content.Context
import androidx.room.*

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

@Database(entities = [Location::class, WeatherInfo::class, Time::class], version = 1, exportSchema = false)
@TypeConverters(LocationConverter::class)
abstract class WeatherDataBase : RoomDatabase() {

    abstract fun noteDao(): LocationDao

    companion object {

        @Volatile
        var INSTANCE: WeatherDataBase? = null

        fun getDatabase(context: Context): WeatherDataBase {
            synchronized(this) {

                val noteDbase =
                    Room.databaseBuilder(context, WeatherDataBase::class.java, "weather")
                        .build()

                INSTANCE = noteDbase

                return noteDbase
            }

        }
    }

}



