package com.example.notesapp.ui.note

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notesapp.R
import com.example.notesapp.common.LoadingDialog
import com.example.notesapp.common.TextDialog
import com.example.notesapp.data.model.NoteModel
import com.example.notesapp.ui.note.component.drawer.NavDrawer
import com.example.notesapp.ui.note.component.item.NoteItem
import com.example.notesapp.ui.note.component.navbar.TopNavAppBar
import com.example.notesapp.ui.note.component.sheet.FilterSheet
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    viewModel: NoteViewModel = hiltViewModel()
) {

    val noteListState = viewModel.noteList.observeAsState()

    val loadingState = viewModel.loader.observeAsState()
    val loadingDialog = remember { mutableStateOf(false) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    val openBottomSheet = rememberSaveable { mutableStateOf(false) }
    val skipPartiallyExpanded = remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded.value
    )

    LaunchedEffect(key1 = noteListState.value) {
        noteListState.value?.onFailure {
            scope.launch {
                snackBarHostState.showSnackbar(
                    message = it.message ?: "",
                    withDismissAction = true
                )
            }
        }
    }

    LaunchedEffect(key1 = loadingState.value) {
        loadingDialog.value = (loadingDialog.value == true)
    }

    // Panga
    val filteredNoteListState = remember { mutableStateOf<List<NoteModel>>(listOf()) }
    LaunchedEffect(key1 = noteListState.value, viewModel.searchTitleText.value) {
        filteredNoteListState.value = noteListState.value?.getOrNull()?.filter { note ->
            note.title.contains(viewModel.searchTitleText.value, ignoreCase = true)
        } ?: listOf()
    }

    val deleteDialog = remember { mutableStateOf(false) }
    val deleteMessage = stringResource(id = R.string.note_is_successfully_deleted)

    fun deleteNoteList() {
        scope.launch {
            viewModel.deleteNotes()
                .onSuccess {
                    deleteDialog.value = false
                    viewModel.unMarkAllNotes()

                    snackBarHostState.showSnackbar(
//                        message = deleteMessage,
                        message = it.toString(),
                        withDismissAction = true
                    )
                }
        }
    }

    NavDrawer(
        drawerState = drawerState,
        onError = {
            scope.launch {
                snackBarHostState.showSnackbar(
                    message = it,
                    withDismissAction = true
                )
            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopNavAppBar(
                        isMarking = viewModel.isMarking.value,
                        markedNoteListSize = viewModel.markedNoteList.size,
                        selectAllCallBack = {
                            noteListState.value?.getOrNull()?.let {
                                viewModel.markAllNotes(it)
                            }
//                            viewModel.noteList.value?.getOrNull()?.let {
//                                viewModel.markAllNotes(it)
//                            }
                        },
                        unSelectAllCallBack = { viewModel.unMarkAllNotes() },
                        isSearching = viewModel.isSearching.value,
                        searchedTitle = viewModel.searchTitleText.value,
                        onSearchValueChange = { viewModel.searchTitleText.value = it },
                        closeMarkingCallBack = { viewModel.closeMarkEvent() },
                        toggleDrawerCallBack = {
                            scope.launch {
                                drawerState.open()
                            }
                        },
                        deleteCallBack = { deleteDialog.value = true },
                        closeSearchCallBack = { viewModel.closeSearchEvent() },
                        searchCallBack = {
                            viewModel.isSearching.value = true
                            viewModel.searchTitleText.value = ""
                        },
                        sortCallBack = { openBottomSheet.value = true }
                    )
                },
                snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
                floatingActionButton = {},
                content = { contentPadding ->
                    Box(modifier = Modifier.padding(contentPadding)) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            contentPadding = PaddingValues(
                                top = 24.dp,
                                bottom = 96.dp,
                                start = 16.dp,
                                end = 16.dp
                            )
                        ) {
                            items(items = filteredNoteListState.value) {
                                NoteItem(
                                    isMarking = viewModel.isMarking.value,
                                    isMarked = it in viewModel.markedNoteList,
                                    data = it,
                                    onClick = {
                                        if (viewModel.isMarking.value) {
                                            viewModel.addToMarkedNoteList(it)
                                        } else {
                                            viewModel.closeMarkEvent()
                                            viewModel.closeSearchEvent()

                                            // Panga (Navigation)
                                        }
                                    },
                                    onCheckClick = { viewModel.addToMarkedNoteList(it) },
                                    onLongClick = {
                                        if (!viewModel.isMarking.value) {
                                            viewModel.isMarking.value = true
                                        }
                                        viewModel.addToMarkedNoteList(it)
                                    }
                                )
                            }
                        }
                    }
                }
            )
        })

    TextDialog(
        isOpened = deleteDialog.value,
        onDismissCallBack = { deleteDialog.value = false },
        onConfirmCallBack = { deleteNoteList() }
    )

    //Panga
    LoadingDialog(
        isOpened = loadingDialog.value,
        onDismissCallback = { loadingDialog.value = false }
    )

    FilterSheet(
        isOpened = openBottomSheet,
        sheetState = bottomSheetState,
        onDismiss = { openBottomSheet.value = false },
        onFilter = { sortBy, orderBy ->
            viewModel.sortAndOrder(sortBy, orderBy)
        }
    )

}