package com.example.notesapp.feature.note_detail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.R
import com.example.notesapp.feature.note.component.navbar.NavBarIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailAppBar(
    isEditing: Boolean,
    descriptionTextLength: Int,
    onBackPressed: () -> Unit,
    onClosePressed: () -> Unit,
    onDeletePressed: () -> Unit,
    onSharePressed: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {

    val isMenuExpanded = remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = if (isEditing) "$descriptionTextLength" else stringResource(id = R.string.note_detail),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                ),
                modifier = Modifier.padding(start = 4.dp),
            )
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        navigationIcon = {
            if (isEditing) {
                NavBarIcons(
                    imageVector = Icons.Filled.Close,
                    description = stringResource(R.string.back_nav_icon),
                    modifier = Modifier.clickable { onClosePressed() }
                )
            } else {
                NavBarIcons(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    description = stringResource(R.string.back_nav_icon),
                    modifier = Modifier.clickable { onBackPressed() }
                )
            }
        },
        actions = {
            NavBarIcons(
                imageVector = Icons.Filled.Share,
                description = stringResource(R.string.share_nav_icon),
                modifier = Modifier.clickable { onSharePressed() }
            )
            NavBarIcons(
                imageVector = Icons.Filled.MoreVert,
                description = stringResource(R.string.menu_nav_icon),
                modifier = Modifier.clickable { isMenuExpanded.value = true }
            )
            DropdownMenu(
                expanded = isMenuExpanded.value,
                onDismissRequest = { isMenuExpanded.value = false }) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = stringResource(id = R.string.delete_note),
                            fontSize = 16.sp
                        )
                    },
                    onClick = {
                        onDeletePressed()
                        isMenuExpanded.value = false
                    },
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )
}