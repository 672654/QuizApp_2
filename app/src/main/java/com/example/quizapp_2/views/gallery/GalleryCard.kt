package com.example.quizapp_2.views.gallery

import android.R.attr.contentDescription
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.quizapp_2.data.QuizItem

@Composable
fun GalleryCard(
    quizItem: QuizItem,
    onDeleteButtonClick: (QuizItem) -> Unit
) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        if (!quizItem.imageUri.isNullOrBlank() || quizItem.imageRes != null) {
            AsyncImage(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(200.dp),
                    model = quizItem.imageUri ?: quizItem.imageRes,

                contentDescription = quizItem.answer,
            )
        }
        Text(
            modifier = Modifier
                .padding(8.dp)
                .align(alignment = Alignment.CenterHorizontally)
                ,
            text = quizItem.answer,
            fontSize = 24.sp
        )
        IconButton(
            modifier = Modifier
                .padding(4.dp)
                .align(alignment = Alignment.CenterHorizontally),
            onClick = { onDeleteButtonClick(quizItem) }
        ) {
            Icon(
                modifier = Modifier
                    .size(36.dp),
                contentDescription = "Delete",
                imageVector =Icons.Default.Delete
            )
        }
    }
}