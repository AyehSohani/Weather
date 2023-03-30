package com.example.weather

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.example.weather.other.OtherActivity
import com.example.weather.weather.ShowWeatherData
import com.example.weather.ui.theme.WeatherTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: WeatherVM by viewModel()

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreate()
        setContent {
            WeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val refreshAction = { viewModel.onRefresh() }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(onClick = {
                            val intent = Intent(this@MainActivity, OtherActivity::class.java)
                            intent.putExtra("data", "this is data from main intent extra")
                            startActivity(intent)
                        }) {
                            Text(text = "Open other activity")
                        }
                        ShowWeatherData(
                            state = viewModel.state.collectAsState(),
                            refreshAction = refreshAction,
                            locationChangeAction = { viewModel.changeLocation(it) }
                        )
                    }
                }
            }
        }
    }
}
