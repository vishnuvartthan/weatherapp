package com.example.weatherapp.adapter

import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.*
import com.example.weatherapp.service.ForecastData
import com.example.weatherapp.service.WeatherData
import com.example.weatherapp.ui.ForecastFragment

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

class HourlyAdapter(   val arrayWeather : ForecastData  ,val resources: Resources, val fragmentManager:FragmentManager) :
    RecyclerView.Adapter<HourlyAdapter.HourlyHolder>() {


    inner class HourlyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val time: TextView
        val temp: TextView
        val image: ImageView
        val wSpeed: TextView

        init {
            time = itemView.findViewById(R.id.h_time)
            temp = itemView.findViewById(R.id.h_temp)
            image = itemView.findViewById(R.id.h_image)
            wSpeed = itemView.findViewById(R.id.w_speed)
           itemView.setOnClickListener {
               fragmentManager.beginTransaction().replace(R.id.fragment, ForecastFragment()).addToBackStack("taj").commit()
               Log.i("xara", "done1")
           }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyHolder {

        val child =
            LayoutInflater.from(parent.context).inflate(R.layout.hourly_child_view, parent, false)

        return HourlyHolder(child)

    }



    override fun onBindViewHolder(holder: HourlyHolder, position: Int) {


            holder.temp.text = tempConv(arrayWeather.list[position].main.temp!!.toInt()).toString()
            Log.i("xara", "done")

            holder.time.text = timeConv(arrayWeather.list[position].dt_txt!!,WeatherViewModel.zoneOffset).toLocalTime().toString()
            holder.image.setImageDrawable(getIcon(arrayWeather.list[position].weather[0].icon!!,resources))
            holder.wSpeed.text =arrayWeather.list[position].wind.speed.toString()

    }

    override fun getItemCount(): Int = 40




}