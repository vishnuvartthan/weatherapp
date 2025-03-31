package com.example.weatherapp

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.dynamicanimation.animation.SpringAnimation
import java.time.LocalDateTime

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
fun timeConv(time:String,zoneOffset:Int):LocalDateTime{ //"2022-08-30 16:00:00"
    return  LocalDateTime.parse("${time.split(' ')[0]}T${time.split(' ')[1]}")
        .plusMinutes(zoneOffset.toLong())
    // 2022-08-30T16.00.00. (2022-08-30T16.00.00+330)
}
fun tempConv(temp: Int): Int {
    return temp - 273
}

fun mpsToKmpH(mps: Float): Float {
    return (mps * 36).toInt() / 10f
}

fun getIcon(iconId: String, resources: Resources): Drawable {
    return when (iconId) {
        "01d" -> ResourcesCompat.getDrawable(resources, R.drawable.d01, null)!!
        "02d" -> ResourcesCompat.getDrawable(resources, R.drawable.d02, null)!!
        "03d" -> ResourcesCompat.getDrawable(resources, R.drawable.dn03, null)!!
        "04d" -> ResourcesCompat.getDrawable(resources, R.drawable.dn04, null)!!
        "09d" -> ResourcesCompat.getDrawable(resources, R.drawable.dn09, null)!!
        "10d" -> ResourcesCompat.getDrawable(resources, R.drawable.dn10, null)!!
        "11d" -> ResourcesCompat.getDrawable(resources, R.drawable.dn11, null)!!
        "13d" -> ResourcesCompat.getDrawable(resources, R.drawable.dn13, null)!!
        "50d" -> ResourcesCompat.getDrawable(resources, R.drawable.dn50, null)!!
        "01n" -> ResourcesCompat.getDrawable(resources, R.drawable.n01, null)!!
        "02n" -> ResourcesCompat.getDrawable(resources, R.drawable.n02, null)!!
        "03n" -> ResourcesCompat.getDrawable(resources, R.drawable.dn03, null)!!
        "04n" -> ResourcesCompat.getDrawable(resources, R.drawable.dn04, null)!!
        "09n" -> ResourcesCompat.getDrawable(resources, R.drawable.dn09, null)!!
        "10n" -> ResourcesCompat.getDrawable(resources, R.drawable.dn10, null)!!
        "11n" -> ResourcesCompat.getDrawable(resources, R.drawable.dn11, null)!!
        "13n" -> ResourcesCompat.getDrawable(resources, R.drawable.dn13, null)!!
        "50n" -> ResourcesCompat.getDrawable(resources, R.drawable.dn50, null)!!

        else -> ResourcesCompat.getDrawable(resources, R.drawable.d01, null)!!
    }

}

fun charCount(string: String, char: Char): Int {
    var int = 0
    for (a in string) {
        if (a == char)
            int += 1
    }
    return int
}





