package com.example.seekbar

import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var seekBar : SeekBar
    private lateinit var rate : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar = findViewById<SeekBar>(R.id.seekBar)
        rate = findViewById<TextView>(R.id.progressRate)

        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                rate.text = "Rating: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Optional: Do something when user starts dragging
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                rate.text = "Final Rate: "  + seekBar!!.progress.toString()
            }
        })



    }
}