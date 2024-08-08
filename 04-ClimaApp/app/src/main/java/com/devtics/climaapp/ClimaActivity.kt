package com.devtics.climaapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class ClimaActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_clima)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val mapCities = mapOf(
            "mx" to City("Mexico", 25, "Soleado"),
            "br" to City("Berlin", 10, "Nublado")
        )

        val key = intent.getStringExtra("com.devtics.climaapp.CIUDAD")
        val selectedCity = mapCities[key];
        findViewById<TextView>(R.id.tvCity).text = selectedCity?.name
        findViewById<TextView>(R.id.tvStatus).text = selectedCity?.status
        findViewById<TextView>(R.id.tvWeather).text = "${selectedCity?.weather}°"
    }
}