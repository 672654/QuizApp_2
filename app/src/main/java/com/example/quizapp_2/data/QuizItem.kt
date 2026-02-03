package com.example.quizapp_2.data

import androidx.annotation.DrawableRes

data class QuizItem(


    val answer: String,
    val imageUri: String? = null,
    @DrawableRes val imageRes: Int? = null

)
