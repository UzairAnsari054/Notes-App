package com.example.notesapp.data

import com.example.notesapp.data.model.NoteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteService: NoteService
) {
    suspend fun getNotes(sortBy: String, orderBy: String): Flow<Result<List<NoteModel>>> =
        noteService.getNotes(sortBy, orderBy).map {
            if (it.isSuccess) {
                Result.success(it.getOrNull() ?: listOf())
            } else {
                Result.failure(it.exceptionOrNull()!!)
            }
        }

    suspend fun getNoteById(id: Int): Flow<Result<NoteModel>> =
        noteService.getNoteById(id).map {
            if (it.isSuccess) {
                Result.success(it.getOrNull() ?: NoteModel(1, "", ""))
            } else {
                Result.failure(it.exceptionOrNull()!!)
            }
        }

    suspend fun insertNote(note: NoteModel): Flow<Result<Boolean>> =
        noteService.insertNote(note).map {
            if (it.isSuccess) {
                Result.success(it.getOrNull() ?: false)
            } else {
                Result.failure(it.exceptionOrNull()!!)
            }
        }

    suspend fun updateNote(note: NoteModel): Flow<Result<Boolean>> =
        noteService.updateNote(note).map {
            if (it.isSuccess) {
                Result.success(it.getOrNull() ?: false)
            } else {
                Result.failure(it.exceptionOrNull()!!)
            }
        }

    suspend fun deleteNote(vararg note: NoteModel): Flow<Result<Boolean>> =
        noteService.deleteNotes(*note).map {
            if (it.isSuccess) {
                Result.success(it.getOrNull() ?: false)
            } else {
                Result.failure(it.exceptionOrNull()!!)
            }
        }
}