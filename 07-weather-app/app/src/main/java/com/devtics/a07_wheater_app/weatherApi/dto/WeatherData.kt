package com.devtics.a07_wheater_app.weatherApi.dto

import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("description") val description: String
) {
}