package com.example.notes_app

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteViewModel : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    var title by mutableStateOf("")
    var content by mutableStateOf("")

    init {
        fetchNotes()
    }

    fun fetchNotes() {
        viewModelScope.launch {
            try {
                _notes.value = RetrofitClient.api.getNotes()
            } catch (e: Exception) {
                Log.e("NoteViewModel", "Error: ${e.message}")
            }
        }
    }

    fun addNote() {
        viewModelScope.launch {
            try {
                val newNote = Note(title = title, content = content)
                RetrofitClient.api.addNote(newNote)
                fetchNotes()
                title = ""
                content = ""
            } catch (e: Exception) {
                Log.e("NoteViewModel", "Add Error: ${e.message}")
            }
        }
    }
}
