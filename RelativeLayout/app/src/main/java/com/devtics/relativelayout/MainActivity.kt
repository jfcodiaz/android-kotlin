package com.devtics.relativelayout

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
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

        val btnHello = this.findViewById<Button>(R.id.button)
        val enName = findViewById<EditText>(R.id.input)
        val cbDeveloper = findViewById<CheckBox>(R.id.imDevelop)
        btnHello.setOnClickListener(View.OnClickListener {
            var msj:String;
            // Toast.makeText(this, enName.text, Toast.LENGTH_LONG).show()
            if(validData()) {
                msj = "Bienvenido, ${enName.text}"
                if(cbDeveloper.isChecked) {
                    msj += ", Eres Developer"
                }
            } else {
                msj = "Escibe tu nombre"
            }
            Toast.makeText(this, msj, Toast.LENGTH_LONG).show()
        })
    }

    private fun validData(): Boolean {
        val etName = findViewById<EditText>(R.id.input)
        return !etName.text.isNullOrEmpty();
    }
}