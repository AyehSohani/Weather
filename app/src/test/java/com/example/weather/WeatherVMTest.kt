package com.example.weather

import com.example.weather.repo.WeatherData
import com.example.weather.usecase.GetAirPollutionUseCase
import com.example.weather.usecase.GetWeatherDataUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

@ExperimentalCoroutinesApi
class WeatherVMTest {

    @MockK
    lateinit var weatherDataUseCase: GetWeatherDataUseCase
    @MockK
    lateinit var airPollutionUseCase: GetAirPollutionUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when the user opens the app, it should fetch data by default location`() = runTest(UnconfinedTestDispatcher()) {
        val weatherLoadedData = WeatherData(1,1, listOf(), 1, 1)
        coEvery { weatherDataUseCase.execute(any()) }.returns(weatherLoadedData)
        coEvery { airPollutionUseCase.execute() }.returns(0)
        val viewModel = WeatherVM(weatherDataUseCase, airPollutionUseCase, coroutineContext)
        viewModel.onCreate()
        val actual = WeatherVM.State.Loaded(weatherLoadedData, 0, "Manchester")
        assertEquals(viewModel.state.value, actual)
    }

    @Test
    fun `when the user opens the app, it should show loading`() = runTest(UnconfinedTestDispatcher()) {
        val weatherLoadedData = WeatherData(1,1, listOf(), 1, 1)
        coEvery { weatherDataUseCase.execute(any()) }.coAnswers {
            delay(10_000)
            weatherLoadedData
        }
        coEvery { airPollutionUseCase.execute() }.returns(0)
        val viewModel = WeatherVM(weatherDataUseCase, airPollutionUseCase, coroutineContext)
        viewModel.onCreate()
        assertEquals(viewModel.state.value, WeatherVM.State.Loading)

        advanceTimeBy(10_010)
        val actual = WeatherVM.State.Loaded(weatherLoadedData, 0, "Manchester")
        assertEquals(viewModel.state.value, actual)
    }

    @Test
    fun onRefresh() {
    }

    @Test
    fun changeLocation() {
    }
}