package com.example.notesapplication.model

import androidx.lifecycle.LiveData
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {
//    val notes: LiveData<List<Note>> = noteDao.getAllNotes()
//
//    suspend fun getNoteById(id: Long): Note? {
//        return noteDao.getNoteById(id)
//    }
//
//    suspend fun addOrupdateNote(note: Note) {
//        return noteDao.insertOrUpdate(note)
//    }
//
//    suspend fun deleteNoteById(id: Long) {
//        noteDao.deleteNoteById(id)
//    }

}