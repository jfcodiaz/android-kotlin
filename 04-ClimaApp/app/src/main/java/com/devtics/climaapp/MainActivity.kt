package com.devtics.climaapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        val btnMx = findViewById<Button>(R.id.btnMx);
        val btnBer = findViewById<Button>(R.id.btn2);
        val TAG = "com.devtics.climaapp.CIUDAD";
        btnMx.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ClimaActivity::class.java);
            intent.putExtra(TAG, "mx")
            startActivity(intent);
        })

        btnBer.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ClimaActivity::class.java);
            intent.putExtra(TAG, "br")
            startActivity(intent);
        })

    }
}