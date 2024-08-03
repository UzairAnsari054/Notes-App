package com.example.notesapp.feature.note_creation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
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
fun NoteCreationAppBar(
    descriptionTextLength: Int,
    onBackPressed: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        title = {
            Text(
                text = if (descriptionTextLength > 0) "$descriptionTextLength" else stringResource(
                    id = R.string.add_new_note
                ),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                ),
                modifier = Modifier.padding(start = 4.dp),
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        navigationIcon = {
            NavBarIcons(
                imageVector = Icons.AutoMirrored.Filled.Sort,
                description = stringResource(id = R.string.sort_nav_icon),
                modifier = Modifier.clickable { onBackPressed() }
            )
        },
        scrollBehavior = scrollBehavior
    )
}