package com.example.weather

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.example.weather.other.OtherActivity
import com.example.weather.ui.ShowWeatherData
import com.example.weather.ui.theme.WeatherTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: WeatherVM by viewModel()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val requestCode = 123

    @SuppressLint("MissingPermission")
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        viewModel.onCreate()
        setContent {
            WeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val refreshAction = { viewModel.onRefresh() }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ShowWeatherData(
                            state = viewModel.state.collectAsState(),
                            refreshAction = refreshAction,
                            locationChangeAction = { viewModel.changeLocation(it) },
                            openOtherActivityAction = ::otherActivity,
                            locationRequestAction = ::currentLocation
                        )
                    }
                }
            }
        }
    }

    private fun otherActivity() {
        val openOtherActivityIntent =
            Intent(this@MainActivity, OtherActivity::class.java)
        openOtherActivityIntent.putExtra(
            "data",
            "this is data from main intent extra"
        )
        startActivity(openOtherActivityIntent)
    }

    @SuppressLint("MissingPermission")
    private fun currentLocation() {
        checkLocationPermissions()
        fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY, CancellationTokenSource().token
        ).addOnSuccessListener { location: Location? ->
            // Got last known location. In some rare situations this can be null.
            val lat = location?.latitude
            val long = location?.longitude
            if (lat != null && long != null) {
                viewModel.currentLocationAction(lat = lat, long = long)
            }
        }
    }

    private fun checkLocationPermissions() {
        if (ActivityCompat.checkSelfPermission(
                this,
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION),
                requestCode
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == this.requestCode) {

            }
        }
    }
}
