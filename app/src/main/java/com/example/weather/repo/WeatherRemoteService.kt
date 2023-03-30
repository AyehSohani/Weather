package com.example.weather.repo

import com.example.weather.model.WeatherDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRemoteService {
    @GET("current?access_key=0196898c66206ad9fb083167722461dd")
    suspend fun getWeatherData(@Query("query") location: String): WeatherDataModel
}