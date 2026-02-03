package com.example.quizapp_2.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.quizapp_2.controller.quizController.QuizController
import com.example.quizapp_2.data.QuizItems
import com.example.quizapp_2.views.quiz.EndScreen
import com.example.quizapp_2.views.quiz.QuizScreen

class QuizActivity: ComponentActivity() {

    private lateinit var controller: QuizController

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val appData = application as QuizItems

        controller = QuizController(appData)

        setContent {
            QuizScreen(
                question = controller.currentQuestion,
                allAnswers = controller.optionsOfAnswers,
                onAnswerClick = {
                    controller.checkAnswer(it)
                },
                onExitClick = {exitQuiz()},
                points = controller.points,
                maxPoints = controller.maximumPoints
            )
            if (controller.isGameFinished.value){
                EndScreen(
                    onRestartClick = {
                        controller.restartQuiz()
                    },
                    onExitClick = {
                        exitQuiz()
                    },
                    points = controller.points,
                    maxPoints = controller.maximumPoints
                )
            }


        }


    }

    fun exitQuiz(){
        finish()
    }
}