package com.example.weather.repo

import kotlinx.coroutines.delay

class WeatherRepository(private val service: WeatherRemoteService) {
    suspend fun fetchWeatherDegree(location: String): WeatherData {
        val rawData = service.getWeatherData(location)
        return WeatherData(
            temperature = rawData.current.temperature,
            feelsLike = rawData.current.feelslike,
            description = rawData.current.weatherDescriptions ?: emptyList(),
            humidity = rawData.current.humidity,
            windSpeed = rawData.current.windSpeed,
            locationName = rawData.location.name
        )
    }

    suspend fun fetchWeatherDegree(lat: Double, long: Double): WeatherData {
        val rawData = service.getWeatherData("$lat,$long")
        return WeatherData(
            temperature = rawData.current.temperature,
            feelsLike = rawData.current.feelslike,
            description = rawData.current.weatherDescriptions ?: emptyList(),
            humidity = rawData.current.humidity,
            windSpeed = rawData.current.windSpeed,
            locationName = rawData.location.name
        )
    }

    suspend fun fetchAirPollution(): Int {
        delay(4_000)
        return 2 //TODO call network and return the result .
    }
}

data class WeatherData(
    val temperature: Int,
    val feelsLike: Int,
    val description: List<String>,
    val humidity: Int,
    val windSpeed: Int,
    val locationName: String
)