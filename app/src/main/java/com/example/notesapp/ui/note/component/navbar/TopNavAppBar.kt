package com.example.notesapp.ui.note.component.navbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavAppBar(
    isMarking: Boolean,
    markedNoteListSize: Int,
    selectAllCallBack: () -> Unit,
    unSelectAllCallBack: () -> Unit,
    isSearching: Boolean,
    searchedTitle: String,
    onSearchValueChange: (String) -> Unit,
    closeMarkingCallBack: () -> Unit,
    toggleDrawerCallBack: () -> Unit,
    deleteCallBack: () -> Unit,
    closeSearchCallBack: () -> Unit,
    searchCallBack: () -> Unit,
    sortCallBack: () -> Unit,
) {

    val expanded = remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            if (isMarking) {
                Row(
                    modifier = Modifier.clickable { expanded.value = true },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = markedNoteListSize.toString(),
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.selected_text),
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = stringResource(id = R.string.dropdown_nav_icon),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false },
                    content = {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = stringResource(id = R.string.select_all),
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                )
                            },
                            onClick = {
                                expanded.value = false
                                selectAllCallBack()
                            }
                        )
                        HorizontalDivider()
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = stringResource(id = R.string.unselect_all),
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                )
                            },
                            onClick = {
                                expanded.value = false
                                unSelectAllCallBack()
                            }
                        )
                    }
                )
            } else if (isSearching) {
                TextField(
                    value = searchedTitle,
                    onValueChange = { onSearchValueChange(it) },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.title_textField_input),
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.primary
                    ),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            } else {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        navigationIcon = {
            LeadingIcons(
                isMarking = isMarking,
                closeMarkingCallBack = closeMarkingCallBack,
                toggleDrawerCallBack = toggleDrawerCallBack
            )
        },
        actions = {
            TrailingIcon(
                isMarking = isMarking,
                markedNoteListSize = markedNoteListSize,
                deleteCallBack = deleteCallBack,
                isSearching = isSearching,
                closeSearchCallBack = closeSearchCallBack,
                searchCallBack = searchCallBack,
                sortCallBack = sortCallBack
            )
        }
    )
}

@Composable
fun LeadingIcons(
    isMarking: Boolean,
    closeMarkingCallBack: () -> Unit,
    toggleDrawerCallBack: () -> Unit
) {
    if (isMarking) {
        NavBarIcons(
            imageVector = Icons.Filled.Close,
            description = stringResource(id = R.string.close_nav_icon),
            modifier = Modifier.clickable { closeMarkingCallBack() }
        )
    } else {
        NavBarIcons(
            imageVector = Icons.Filled.Menu,
            description = stringResource(id = R.string.menu_nav_icon),
            modifier = Modifier.clickable { toggleDrawerCallBack() }
        )
    }
}

@Composable
fun TrailingIcon(
    isMarking: Boolean,
    markedNoteListSize: Int,
    deleteCallBack: () -> Unit,
    isSearching: Boolean,
    closeSearchCallBack: () -> Unit,
    searchCallBack: () -> Unit,
    sortCallBack: () -> Unit,

    ) {
    if (isMarking) {
        NavBarIcons(
            imageVector = Icons.Filled.Delete,
            description = stringResource(id = R.string.delete_nav_icon),
            tint = if (markedNoteListSize > 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                alpha = 0.6f
            ),
            modifier = Modifier.clickable { deleteCallBack() }
        )
    } else if (isSearching) {
        NavBarIcons(
            imageVector = Icons.Filled.Close,
            description = stringResource(id = R.string.close_nav_icon),
            modifier = Modifier.clickable { closeSearchCallBack() }
        )
    } else {
        NavBarIcons(
            imageVector = Icons.Filled.Search,
            description = stringResource(id = R.string.search_nav_icon),
            modifier = Modifier.clickable { searchCallBack() }
        )
        NavBarIcons(
            imageVector = Icons.AutoMirrored.Filled.Sort,
            description = stringResource(id = R.string.sort_nav_icon),
            modifier = Modifier.clickable { sortCallBack() }
        )
    }
}

@Composable
fun NavBarIcons(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    description: String,
    tint: Color = MaterialTheme.colorScheme.primary,
) {
    Icon(
        imageVector = imageVector,
        contentDescription = description,
        tint = tint,
        modifier = modifier
    )
}