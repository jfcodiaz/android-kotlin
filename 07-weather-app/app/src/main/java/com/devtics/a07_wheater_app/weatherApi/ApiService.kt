package com.devtics.a07_wheater_app.weatherApi

import com.devtics.a07_wheater_app.BuildConfig
import com.devtics.a07_wheater_app.weatherApi.dto.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query
interface ApiService {
    @GET("weather")
    suspend fun getWeatherByLatLon(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") metric: String,
        @Query("lang") lang: String,
        @Query("appid") appid: String = BuildConfig.weatherApiKey,
    ) : WeatherResponse
}
