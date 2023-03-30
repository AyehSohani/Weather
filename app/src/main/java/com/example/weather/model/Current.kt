package com.example.weather.model

import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("weather_descriptions")
    val weatherDescriptions: List<String>?,
    @SerializedName("observation_time")
    val observationTime: String = "",
    @SerializedName("wind_degree")
    val windDegree: Int = 0,
    @SerializedName("visibility")
    val visibility: Int = 0,
    @SerializedName("weather_icons")
    val weatherIcons: List<String>?,
    @SerializedName("feelslike")
    val feelslike: Int = 0,
    @SerializedName("is_day")
    val isDay: String = "",
    @SerializedName("wind_dir")
    val windDir: String = "",
    @SerializedName("pressure")
    val pressure: Int = 0,
    @SerializedName("cloudcover")
    val cloudcover: Int = 0,
    @SerializedName("uv_index")
    val uvIndex: Int = 0,
    @SerializedName("temperature")
    val temperature: Int = 0,
    @SerializedName("humidity")
    val humidity: Int = 0,
    @SerializedName("wind_speed")
    val windSpeed: Int = 0,
    @SerializedName("weather_code")
    val weatherCode: Int = 0
)