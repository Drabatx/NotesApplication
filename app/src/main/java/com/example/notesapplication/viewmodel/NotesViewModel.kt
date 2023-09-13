package com.example.notesapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapplication.model.Note
import com.example.notesapplication.model.NoteDao
import com.example.notesapplication.model.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val noteDao: NoteDao): ViewModel() {
    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> get() = _notes

    private val _selectNote = MutableLiveData<Note>()
    val selectNote: LiveData<Note> get() = _selectNote

    fun findAllNotes(){
        noteDao.getAllNotes().observeForever {
            _notes.value = it
        }
    }

    fun findByIdNote(id: Long){
        noteDao.getNoteById(id).observeForever {
            _selectNote.value= it
        }
    }
    fun insertOrUpdate(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.insertOrUpdate(note)
        }
    }

    fun deleteNoteById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.deleteNoteById(id)
        }
    }

    fun setSelectedNote(note: Note){
        _selectNote.value = note
    }

    init {
    }
}