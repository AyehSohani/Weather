package com.example.weather.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather.R
import com.example.weather.WeatherVM
import com.example.weather.repo.WeatherData

@ExperimentalComposeUiApi
@Composable
fun ShowWeatherData(
    state: State<WeatherVM.State>,
    refreshAction: () -> Unit,
    locationChangeAction: (String) -> Unit
) {
    Box {
        ShowWeatherDetails(state, refreshAction)
        WeatherActions(locationChangeAction)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun WeatherPreview() {
    val state = remember {
        mutableStateOf(
            value = WeatherVM.State.Loaded(
                WeatherData(
                    temperature = 20,
                    feelsLike = 2,
                    description = listOf("Sunny"), humidity = 50, windSpeed = 15
                ),
                pollution = 2,
                location = "Tehran", imageOfWeatherId = R.drawable.sunny
            )
        )
    }
    ShowWeatherData(
        state = state, refreshAction = { /*TODO*/ }, locationChangeAction = {})
}