package com.example.weather

import com.example.weather.WeatherVM.State.Loaded
import com.example.weather.WeatherVM.State.Loading
import com.example.weather.repo.WeatherData
import com.example.weather.usecase.GetAirPollutionUseCase
import com.example.weather.usecase.GetWeatherDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WeatherVM(
    private val weatherDataUseCase: GetWeatherDataUseCase,
    private val airPollutionUseCase: GetAirPollutionUseCase,
    coroutineContext: CoroutineContext? = null
) : BaseViewModel(coroutineContext) {

    sealed interface State {
        object Loading : State
        data class Loaded(
            val weatherData: WeatherData,
            val pollution: Int,
            val location: String
        ) : State
    }


//    private val _a : MutableList<Int> = mutableListOf()
//    val a :List<Int> = _a

    private val _state: MutableStateFlow<State> = MutableStateFlow(Loading)
    val state: StateFlow<State> = _state //?

    fun onCreate() {
        loadData()
    }

    fun onRefresh() {
        loadData()
    }

    private fun loadData(location: String = "Manchester") {
        _state.value = Loading
        launch {
            _state.value = Loaded(
                weatherData = weatherDataUseCase.execute(location),
                pollution = airPollutionUseCase.execute(),
                location
            )
        }
    }

    fun changeLocation(location: String) {
        _state.value = (_state.value as Loaded).copy(location = location)
        loadData(location)
    }
}
