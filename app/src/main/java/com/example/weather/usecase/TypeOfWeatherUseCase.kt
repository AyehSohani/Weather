package com.example.weather.usecase

import com.example.weather.R

class TypeOfWeatherUseCase {
    fun execute(typeOfWeather: String): Int {
        return when (typeOfWeather) {
            //Todo update animations
            "Sunny" -> R.raw.windy
            "Rainy" -> R.raw.windy
            "Cloudy" -> R.raw.windy
            "Snowing" -> R.raw.windy
            "Clear" -> R.raw.windy
            "Windy" -> R.raw.windy
            else -> R.raw.windy
        }
    }
}