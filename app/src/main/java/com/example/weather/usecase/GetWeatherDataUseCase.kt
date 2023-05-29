package com.example.weather.usecase

import com.example.weather.repo.WeatherData
import com.example.weather.repo.WeatherRepository

class GetWeatherDataUseCase(private val repository: WeatherRepository) {
    suspend fun execute(location: String): WeatherData {
        return repository.fetchWeatherDegree(location)
    } // ?

    suspend fun execute(lat: Double, long: Double): WeatherData {
        return repository.fetchWeatherDegree(lat, long)
    } // ?
}
