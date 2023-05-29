package com.example.weather.usecase

import com.example.weather.R

class TypeOfWeatherUseCase {
    fun execute(typeOfWeather: String): Int {
        return when (typeOfWeather) {
            //Todo update animations
            "Sunny" -> R.raw.sunny
            "Rainy" -> R.raw.rain
            "Cloudy" -> R.raw.cloudy
            "partly Cloudy" -> R.raw.sunnyrainy
            "Snowing" -> R.raw.snow
            "Clear" -> R.raw.clear
            "Windy" -> R.raw.windy
            else -> R.raw.partlycloudy
        }
    }
}