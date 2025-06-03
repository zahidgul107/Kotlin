package com.example.togglebutton

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var toggleButton: ToggleButton
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggleButton = findViewById<ToggleButton>(R.id.toggleButton)
        textView = findViewById<TextView>(R.id.textView)

        toggleButton.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked) {
                textView.visibility = View.VISIBLE
            } else {
                textView.visibility = View.INVISIBLE
            }
        }
    }
}