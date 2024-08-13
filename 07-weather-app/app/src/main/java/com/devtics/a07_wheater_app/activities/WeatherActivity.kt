package com.devtics.a07_wheater_app.activities

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.devtics.a07_wheater_app.R
import com.devtics.a07_wheater_app.weatherApi.RetrofitClient
import kotlinx.coroutines.launch

class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_wheater)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val lat = intent.getDoubleExtra("lat", 0.0)
        val lot = intent.getDoubleExtra("lot", 0.0)
        val tvNameCity = findViewById<TextView>(R.id.tvNameCity)
        val tvWeather = findViewById<TextView>(R.id.tvWeather)
        val tvDescription = findViewById<TextView>(R.id.tvDescription)
        lifecycleScope.launch {
            val result = RetrofitClient.apiService.getWeatherByLatLon(
                lat,
                lot,
                "metric",
                "es"
            )
            runOnUiThread {
                tvNameCity.text = result.name
                tvWeather.text = result.main.temp.toString() + "°"
                tvDescription.text = result.weather[0]?.description
            }
        }
    }
}