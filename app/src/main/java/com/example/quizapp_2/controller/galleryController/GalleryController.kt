package com.example.quizapp_2.controller.galleryController

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.quizapp_2.data.QuizItem
import com.example.quizapp_2.data.QuizItems

class GalleryController(private val quizData: QuizItems) {


    //Referer til QuizItems.quizItems (pointer)
    var quizControllerItems = quizData.quizItems

    var newImageUri by mutableStateOf<Uri?>(null)
        private set


    fun addImageUri(imageUri: Uri?){
        newImageUri = imageUri
    }

    fun dismissNewImage(){
        newImageUri = null
    }

    fun addItem(name:String){
        val answer = name.replaceFirstChar { char -> char.uppercase() }
        quizData.addItem(imageUri = newImageUri, answer = answer)
        newImageUri = null
    }

    fun sortItems(sortAsc: Boolean){
        if (sortAsc){
            quizControllerItems.sortBy { it.answer }
        } else {
            quizControllerItems.sortByDescending { it.answer }
        }

    }

    fun deleteImage(quizItem: QuizItem){
        quizData.quizItems.remove(quizItem)
    }
}
