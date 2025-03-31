package com.example.weatherapp

import android.app.Activity
import android.util.Log
import androidx.lifecycle.*
import com.example.weatherapp.db.Location
import com.example.weatherapp.db.LocationDao
import com.example.weatherapp.db.Time
import com.example.weatherapp.db.WeatherInfo
import com.example.weatherapp.service.ForecastData
import com.example.weatherapp.service.GeoLocation
import com.example.weatherapp.service.Service
import com.example.weatherapp.service.WeatherData
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

class WeatherViewModel(activity: Activity, val DAO: LocationDao) : ViewModel() {


    private val _liveData = MutableLiveData<WeatherData?>()
    val liveData: LiveData<WeatherData?> = _liveData

    private val _hForecast = MutableLiveData<ForecastData>()
    val hForecast: LiveData<ForecastData> = _hForecast

    val geoLocation = GeoLocation(activity)

    val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    val service: Service = retrofit.create(Service::class.java)


    companion object {
        const val MILLIS_TO_MIN = 60000
        val zoneOffset = TimeZone.getDefault().rawOffset / MILLIS_TO_MIN
    }

    fun performRequest(lat: String, lon: String) {

        viewModelScope.launch {
            Log.i("asdasdasd", "dsassds")


            if (DAO.getTime(0) == null) {
                weatherRequest(lat, lon)
                Log.i("asdasdasd", "dsassds9")

            } else{
                if (DAO.getTime(0).time + 3600 < LocalDateTime.now()
                        .toEpochSecond(ZoneOffset.UTC)
                ) {
                    Log.i("asdasdasd", "dsassds0")

                    weatherRequest(lat, lon)
                } else {
                    Log.i("asdasdasd", "dsassds")

                    _liveData.value = DAO.getWeather().row
                }
            }

            if (DAO.getTime(0) == null) {
                forecastRequest(lat, lon)
            } else{
                if (DAO.getTime(1).time + 10800 <= LocalDateTime.now()
                        .toEpochSecond(ZoneOffset.UTC)
                ) {
                    forecastRequest(lat, lon)
                } else {

                    val sample = DAO.getForeCast()
                    val list: MutableList<WeatherData> = mutableListOf()
                    for (i in sample) {
                        list.add(i.row)
                    }
                    _hForecast.value = ForecastData(list)
                }
            }

        }
    }

    fun weatherRequest(latitude: String, longitude: String) {


        Log.i("asdasdas", "done14")

        val wData: Call<WeatherData> =
            service.getWeatherData(latitude, longitude, "d302966d75ee6c16375cb23c4c9c077f")


        Log.i("asdasdas", "done13")

        wData.enqueue(object : Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                setTimeStamp(Time(0, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)))
                _liveData.value = response.body()
                setWeather(WeatherInfo(0, response.body()!!))
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                Log.i("asdasdas", "done15")


            }
        })
        Log.i("asdasdas", "done16")


    }

    fun forecastRequest(latitude: String, longitude: String) {
        val forecast: Call<ForecastData> =
            service.getForecastDAta(latitude, longitude, "d302966d75ee6c16375cb23c4c9c077f")

        setTimeStamp(Time(1, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)))

        forecast.enqueue(object : Callback<ForecastData> {
            override fun onResponse(
                call: Call<ForecastData>, response: Response<ForecastData>
            ) {
                _hForecast.value = response.body()!!
                viewModelScope.launch {
                    for (i in 1..40) {
                        DAO.insertWeather(WeatherInfo(i, response.body()!!.list[i - 1]))

                    }
                }
            }

            override fun onFailure(call: Call<ForecastData>, t: Throwable) {
            }

        })


    }

    fun setNote(weather: Location) {

        viewModelScope.launch {
            try {
                DAO.insertNote(weather)

            } catch (e: java.lang.Exception) {
                Log.i("dsadsa", "kadhalukku_vizhai_illai")
            }
        }
    }

    fun update(weather: Location) {
        viewModelScope.launch {
            DAO.updateNote(weather)

        }
    }

    fun setWeather(weather: WeatherInfo) {

        viewModelScope.launch {
            try {
                DAO.insertWeather(weather)


            } catch (e: java.lang.Exception) {
                Log.i("dsadsa", "kadhalukku_vizhai_illai")
            }
        }
    }

    fun setTimeStamp(time: Time) {
        viewModelScope.launch {
            try {
                DAO.insertTime(time)

            } catch (e: java.lang.Exception) {

            }
        }
    }

    fun updateWeather(weather: WeatherInfo) {
        viewModelScope.launch {
            DAO.updateWeather(weather)
        }
    }
    class WeatherViewModelFactory(
        private val activity: Activity, private val dao: LocationDao
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
                return WeatherViewModel(activity, dao) as T
            }
            throw IllegalArgumentException("less loose end")
        }
    }
}

