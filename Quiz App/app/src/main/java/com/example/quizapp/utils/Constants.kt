package com.example.quizapp.utils

import com.example.quizapp.R
import com.example.quizapp.model.Question


object Constants {

    fun getQuestions() : MutableList<Question> {
        val questions = mutableListOf<Question>()

        val quest1 = Question(
            id = 1,
            question = "What country does this flag belong to?",
            image = R.drawable.flag_of_italy, // Ensure this matches your actual drawable name
            optionOne = "Italy",
            optionTwo = "India",
            optionThree = "Iran",
            optionFour = "Ireland",
            correctOption = 1
        )
        questions.add(quest1)
        return questions
    }
}