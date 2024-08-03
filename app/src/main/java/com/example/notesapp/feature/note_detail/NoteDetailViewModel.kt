package com.example.notesapp.feature.note_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.data.NoteRepository
import com.example.notesapp.data.model.NoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    val loader = MutableLiveData(false)
    val noteDetails: MutableLiveData<Result<NoteModel>> = MutableLiveData()

    var isEditing: MutableState<Boolean> = mutableStateOf(false)

    var title: MutableState<String> = mutableStateOf("")
    var description: MutableState<String> = mutableStateOf("")

    suspend fun getNoteById(id: Int): Result<Boolean> =
        withContext(Dispatchers.IO) {
            loader.postValue(true)
            try {
                repository.getNoteById(id)
                    .onEach { loader.postValue(false) }
                    .collect { noteDetails.postValue(it) }
                Result.success(true)
            } catch (e: Exception) {
                loader.postValue(false)
                Result.failure(e)
            }
        }

    suspend fun updateNote(note: NoteModel): Result<Boolean> =
        withContext(Dispatchers.IO) {
            loader.postValue(true)
            try {
                repository.updateNote(note)
                    .onEach { loader.postValue(false) }
                    .last()
            } catch (e: Exception) {
                loader.postValue(false)
                Result.failure(e)
            }
        }

    suspend fun deleteNotes(vararg note: NoteModel): Result<Boolean> =
        withContext(Dispatchers.IO) {
            loader.postValue(true)
            try {
                repository.deleteNote(*note)
                    .onEach { loader.postValue(false) }
                    .last()
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}