package com.example.quizapp_2.views.gallery

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quizapp_2.data.QuizItem
import com.example.quizapp_2.data.QuizItems


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(
    quizItems: List<QuizItem>,
    onAddButtonClick: () -> Unit,
    onSortButtonClick: (Boolean) -> Unit,
    onDeleteButtonClick: (QuizItem) -> Unit
){

    //sort
    var isSortedAsc by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Gallery") },
                actions = {
                    TextButton(
                        onClick = {
                            if (!isSortedAsc){
                                onSortButtonClick(true)
                                isSortedAsc = true
                            } else{
                                onSortButtonClick(false)
                                isSortedAsc = false
                            }
                        }
                    ){
                        Text( if (isSortedAsc) "Sort Desc" else "Sort Asc")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddButtonClick
            ){
                Text(text = "ADD")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        LazyColumn (
            modifier = Modifier
                .padding(paddingValues),
            contentPadding = PaddingValues(8.dp)
        ){
            items(quizItems) { quizItem ->
                GalleryCard(
                    quizItem,
                    onDeleteButtonClick
                )
            }
        }
    }
}
