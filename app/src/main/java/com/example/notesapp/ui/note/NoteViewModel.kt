package com.example.notesapp.ui.note

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.example.notesapp.data.NoteRepository
import com.example.notesapp.data.model.NoteModel
import com.example.notesapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    val sortAndOrderData: MutableLiveData<Pair<String, String>> =
        MutableLiveData(Pair(Constants.CREATED_AT, Constants.DESCENDING))

    fun sortAndOrder(sortBy: String, orderBy: String) {
        sortAndOrderData.value = Pair(sortBy, orderBy)
    }

    val noteList: LiveData<Result<List<NoteModel>>> = sortAndOrderData.switchMap {
        liveData {
            loader.postValue(true)
            try {
                emitSource(repository.getNotes(it.first, it.second)
                    .onEach { loader.postValue(false) }
                    .asLiveData())
            } catch (e: Exception) {
                loader.postValue(false)
                emit(Result.failure(e))
            }
        }
    }


    val isSearching = mutableStateOf(false)
    val searchTitleText = mutableStateOf("")

    val isMarking = mutableStateOf(false)
    val markedNoteList = mutableStateListOf<NoteModel>()

    fun markAllNotes(notes: List<NoteModel>) {
        markedNoteList.addAll(notes.minus(markedNoteList.toSet()))
    }

    fun unMarkAllNotes() {
        markedNoteList.clear()
    }

    fun addToMarkedNoteList(note: NoteModel) {
        if (markedNoteList.contains(note)) {
            markedNoteList.remove(note)
        } else {
            markedNoteList.add(note)
        }
    }

    fun closeSearchEvent() {
        isSearching.value = false
        searchTitleText.value = ""
    }

    fun closeMarkEvent() {
        isMarking.value = false
        markedNoteList.clear()
    }

    suspend fun deleteNotes(vararg note: NoteModel): Result<Boolean> =
        withContext(Dispatchers.IO) {
            loader.postValue(true)
            try {
                val noteList: List<NoteModel> =
                    if (note.isEmpty()) markedNoteList else note.toList()
                repository.deleteNote(*noteList.toTypedArray())
                    .onEach { loader.postValue(false) }
                    .last()
            } catch (e: Exception) {
                loader.postValue(false)
                Result.failure(e)
            }
        }
}