package com.example.activity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    private lateinit var textViewIntent : TextView
    private lateinit var goBackButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        textViewIntent = findViewById<TextView>(R.id.textView2)
        goBackButton = findViewById<Button>(R.id.goBackButton)

        goBackButton.setOnClickListener {
            val intent = intent
            intent.putExtra(Constants.INTENT_MESSAGE2_KEY, "Hello from second Activity")
            setResult(Constants.RESULT_CODE, intent)
            finish()
        }

        var data = intent.extras

        data?.let {
            val message = data.getString(Constants.INTENT_MESSAGE_KEY)
            val message2 = data.getString(Constants.INTENT_MESSAGE2_KEY)
            val number = data.getDouble(Constants.INTENT_DATA_NUMBER)

            textViewIntent.text = message + "\n" + message2 + "\n" + number
        }
    }
}