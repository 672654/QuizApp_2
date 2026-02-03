package com.example.quizapp_2.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import android.net.Uri
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.quizapp_2.controller.galleryController.GalleryController
import com.example.quizapp_2.data.QuizItem
import com.example.quizapp_2.data.QuizItems
import com.example.quizapp_2.views.gallery.GalleryScreen


class GalleryActivity: ComponentActivity() {


    //Declare a non-nullable var to be assigned in onCreate()
    private lateinit var controller: GalleryController


    /**
     *
     */
    private val image = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            uri.let {
                val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
                contentResolver.takePersistableUriPermission(it, flag)
            }
            controller.addImageUri(uri)
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Get reference to application() created in AndroidManifest.xml
        val appData = application as QuizItems

        //Create controller with reference to QuizItems
        controller = GalleryController(appData)

        setContent{
            GalleryScreen(
                controller.quizControllerItems,
                onAddButtonClick = {
                    image.launch("image/*") //åpne galleri på telefonen.
                },
                onSortButtonClick = { boolean ->
                    controller.sortItems(boolean)
                },
                onDeleteButtonClick = {
                    controller.deleteImage(it)
                }
            )
            if (controller.newImageUri != null) {
                AddImage()
            }
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AddImage(){

        var name by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest ={controller.dismissNewImage()},
            confirmButton = {
                TextButton(
                    onClick = {
                        controller.addItem(name)
                    }
                ) {
                    Text("Add")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        controller.dismissNewImage()
                    }
                ) {
                    Text("Cancel")
                }
            },
            title = { Text("name of image")},
            text = {
                TextField(
                    value = name,
                    onValueChange = {name = it}
                )
            }

        )
    }


}
