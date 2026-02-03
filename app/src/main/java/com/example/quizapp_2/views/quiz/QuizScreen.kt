package com.example.quizapp_2.views.quiz

import android.widget.GridView
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.helper.widget.Grid
import coil.compose.AsyncImage
import com.example.quizapp_2.data.QuizItem
import org.intellij.lang.annotations.JdkConstants


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen (
    question : QuizItem?,
    allAnswers: List<String>,
    onAnswerClick: (String) -> Unit,
    onExitClick: () -> Unit,
    points: Int,
    maxPoints: Int
){

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Quiz") },
                actions = {
                    Text(
                        text = "Score: $points / $maxPoints",
                        modifier = Modifier
                            .padding(32.dp)

                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onExitClick() }
            ){
                Text(text = "EXIT")
            }
         },
        floatingActionButtonPosition = FabPosition.End

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            if (question != null) {
                QuizCard(
                    quizItem = question,
                    allAnswers = allAnswers,
                    onAnswerClick = onAnswerClick
                )
            }
        }


    }

}

@Composable
fun QuizCard(
    quizItem: QuizItem,
    allAnswers: List<String>,
    onAnswerClick : (String) -> Unit,
){

    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var hasAnswered by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
    ) {
        AsyncImage(
            model = quizItem.imageUri ?: quizItem.imageRes,
            contentDescription = "",
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(200.dp)
        )
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            for (answer in allAnswers){
                Button(
                    enabled = !hasAnswered,
                    onClick = {
                        selectedAnswer = answer
                        hasAnswered = true
                              },
                    modifier = Modifier
                        .padding(2.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = answer,
                        fontWeight = FontWeight(10),
                        modifier = Modifier
                            .padding(4.dp)
                    )
                }
            }
            if (hasAnswered) {
                Column(
                    modifier = Modifier
                        .padding(top = 32.dp, bottom = 32.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = " Correct answer is: ${quizItem.answer}",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(4.dp)
                            .align(alignment = Alignment.CenterHorizontally)

                    )
                    Button(
                        modifier = Modifier
                            .padding(4.dp)
                            .align(alignment = Alignment.CenterHorizontally)
                            .fillMaxWidth(),
                        onClick = {
                            onAnswerClick(selectedAnswer ?: "")
                            hasAnswered = false
                                  },
                    ) {
                        Text(text = "Next Question")
                    }
                }


            }


        }
    }

}

