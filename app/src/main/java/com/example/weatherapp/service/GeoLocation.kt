package com.example.weatherapp.service

import android.Manifest
import android.app.Activity
import android.content.IntentSender
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.Priority
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.tasks.Task
import kotlin.properties.Delegates


class GeoLocation(private val activity: Activity) {

    companion object {
        const val LATITUDE = 0
        const val LONGITUDE = 1
        const val REQUEST_LOCATION_CODE = 1000
    }

    private lateinit var task: Task<LocationSettingsResponse>
    private lateinit var client: SettingsClient
    private lateinit var builder: LocationSettingsRequest.Builder
    private lateinit var locationRequest: LocationRequest
    var variety: Array<String>? = null
    private val _location: MutableLiveData<Array<String>> = MutableLiveData()
    val location: LiveData<Array<String>> = _location

    private var fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(activity)


    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            activity, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission() {
        if (!checkPermission()) ActivityCompat.requestPermissions(
            activity, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_LOCATION_CODE
        )
    }


    fun getLocation() {
        Log.i("asdasdasd", "success2")

        if (checkPermission()) {
            Log.i("asdasdasd", "success2")

            fusedLocationClient.lastLocation.addOnSuccessListener {

                if (it != null) {
                    variety = arrayOf(it.latitude.toString(), it.longitude.toString())
                    _location.value = variety
                    Log.i("asdasdasd", "succewwwss")

                } else {
                    Log.i("asdasdasd", "successas")

                    fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                        .addOnSuccessListener { truffle ->
                            if (truffle != null) {
                                Log.i("asdasdasd", "susdasdccess")

                                _location.value = arrayOf(
                                    truffle.latitude.toString(), truffle.longitude.toString()
                                )
                            } else {

                                Log.i("asdasdasd", "succesasdasdasds")
                                createLocationRequest()
                            }
                        }
                }
            }
        }
    }

    fun createLocationRequest() {
        locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000).build()
        builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        client = LocationServices.getSettingsClient(activity)
        task = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener {

            Log.i("asdasdasd", "success")

            getLocation()

        }
        task.addOnFailureListener {

            if (it is ResolvableApiException) {
                try {
                    it.startResolutionForResult(activity, REQUEST_LOCATION_CODE)

                } catch (balloon: IntentSender.SendIntentException) {
                    TODO()
                }
            }
        }
    }




}
