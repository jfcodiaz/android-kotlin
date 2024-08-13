package com.devtics.a07_wheater_app.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devtics.a07_wheater_app.R
import com.devtics.a07_wheater_app.utils.Network


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBerlin = findViewById<Button>(R.id.btnBerlin)
        btnBerlin.tag = hashMapOf("lat" to 52.5170365, "lot" to 13.3888599)
        val btnShanghai = findViewById<Button>(R.id.btnShanghai)
        btnShanghai.tag = hashMapOf("lat" to 31.2322758, "lot" to 121.4692071)
        val btnMexicoCity = findViewById<Button>(R.id.btnMexicoCity)
        btnMexicoCity.tag = hashMapOf("lat" to 19.4326296, "lot" to -99.1331785)

        val clickListener: (View) -> Unit = {
            if (!Network.isConnected(this)) {
                Toast.makeText(this, "No hay Conexión", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, WeatherActivity::class.java)
                val data = it.tag as HashMap<*, *>;
                intent.putExtra("lat", data["lat"] as Double)
                intent.putExtra("lot", data["lot"] as Double)
                startActivity(intent);
            }
        }
        btnBerlin.setOnClickListener(clickListener)
        btnShanghai.setOnClickListener(clickListener)
        btnMexicoCity.setOnClickListener(clickListener)
    }
}