package com.example.weatherapp.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weatherapp.*
import com.example.weatherapp.db.WeatherInfo
import com.example.weatherapp.service.ForecastData
import com.example.weatherapp.service.WeatherData


class CastAdapter (val uWeather: List<WeatherInfo>, val resources: Resources):RecyclerView.Adapter<CastAdapter.CastHolder>() {

    class CastHolder(itemView:View):ViewHolder(itemView){
        val wSpeed : TextView
        val humidity : TextView
        val pressure : TextView
        val description : ImageView
        val cor : TextView
        val temp1 : TextView
        val time : TextView

        init {
            wSpeed = itemView.findViewById(R.id.wind_speed)
            humidity = itemView.findViewById(R.id.c_humidity)
            pressure = itemView.findViewById(R.id.c_pressure)
            description = itemView.findViewById(R.id.c_image)
            cor = itemView.findViewById(R.id.c_rain)
            time = itemView.findViewById(R.id.c_time)
            temp1 = itemView.findViewById(R.id.temperature)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastHolder {

        val game = LayoutInflater.from(parent.context).inflate(R.layout.forecast_child_view,parent,false)
        return CastHolder(game)
    }

    var easy : WeatherData? = null
    override fun onBindViewHolder(holder: CastHolder, position: Int) {
        easy = uWeather[position].row
        holder.wSpeed.text = easy!!.wind.speed.toString()
        holder.humidity.text = easy!!.main.humidity.toString()
        holder.pressure.text = easy!!.main.pressure.toString()
        holder.description.setImageDrawable(getIcon(easy!!.weather[0].icon!!,resources))
        holder.cor.text = easy!!.pop.toString()
        holder.temp1.text = tempConv(easy!!.main.temp!!.toInt()) .toString()
        holder.time.text = timeConv(easy!!.dt_txt!!,WeatherViewModel.zoneOffset).toLocalTime().toString()

    }

    override fun getItemCount(): Int = uWeather.size

}