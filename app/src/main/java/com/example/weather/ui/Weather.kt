package com.example.weather.weather

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weather.WeatherVM

@ExperimentalComposeUiApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowWeatherData(
    state: State<WeatherVM.State>,
    refreshAction: () -> Unit,
    locationChangeAction: (String) -> Unit
) {
    val refreshing = state.value == WeatherVM.State.Loading
    val pullRefreshState = rememberPullRefreshState(refreshing, refreshAction)

    var location: String by remember {
        mutableStateOf("Manchester")
    }

    when (state.value) {
        WeatherVM.State.Loading -> CircularProgressIndicator(
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = 16.dp)
        )
        is WeatherVM.State.Loaded -> {
            val weatherState = state.value as WeatherVM.State.Loaded
            Box(
                Modifier
                    .pullRefresh(pullRefreshState)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentWidth()
                        .padding(top = 16.dp)
                ) {
                    TextField(
                        value = location,
                        onValueChange = { location = it }
                    )
                    Text(text = "The temperature is ${weatherState.weatherData.temperature}")
                    Text(text = "The humidity is ${weatherState.weatherData.humidity}")
                    Text(text = "Today is ${weatherState.weatherData.description}")
                    Text(text = "Speed of wind is ${weatherState.weatherData.windSpeed}")
                    Text(text = "The air pollution is ${(weatherState.pollution)}")
                    Button(onClick = { locationChangeAction(location) }) {
                        Text(text = "Update weather")
                    }
                }
                PullRefreshIndicator(
                    refreshing,
                    pullRefreshState,
                    Modifier.align(Alignment.TopCenter)
                )
            }
        }
    }
}