package com.example.quizapp_2.data

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import com.example.quizapp_2.R

class QuizItems : Application() {

    var quizItems = mutableStateListOf(

        QuizItem(
            imageRes = R.drawable.lion,
            answer = "Lion"
        ),
        QuizItem(
            imageRes = R.drawable.panda,
            answer = "Panda"
        ),
        QuizItem(
            imageRes = R.drawable.elephant,
            answer = "Elephant"
        )
        ,
        QuizItem(
            imageRes = R.drawable.zebra,
            answer = "Zebra"
        )
        ,
        QuizItem(
            imageRes = R.drawable.wolf,
            answer = "Wolf"
        )


    )

    fun addItem(imageUri: Uri?, answer:String){
        quizItems.add(QuizItem(imageUri = imageUri?.toString(), answer = answer))
    }
}
