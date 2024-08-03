package com.example.notesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notesapp.data.model.NoteModel
import com.example.notesapp.feature.note.NotesScreen
import com.example.notesapp.feature.note_creation.NoteCreationScreen
import com.example.notesapp.feature.note_detail.NoteDetailScreen
import com.example.notesapp.feature.share_preview.SharePreviewPage

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Route.NotesScreen.routeName
    ) {
        composable(Route.NotesScreen.routeName) {
            NotesScreen(navController = navHostController)
        }
        composable(
            route = "${Route.NoteDetailScreen.routeName}/{noteId}",
            arguments = listOf(navArgument("noteId") {
                type = NavType.StringType
                defaultValue = ""
            })
        ) {
            NoteDetailScreen(
                navController = navHostController,
                id = it.arguments?.getString("noteId") ?: "0")
        }
        composable(Route.NoteCreationScreen.routeName) {
            NoteCreationScreen(navController = navHostController)
        }
        composable(
            route = "${Route.SharePreviewScreen.routeName}/{noteData}",
            arguments = listOf(navArgument("noteData") {
                type = NoteModelParamType()
            })
        ) {
            val note = it.arguments?.getParcelable<NoteModel>("noteData")
            SharePreviewPage(navHostController = navHostController, note = note)
        }
    }
}