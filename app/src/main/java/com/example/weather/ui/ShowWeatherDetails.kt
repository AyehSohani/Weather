package com.example.weather.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather.R
import com.example.weather.WeatherVM
import com.example.weather.repo.WeatherData

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun ShowWeatherDetails(
    state: State<WeatherVM.State>,
    refreshAction: () -> Unit
) {
    val refreshing = state.value == WeatherVM.State.Loading
    val pullRefreshState = rememberPullRefreshState(refreshing, refreshAction)

    if (state.value is WeatherVM.State.Loaded) {
        val weatherState = state.value as WeatherVM.State.Loaded
        if (weatherState.imageOfWeatherId != R.color.white)
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = weatherState.imageOfWeatherId),
                contentDescription = null
            )
        ShowWeatherRawDetails(weatherState)
    }
    Box(
        Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
            .verticalScroll(rememberScrollState())
    ) {

        PullRefreshIndicator(
            refreshing,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
private fun ShowWeatherRawDetails(weatherState: WeatherVM.State.Loaded) {

    val list = listOf(
        "${weatherState.weatherData.temperature}°",
        "${weatherState.weatherData.humidity}",
        "${weatherState.weatherData.windSpeed} \uD83C\uDF2A ",
        "${(weatherState.weatherData.feelsLike)}°",
        "${(weatherState.pollution)} p",
    )
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        columns = GridCells.Fixed(2)
    ) {
        items(list) {
            Text( text = it, textAlign = TextAlign.Center,style = MaterialTheme.typography.h4)
        }
    }
}

//@Preview
//@Composable
//fun WeatherDetailsPreview() {
//    val state = remember {
//        mutableStateOf(
//            value = WeatherVM.State.Loaded(
//                WeatherData(
//                    temperature = 20,
//                    feelsLike = 2,
//                    description = listOf("Sunny"), humidity = 50, windSpeed = 15
//                ),
//                pollution = 2,
//                location = "Tehran", imageOfWeatherId = R.drawable.sunny
//            )
//        )
//    }
//    ShowWeatherDetails(
//        state = state, refreshAction = { /*TODO*/ })
//}

@Preview
@Composable
fun WeatherRawDetailsPreview() {
    val state = remember {
        mutableStateOf(
            value = WeatherVM.State.Loaded(
                WeatherData(
                    temperature = 20,
                    feelsLike = 21,
                    description = listOf("Sunny"), humidity = 50, windSpeed = 15
                ),
                pollution = 2,
                location = "Tehran", imageOfWeatherId = R.drawable.sunny
            )
        )
    }
    ShowWeatherRawDetails(state.value)
}
