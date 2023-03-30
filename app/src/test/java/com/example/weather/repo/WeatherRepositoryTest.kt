package com.example.weather.repo

import com.example.weather.model.Current
import com.example.weather.model.Location
import com.example.weather.model.WeatherDataModel
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class WeatherRepositoryTest {

    @Test
    fun `when the server response is 200 then it should return correct data`() = runBlocking {
        val service: WeatherRemoteService = mockk()
        coEvery { service.getWeatherData("karaj") }.returns(
            Gson().fromJson(response200, WeatherDataModel::class.java)
        )
        val repository = WeatherRepository(service)
        val actual = repository.fetchWeatherDegree(location = "karaj")
        val expected = WeatherData(9, 7, listOf("Partly cloudy"), 66, 19)
        assertEquals(/* expected = */ expected, /* actual = */ actual)
    }

    @Test
    fun `when user change the location the repository should fetch new data for that location from server`() =
        runBlocking {
            val service: WeatherRemoteService = mockk()
            val repository = WeatherRepository(service)
            coEvery { service.getWeatherData("1") }.returns(
                WeatherDataModel(
                    current = Current(weatherDescriptions = null, weatherIcons = null),
                    location = Location()
                )
            )
            coEvery { service.getWeatherData("2") }.returns(
                Gson().fromJson(response200, WeatherDataModel::class.java)
            )
            val result1 = repository.fetchWeatherDegree("1")
            val result2 = repository.fetchWeatherDegree("2")
            val expected = WeatherData(9, 7, listOf("Partly cloudy"), 66, 19)

            assertNotEquals(result1, result2)
            assertEquals(expected, result2)
        }

//    @Test
//    fun `when server fails then throw an exception`() = runBlocking{
//        val service: WeatherRemoteService = mockk()
//        coEvery { service.getWeatherData(any()) }.coAnswers { throw Exception() }
//        val repository = WeatherRepository(service)
//        val actual = repository.fetchWeatherDegree(location = "karaj")
//        val expected = null
//        assertEquals(/* expected = */ expected, /* actual = */ actual)
//    }

    private val response200 = """{
    "request": {
        "type": "City",
        "query": "Manchester, United Kingdom",
        "language": "en",
        "unit": "m"
    },
    "location": {
        "name": "Manchester",
        "country": "United Kingdom",
        "region": "Greater Manchester",
        "lat": "53.483",
        "lon": "-2.250",
        "timezone_id": "Europe/London",
        "localtime": "2023-03-19 13:49",
        "localtime_epoch": 1679233740,
        "utc_offset": "0.0"
    },
    "current": {
        "observation_time": "01:49 PM",
        "temperature": 9,
        "weather_code": 116,
        "weather_icons": [
            "https://cdn.worldweatheronline.com/images/wsymbols01_png_64/wsymbol_0002_sunny_intervals.png"
        ],
        "weather_descriptions": [
            "Partly cloudy"
        ],
        "wind_speed": 19,
        "wind_degree": 260,
        "wind_dir": "W",
        "pressure": 1016,
        "precip": 0,
        "humidity": 66,
        "cloudcover": 75,
        "feelslike": 7,
        "uv_index": 2,
        "visibility": 10,
        "is_day": "yes"
    }
}"""
}