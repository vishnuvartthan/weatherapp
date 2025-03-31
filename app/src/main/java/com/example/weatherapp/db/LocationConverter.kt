package com.example.weatherapp.db

import android.util.Log
import androidx.room.TypeConverter
import com.example.weatherapp.charCount
import com.example.weatherapp.service.WeatherData

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

class LocationConverter {
    @TypeConverter
    fun fromListToString(type: List<String>): String {
        val prop = type.size
        var name: String? = null
        for (i in type) {
            if (name != null) {
                name = "$name#$i"
            } else {
                name = i
            }
        }
        return name!!
    }

    @TypeConverter
    fun toArrayOfString(value: String): List<String> {
        var tempo = value
        val count = charCount(value, '#')
        val alias: MutableList<String> = mutableListOf()
        for (i in 0..count) {
            if (tempo.contains('#')) {
                alias .add(tempo.split('#')[0])
                tempo = tempo.removeRange(0,alias.last().length+1)
            } else {
               alias.add(tempo)
            }

        }
        return alias
    }

    @TypeConverter
    fun wdToList(wdData: WeatherData?): List<String?> {

        val s = listOf(
            wdData?.visibility.toString(),
            wdData?.timeZone.toString(),
            wdData?.cityId.toString(),
            wdData?.name,
            wdData?.pop.toString(),
            wdData?.dt_txt,
            wdData?.main?.temp.toString(),
            wdData?.main?.humidity.toString(),
            wdData?.main?.pressure.toString(),
            wdData?.main?.temp_min.toString(),
            wdData?.main?.temp_max.toString(),
            wdData?.sys?.type.toString(),
            wdData?.sys?.sunrise.toString(),
            wdData?.sys?.sunset.toString(),
            wdData?.sys?.id.toString(),
            wdData?.sys?.country,
            wdData?.weather?.get(0)?.description,
            wdData?.weather?.get(0)?.main,
            wdData?.weather?.get(0)?.icon,
            wdData?.wind?.speed.toString(),
            wdData?.wind?.deg.toString(),
            wdData?.wind?.gust.toString(),
            wdData?.rain?.oneHour.toString(),
            wdData?.rain?.threeHour.toString(),
            wdData?.snow?.oneHour.toString(),
            wdData?.snow?.threeHour.toString(),
            wdData?.coord?.lat.toString(),
            wdData?.coord?.lon.toString()
        )
        return s

    }

    @TypeConverter
    fun listToWd(dragon: List<String>): WeatherData {
        val s = WeatherData(
            WeatherData.Main(
                dragon[6].toFloatOrNull(),
                dragon[7].toIntOrNull(),
                dragon[8].toIntOrNull(),
                dragon[9].toFloatOrNull(),
                dragon[10].toFloatOrNull()
            ),
            WeatherData.Sys(
                dragon[11].toIntOrNull(),
                dragon[12].toIntOrNull(),
                dragon[13].toIntOrNull(),
                dragon[14].toIntOrNull(),
                dragon[15]
            ),
            arrayOf(WeatherData.Weather(dragon[16], dragon[17], dragon[18])),
            dragon[0].toIntOrNull(),
            WeatherData.Wind(
                dragon[19].toFloatOrNull(),
                dragon[20].toFloatOrNull(),
                dragon[21].toFloatOrNull()
            ),
            WeatherData.Rain(dragon[22].toFloatOrNull(), dragon[23].toFloatOrNull()),
            WeatherData.Snow(dragon[24].toFloatOrNull(), dragon[25].toFloatOrNull()),
            dragon[1].toIntOrNull(),
            dragon[2].toIntOrNull(),
            dragon[3],
            WeatherData.Coord(dragon[26].toFloatOrNull(), dragon[27].toFloatOrNull()),
            dragon[4].toFloatOrNull(),
            dragon[5]

        )
        return s
    }


}


