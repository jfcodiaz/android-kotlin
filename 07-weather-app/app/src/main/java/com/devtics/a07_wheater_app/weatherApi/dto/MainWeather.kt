package com.devtics.a07_wheater_app.weatherApi.dto

import com.google.gson.annotations.SerializedName

data class MainWeather(
    @SerializedName("temp") val temp: Double
) {
}