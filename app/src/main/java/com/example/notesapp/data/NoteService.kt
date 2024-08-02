package com.example.notesapp.data

import com.example.notesapp.data.local.NoteDao
import com.example.notesapp.data.model.NoteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteService @Inject constructor(
    private val dao: NoteDao
) {
    suspend fun getNotes(sortBy: String, orderBy: String): Flow<Result<List<NoteModel>>> =
        dao.getNoteList(sortBy, orderBy).map {
            Result.success(it)
        }.catch {
            emit(Result.failure(RuntimeException("Failed to get notes")))
        }

    suspend fun getNoteById(id: Int): Flow<Result<NoteModel>> =
        dao.getNoteById(id).map {
            Result.success(it)
        }.catch {
            emit(Result.failure(RuntimeException("Failed to get the specific note")))
        }

    suspend fun insertNote(note: NoteModel): Flow<Result<Boolean>> =
        flow {
            val result = dao.saveNoteWithTimestamp(note) != -1L
            emit(Result.success(result))
        }.catch {
            emit(Result.failure(RuntimeException("Failed to save note")))
        }

    suspend fun updateNote(note: NoteModel): Flow<Result<Boolean>> =
        flow {
            val result = dao.updateNoteWithTimeStamp(note) >= 1
            emit(Result.success(result))
        }.catch {
            emit(Result.failure(RuntimeException("Failed to update note")))
        }

    suspend fun deleteNotes(vararg notes: NoteModel): Flow<Result<Boolean>> =
        flow {
            val result = dao.deleteNote(*notes) == notes.size
            emit(Result.success(result))
        }.catch {
            emit(Result.failure(RuntimeException("Failed to delete notes")))
        }
}
