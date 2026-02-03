package com.example.quizapp_2.controller.quizController

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.quizapp_2.data.QuizItem
import com.example.quizapp_2.data.QuizItems
import kotlin.random.Random

class QuizController(private val quizData: QuizItems) {

    private var quizControllerItems = quizData.quizItems

    var currentQuestion by mutableStateOf<QuizItem?>(null)
        private set

    var optionsOfAnswers by mutableStateOf<List<String>>(emptyList())
        private set

    private var availableQuestions = mutableListOf<QuizItem>()

    var isGameFinished = mutableStateOf<Boolean>(false)

    var points by mutableStateOf<Int>(0)

    var maximumPoints by mutableStateOf<Int>(quizControllerItems.size)



    init {
        availableQuestions.addAll(quizControllerItems)
        nextQuestion()
    }

    fun nextQuestion(){
        if (availableQuestions.isEmpty()){
            isGameFinished.value = true
        } else {


            val next = availableQuestions.random()
            currentQuestion = next

            availableQuestions.remove(next)

            generateAllAnswers()
        }
    }

    fun generateAllAnswers(){
        val current = currentQuestion ?: return

        optionsOfAnswers = quizControllerItems
            .filter { it != current }
            .map { it.answer }
            .shuffled()
            .take(2)

        optionsOfAnswers += current.answer
        optionsOfAnswers = optionsOfAnswers.shuffled()
    }

    fun checkAnswer(answer: String) {
        val current = currentQuestion ?: return

        if (answer == current.answer) {
           points = points.inc()
        }
        nextQuestion()

    }

    fun restartQuiz() {
        isGameFinished.value = false
        points = 0
        availableQuestions.clear()
        availableQuestions.addAll(quizControllerItems)
        nextQuestion()
    }

}
