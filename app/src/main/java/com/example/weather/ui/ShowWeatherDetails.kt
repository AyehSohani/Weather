package com.example.weather.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.example.weather.R
import com.example.weather.WeatherVM
import com.example.weather.repo.WeatherData
import com.example.weather.ui.theme.TransparentOnBackground

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun ShowWeatherDetails(
    modifier: Modifier,
    state: State<WeatherVM.State>, refreshAction: () -> Unit
) {
    val refreshing = state.value == WeatherVM.State.Loading
    val pullRefreshState = rememberPullRefreshState(refreshing, refreshAction)
        Box(
            modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
        ) {
            if (state.value is WeatherVM.State.Loaded) {
                val weatherState = state.value as WeatherVM.State.Loaded
                val composition by rememberLottieComposition(
                    LottieCompositionSpec.RawRes(
                        weatherState.imageOfWeatherId
                    )
                )
                val progress by animateLottieCompositionAsState(
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )

                LottieAnimation(
                    composition = composition,
                    modifier = Modifier,
                    progress = { progress })
                ShowWeatherRawDetails(modifier, weatherState)
            }

            PullRefreshIndicator(
                refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter)
            )
        }
}

@Composable
private fun ShowWeatherRawDetails(modifier: Modifier, weatherState: WeatherVM.State.Loaded) {

    val list = listOf(
        "${weatherState.weatherData.temperature}°",
        "${weatherState.weatherData.humidity}",
        "${weatherState.weatherData.windSpeed} \uD83C\uDF2A ",
        "${(weatherState.weatherData.feelsLike)}°",
        "${(weatherState.pollution)} p",
    )
    Box(
        modifier = modifier
            .fillMaxWidth(), contentAlignment = Alignment.TopCenter
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .padding(top = 2.dp, bottom = 16.dp)
                .fillMaxWidth(),
            columns = GridCells.Fixed(2)
        ) {
            items(list) {
                Text(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .padding(32.dp)
                        .clip(shape = MaterialTheme.shapes.large)
                        .background(MaterialTheme.colors.TransparentOnBackground),
                    text = it,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4
                )
            }
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
                    description = listOf("Sunny"),
                    humidity = 50,
                    windSpeed = 15
                ), pollution = 2, location = "Tehran", imageOfWeatherId = R.raw.rain
            )
        )
    }
    ShowWeatherRawDetails(Modifier, state.value)
}
