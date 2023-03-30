package com.example.weather.usecase

import com.example.weather.repo.WeatherRepository

class GetAirPollutionUseCase(private val repository: WeatherRepository) {
    suspend fun execute(): Int = repository.fetchAirPollution() //?
} // this is another version for returning an inlined variable
// (functional programming we can use or behave methods like variables)
/** =>
 *   fun x(){
 *      return method()
 *   }
 *   ===
 *   fun x() = methode()
 */
