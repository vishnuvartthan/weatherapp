package com.example.weatherapp.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.TextView
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.*
import com.example.weatherapp.adapter.HourlyAdapter
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.db.Location
import com.example.weatherapp.db.WeatherDataBase
import com.example.weatherapp.service.GeoLocation
import kotlinx.coroutines.launch

/**
 * This file contains programmes that may be subjected to copyright infringements.
 * Reusing these programmes, re-publishing is acceptable, while earning out of it is an
 * offensive crime and will be subjected to prosecution.
 *
 * We will be watching.
 *
 * Capitan Ghost
 *
 * Ghosts Faction © 2023
 */


class WeatherFragment : Fragment() {

    private var lat = "0.0"
    private var lon = "0.0"
    lateinit var binding: FragmentWeatherBinding
    private val weatherViewModel: WeatherViewModel by activityViewModels {
        WeatherViewModel.WeatherViewModelFactory(
            requireActivity(),
            WeatherDataBase.getDatabase(requireContext()).noteDao()
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        weatherViewModel.geoLocation.requestPermission()

        weatherViewModel.geoLocation.getLocation()
        Log.i("asdasdasd", "ijhgh")


        lifecycleScope.launch {
            if (weatherViewModel.DAO.getNote(1) != null) {
                val p = weatherViewModel.DAO.getNote(1)
                weatherViewModel.performRequest(p.lat, p.lon)
            }

        }

        weatherViewModel.geoLocation.location.observe(viewLifecycleOwner) {

            if (it != null && (it[GeoLocation.LATITUDE] != lat || it[GeoLocation.LONGITUDE] != lon)) {

                lat = it[GeoLocation.LATITUDE]
                lon = it[GeoLocation.LONGITUDE]
                weatherViewModel.performRequest(lat, lon)
                weatherViewModel.setNote(Location(1, lat, lon))
            }
        }


        weatherViewModel.liveData.observe(viewLifecycleOwner) {
            binding.apply {
                humidity.text = it!!.main.humidity.toString()
                windSpeed.text = mpsToKmpH(it.wind.speed!!).toString()
                pressure.text = it.main.pressure.toString()
                description.text = it.weather[0].description!!.replaceFirstChar { it.uppercase() }
                currTemp.text = tempConv(it.main.temp!!.toInt()).toString()
                city.text = it.name.plus(", ${it.sys.country}")

//                lifecycleScope.launch {
//                    binding.timeStamp.text = weatherViewModel.DAO.getTime(0).time.toString()
//                }
//            }

            }


            weatherViewModel.hForecast.observe(viewLifecycleOwner) {
                binding.rain.text = it.list[0].pop.toString()
                binding.rv1.adapter = HourlyAdapter(it, resources, parentFragmentManager)
                binding.polska.visibility = View.INVISIBLE
                binding.rv1.visibility = View.VISIBLE
            }

            binding.rv1.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)


        }
    }

}
