package com.example.weather.usecase

import com.example.weather.R

class TypeOfWeatherUseCase {
    fun execute(typeOfWeather: String): Int {
        return when (typeOfWeather) {
            "Sunny" -> R.drawable.sunny
            "Rainy" -> R.drawable.rainy
            "Cloudy" -> R.drawable.cloudy
            "Snowing" -> R.drawable.snowing
            "Clear" -> R.drawable.clear
            else -> R.color.white
        }
    }
}