package com.example.notesapp.navigation

sealed class Route(val routeName: String) {
    object NotesScreen: Route(routeName = "notes_Screen")
    object NoteDetailScreen: Route(routeName = "note_detail_Screen")
    object NoteCreationScreen: Route(routeName = "note_creation_Screen")
    object SharePreviewScreen: Route(routeName = "share_preview_Screen")
}