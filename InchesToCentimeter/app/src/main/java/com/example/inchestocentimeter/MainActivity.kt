package com.example.inchestocentimeter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val inchVal = 2.54
    private lateinit var enterInches : EditText
    private lateinit var convertButton: Button
    private lateinit var textViewCentimeters: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        enterInches = findViewById<EditText>(R.id.editTextInches)
        convertButton = findViewById<Button>(R.id.button_convert)
        textViewCentimeters = findViewById<TextView>(R.id.textViewConvert)

        convertButton.setOnClickListener {
            if(!enterInches.text.toString().isEmpty()) {
                val result = enterInches.text.toString().toDouble() * inchVal
                textViewCentimeters.text = result.toString()
            } else {
                textViewCentimeters.text = getString(R.string.fail)
            }
        }
    }
}