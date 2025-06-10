package com.example.quizapp.utils

import com.example.quizapp.R
import com.example.quizapp.model.Question


object Constants {

    const val USER_NAME = "user_name"
    const val TOTAL_QUESTIONS = "total_questions"
    const val SCORE = "correct_answers"


    fun getQuestions() : MutableList<Question> {
        val questions = mutableListOf<Question>()

        val quest1 = Question(
            id = 1,
            question = "What country does this flag belong to?",
            image = R.drawable.flag_of_italy,
            optionOne = "Italy",
            optionTwo = "India",
            optionThree = "Iran",
            optionFour = "Ireland",
            correctOption = 1
        )
        val quest2 = Question(
            id = 2,
            question = "What country does this flag belong to?",
            image = R.drawable.flag_of_argentina,
            optionOne = "Armenia",
            optionTwo = "Argentina",
            optionThree = "Austria",
            optionFour = "Australia",
            correctOption = 2
        )
        val quest3 = Question(
            id = 3,
            question = "What country does this flag belong to?",
            image = R.drawable.flag_of_brazil,
            optionOne = "Belarus",
            optionTwo = "Belgium",
            optionThree = "Bangladesh",
            optionFour = "Brazil",
            correctOption = 4
        )
        val quest4 = Question(
            id = 4,
            question = "What country does this flag belong to?",
            image = R.drawable.flag_of_france,
            optionOne = "Finland",
            optionTwo = "Fiji",
            optionThree = "France",
            optionFour = "None of the options",
            correctOption = 3
        )
        val quest5 = Question(
            id = 5,
            question = "What country does this flag belong to?",
            image = R.drawable.flag_of_finland,
            optionOne = "Finland",
            optionTwo = "Fiji",
            optionThree = "France",
            optionFour = "None of the options",
            correctOption = 1
        )
        val quest6 = Question(
            id = 6,
            question = "What country does this flag belong to?",
            image = R.drawable.flag_germany,
            optionOne = "Gambia",
            optionTwo = "Germany",
            optionThree = "Georgia",
            optionFour = "Greece",
            correctOption = 2
        )
        val quest7 = Question(
            id = 7,
            question = "What country does this flag belong to?",
            image = R.drawable.flag_of_nigeria,
            optionOne = "Netherlands",
            optionTwo = "Nicaragua",
            optionThree = "Nigeria",
            optionFour = "Nepal",
            correctOption = 3
        )
        val quest8 = Question(
            id = 8,
            question = "What country does this flag belong to?",
            image = R.drawable.flag_of_romania,
            optionOne = "Russia",
            optionTwo = "Rwanda",
            optionThree = "None of the otions",
            optionFour = "Romania",
            correctOption = 4
        )
        val quest9 = Question(
            id = 9,
            question = "What country does this flag belong to?",
            image = R.drawable.flag_of_spain,
            optionOne = "Serbia",
            optionTwo = "Spain",
            optionThree = "Saudi Arabia",
            optionFour = "Slovenia",
            correctOption = 2
        )
        val quest10 = Question(
            id = 10,
            question = "What country does this flag belong to?",
            image = R.drawable.flag_of_haiti,
            optionOne = "Honduras",
            optionTwo = "Hungary",
            optionThree = "Haiti",
            optionFour = "None of the options",
            correctOption = 3
        )
        questions.add(quest1)
        questions.add(quest2)
        questions.add(quest3)
        questions.add(quest4)
        questions.add(quest5)
        questions.add(quest6)
        questions.add(quest7)
        questions.add(quest8)
        questions.add(quest9)
        questions.add(quest10)
        return questions
    }
}