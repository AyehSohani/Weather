package com.example.weather.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather.R
import com.example.weather.WeatherVM
import com.example.weather.repo.WeatherData

@ExperimentalComposeUiApi
@Composable
fun ShowWeatherData(
    state: State<WeatherVM.State>,
    refreshAction: () -> Unit,
    openOtherActivityAction: () -> Unit,
    locationChangeAction: (String) -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        ShowWeatherDetails(Modifier.weight(1f), state, refreshAction)
        WeatherActions(Modifier,locationChangeAction, openOtherActivityAction )
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
                location = "Tehran", imageOfWeatherId = R.raw.rain
            )
        )
    }
    ShowWeatherData(
        state = state, refreshAction = { /*TODO*/ }, locationChangeAction = {}, openOtherActivityAction = {})
}