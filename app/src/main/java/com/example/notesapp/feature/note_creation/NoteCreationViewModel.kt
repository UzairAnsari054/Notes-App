package com.example.notesapp.feature.note_creation

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
class NoteCreationViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    val loader = MutableLiveData(false)
    val title = mutableStateOf("")
    val description = mutableStateOf("")

    suspend fun addNote(note: NoteModel): Result<Boolean> =
        withContext(Dispatchers.IO) {
            loader.postValue(true)
            try {
                repository.insertNote(note)
                    .onEach { loader.postValue(false) }
                    .last()
            } catch (e: Exception) {
                loader.postValue(false)
                Result.failure(e)
            }
        }
}