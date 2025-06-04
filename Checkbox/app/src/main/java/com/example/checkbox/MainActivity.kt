package com.example.checkbox

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var checkBoxKotlin : CheckBox
    private lateinit var checkBoxJava : CheckBox
    private lateinit var checkBoxPython : CheckBox
    private lateinit var textViewChoose : TextView
    private lateinit var showButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkBoxKotlin = findViewById<CheckBox>(R.id.checkBoxKotlin)
        checkBoxJava = findViewById<CheckBox>(R.id.checkBoxJava)
        checkBoxPython = findViewById<CheckBox>(R.id.checkBoxPython)
        textViewChoose = findViewById<TextView>(R.id.textView)
        showButton = findViewById<Button>(R.id.button)

        showButton.setOnClickListener {

            val sb = StringBuilder();
            sb.append(checkBoxKotlin.text.toString() + " status is: " + checkBoxKotlin.isChecked + "\n")
            sb.append(checkBoxJava.text.toString() + " status is: " + checkBoxJava.isChecked + "\n")
            sb.append(checkBoxPython.text.toString() + " status is: " + checkBoxPython.isChecked + "\n")

            textViewChoose.text = sb.toString()
        }
    }
}