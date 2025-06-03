package com.example.changecolor

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {
    private lateinit var view: View
    private lateinit var button: FloatingActionButton
    private val colors = arrayOf(Color.RED, Color.WHITE, Color.GRAY, Color.GREEN , Color.MAGENTA,
        Color.BLACK, Color.YELLOW)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view = findViewById<View>(R.id.view)
        button = findViewById<FloatingActionButton>(R.id.button_change)

        button.setOnClickListener {
            view.setBackgroundColor(colors[Random.nextInt(colors.size)])
        }

    }
}