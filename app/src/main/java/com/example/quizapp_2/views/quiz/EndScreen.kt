package com.example.quizapp_2.views.quiz

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EndScreen(
    onRestartClick: () -> Unit,
    onExitClick: () -> Unit,
    points: Int,
    maxPoints: Int
){
    AlertDialog(
        onDismissRequest = { onExitClick() },
        confirmButton = {
            TextButton(
                onClick = onRestartClick
            ) {
                Text(text = "Restart")
            } },
        dismissButton = {
            TextButton(
                onClick = onExitClick
            ) {
                Text(text = "EXIT")
            }
        },
        title = { Text(text = "Game Over\nPoints: $points / $maxPoints") },
        text = { Text(text = "Do you want to restart the game?") }
    )

}