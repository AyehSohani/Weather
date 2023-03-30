package com.example.weather.model

import com.google.gson.annotations.SerializedName

data class WeatherDataModel(
    @SerializedName("current")
    val current: Current,
    @SerializedName("location")
    val location: Location
)