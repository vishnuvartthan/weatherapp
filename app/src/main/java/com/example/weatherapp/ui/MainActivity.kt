package com.example.weatherapp.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.R
import com.example.weatherapp.TheApplication
import com.example.weatherapp.WeatherViewModel
import com.example.weatherapp.service.GeoLocation
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<WeatherViewModel> {

        WeatherViewModel.WeatherViewModelFactory(
            this, (application as TheApplication).database.noteDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.beginTransaction().add(R.id.fragment, WeatherFragment()).commit()


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GeoLocation.REQUEST_LOCATION_CODE && resultCode == RESULT_OK) {
            viewModel.geoLocation.getLocation()
        } else {

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == GeoLocation.REQUEST_LOCATION_CODE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            viewModel.geoLocation.getLocation()
        } else {

        }


    }


}


