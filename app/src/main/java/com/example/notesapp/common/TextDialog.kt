package com.example.notesapp.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.R

@Composable
fun TextDialog(
    isOpened: Boolean,
    title: String = stringResource(id = R.string.warning_title),
    description: String = stringResource(id = R.string.delete_confirmation_text),
    onDismissCallBack: () -> Unit,
    onConfirmCallBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isOpened) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
            },
            text = {
                Text(
                    text = description,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
            },
            onDismissRequest = { onDismissCallBack() },
            dismissButton = {
                TextButton(
                    onClick = { onDismissCallBack() },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.dismiss),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = { onConfirmCallBack() },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.confirm),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            },
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
        )
    }
}