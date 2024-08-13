package com.devtics.a07_wheater_app.weatherApi.dto

import com.google.gson.annotations.SerializedName

class WeatherResponse(
    @SerializedName("main") val main: MainWeather,
    @SerializedName("name") val name: String,
    @SerializedName("weather") val weather: List<WeatherData>
){
}