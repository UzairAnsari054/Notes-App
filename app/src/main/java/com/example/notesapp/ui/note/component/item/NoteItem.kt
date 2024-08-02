package com.example.notesapp.ui.note.component.item

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.common.DateUtils
import com.example.notesapp.data.model.NoteModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteItem(
    isMarking: Boolean,
    isMarked: Boolean,
    data: NoteModel,
    onClick: () -> Unit,
    onCheckClick: () -> Unit,
    onLongClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .combinedClickable(
                onClick = if (isMarking) onCheckClick else onClick,
                onLongClick = { onLongClick() }
            )
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = if (isMarked) 8.dp else 0.dp)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = data.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = data.note,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    modifier = Modifier.padding(bottom = 1.dp)
                )
                // Panga
                Text(
                    text = DateUtils.convertDateString(
                        "EEEE, MMMM d h:mm a",
                        data.createdAt.toString()
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    ),
                )
            }
        }
    }
}