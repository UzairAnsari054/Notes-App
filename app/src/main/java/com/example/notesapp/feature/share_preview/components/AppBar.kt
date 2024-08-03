package com.example.notesapp.feature.share_preview.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Help
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
fun SharePreviewAppBar(
    onBackPressed: () -> Unit,
    onHelpPressed: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {

    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.share_preview),
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
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                description = stringResource(R.string.back_nav_icon),
                modifier = Modifier.clickable { onBackPressed() })
        },
        actions = {
            NavBarIcons(
                imageVector = Icons.AutoMirrored.Filled.Help,
                description = stringResource(R.string.menu_nav_icon),
                modifier = Modifier.clickable { onHelpPressed() })
        },
        scrollBehavior = scrollBehavior,
    )
}