package com.ashraf.notemeappkotlin.data.repository

import androidx.lifecycle.LiveData
import com.ashraf.notemeappkotlin.data.dao.NotesDao
import com.ashraf.notemeappkotlin.data.model.Note

class NoteRepository(private val notesDao: NotesDao) {


    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()


    fun statuswiseNotes(status: String): LiveData<List<Note>> {  // WARNING! run this in background thread else it will crash
        return notesDao.getNotesByStatus(status)
    }


    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }
    suspend fun delete(note: Note){
        notesDao.delete(note)
    }

    suspend fun update(note: Note){
        notesDao.update(note)
    }
}