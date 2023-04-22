package com.example.weather.di

import com.example.weather.WeatherVM
import com.example.weather.repo.WeatherRemoteService
import com.example.weather.repo.WeatherRepository
import com.example.weather.usecase.GetAirPollutionUseCase
import com.example.weather.usecase.GetWeatherDataUseCase
import com.example.weather.usecase.TypeOfWeatherUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun appModule() = module {

    single {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl("http://api.weatherstack.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    factory { get<Retrofit>().create(WeatherRemoteService::class.java) }

    single { WeatherRepository(get()) }

    factory { GetWeatherDataUseCase(get()) }
    factory { GetAirPollutionUseCase(get()) }

    viewModel { WeatherVM(get(), get(), TypeOfWeatherUseCase()) }
}