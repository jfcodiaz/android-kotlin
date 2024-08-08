package com.devtics.climaapp

class City (name: String, weather: Int, status: String) {
    var name:String = ""
    var weather:Int = 0
    var status:String = ""

    init {
        this.name = name
        this.weather = weather
        this.status = status
    }
}