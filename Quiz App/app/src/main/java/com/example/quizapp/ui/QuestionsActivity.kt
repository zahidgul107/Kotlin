package com.example.quizapp.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.media.Image
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.R
import com.example.quizapp.model.Question
import com.example.quizapp.utils.Constants

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var progressBar : ProgressBar
    private lateinit var textViewProgress : TextView
    private lateinit var textViewQuestion : TextView
    private lateinit var flagImage: ImageView
    private lateinit var checkButton : Button

    private lateinit var textViewOptionOne : TextView
    private lateinit var textViewOptionTwo : TextView
    private lateinit var textViewOptionThree : TextView
    private lateinit var textViewOptionFour : TextView

    private var questionCounter = 0
    private lateinit var questionsList : MutableList<Question>
    private var selectedAnswer = 0
    private lateinit var currentQuestion: Question
    private var answered = false
    private lateinit var name : String
    private var score = 0
    private lateinit var wrongAnswerSound : MediaPlayer
    private lateinit var rightAnswerSound : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_questions)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        textViewProgress = findViewById<TextView>(R.id.text_view_progress)
        textViewQuestion = findViewById<TextView>(R.id.question_text_view)
        flagImage = findViewById<ImageView>(R.id.image_flag)
        checkButton = findViewById<Button>(R.id.button_check)

        textViewOptionOne = findViewById<TextView>(R.id.text_view_option_one)
        textViewOptionTwo = findViewById<TextView>(R.id.text_view_option_two)
        textViewOptionThree = findViewById<TextView>(R.id.text_view_option_three)
        textViewOptionFour = findViewById<TextView>(R.id.text_view_option_four)

        wrongAnswerSound = MediaPlayer.create(this, R.raw.wronganswersound)
        rightAnswerSound = MediaPlayer.create(this, R.raw.rightanswer)

        textViewOptionOne.setOnClickListener(this)
        textViewOptionTwo.setOnClickListener(this)
        textViewOptionThree.setOnClickListener(this)
        textViewOptionFour.setOnClickListener(this)
        checkButton.setOnClickListener(this)

        questionsList = Constants.getQuestions()
        Log.d("QuestionSize", "${questionsList.size}")

        showNextQuestion()

        if (intent.hasExtra(Constants.USER_NAME)) {
            name = intent.getStringExtra(Constants.USER_NAME)!!
        }
    }

    private fun showNextQuestion() {

        if (questionCounter < questionsList.size) {
            currentQuestion = questionsList[questionCounter]
            checkButton.text = "CHECK"

            resetOptions()
            val question = questionsList[questionCounter]
            flagImage.setImageResource(question.image)
            progressBar.progress = questionCounter
            textViewProgress.text = "${questionCounter + 1}/${progressBar.max}"
            textViewQuestion.text = question.question
            textViewOptionOne.text = question.optionOne
            textViewOptionTwo.text = question.optionTwo
            textViewOptionThree.text = question.optionThree
            textViewOptionFour.text = question.optionFour
        } else {
            checkButton.text = "FINISH"
            // starting activity here
            Intent(this, ResultActivity::class.java).also {
                it.putExtra(Constants.USER_NAME, name)
                it.putExtra(Constants.SCORE, score)
                it.putExtra(Constants.TOTAL_QUESTIONS, questionsList.size)
                startActivity(it)
            }
        }

        questionCounter++
        answered = false
    }

    private fun resetOptions() {
        val options = mutableListOf<TextView>()
        options.add(textViewOptionOne)
        options.add(textViewOptionTwo)
        options.add(textViewOptionThree)
        options.add(textViewOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_border_option_bg
            )
        }
    }

    private fun selectedOption(textView: TextView, selectOptionNumber: Int) {
        resetOptions()
        selectedAnswer = selectOptionNumber

        textView.setTextColor(Color.parseColor("#363A43"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_option_bg
        )
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.text_view_option_one -> {
                selectedOption(textViewOptionOne, 1)
            }
            R.id.text_view_option_two -> {
                selectedOption(textViewOptionTwo, 2)
            }
            R.id.text_view_option_three -> {
                selectedOption(textViewOptionThree, 3)
            }
            R.id.text_view_option_four -> {
                selectedOption(textViewOptionFour, 4)
            }
            R.id.button_check -> {
                if(!answered) {
                    checkAnswer()
                } else {
                    showNextQuestion()
                }
                selectedAnswer = 0
            }
        }
    }

    private fun checkAnswer() {
        answered = true
        if(selectedAnswer == currentQuestion.correctOption) {
            highlightAnswer(selectedAnswer)
            score++
            playRightAnswerSound()
        } else {
            vibratePhone()
            playWrongAnswerSound()
            when(selectedAnswer) {
                1 -> {
                    textViewOptionOne.background = ContextCompat.getDrawable(
                        this,
                        R.drawable.wrong_border_option_bg
                    )
                }
                2 -> {
                    textViewOptionTwo.background = ContextCompat.getDrawable(
                        this,
                        R.drawable.wrong_border_option_bg
                    )
                }
                3 -> {
                    textViewOptionThree.background = ContextCompat.getDrawable(
                        this,
                        R.drawable.wrong_border_option_bg
                    )
                }
                4 -> {
                    textViewOptionFour.background = ContextCompat.getDrawable(
                        this,
                        R.drawable.wrong_border_option_bg
                    )
                }
            }
        }
        checkButton.text = "NEXT"
        showSolution()
    }

    private fun showSolution() {
        selectedAnswer = currentQuestion.correctOption

        highlightAnswer(selectedAnswer)
    }

    private fun highlightAnswer(answer : Int) {
        when(answer) {
            1 -> {
                textViewOptionOne.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.correct_border_option_bg
                )
            }
            2 -> {
                textViewOptionTwo.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.correct_border_option_bg
                )
            }
            3 -> {
                textViewOptionThree.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.correct_border_option_bg
                )
            }
            4 -> {
                textViewOptionFour.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.correct_border_option_bg
                )
            }
        }
    }

    private fun vibratePhone() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                vibrator.vibrate(200)
            }
        }
    }

    private fun playWrongAnswerSound() {
        try {
            if (wrongAnswerSound.isPlaying) {
                wrongAnswerSound.seekTo(0) // Rewind to start if already playing
            }
            wrongAnswerSound.start() // Play the sound
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun playRightAnswerSound() {
        try {
            if (rightAnswerSound.isPlaying) {
                rightAnswerSound.seekTo(0) // Rewind to start if already playing
            }
            rightAnswerSound.start() // Play the sound
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Exit Quiz?")
            .setMessage("Are you sure you want to exit? Your progress will be lost.")
            .setPositiveButton("Exit") { _, _ ->
                super.onBackPressed()
                finish()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    // Add this to clean up MediaPlayer when activity is destroyed
    override fun onDestroy() {
        super.onDestroy()
        wrongAnswerSound.release()
    }

}