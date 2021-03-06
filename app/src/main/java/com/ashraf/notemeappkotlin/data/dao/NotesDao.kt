package com.ashraf.notemeappkotlin.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ashraf.notemeappkotlin.data.model.Note

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note : Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("Select * from notesTable where status= :status order by id ASC")
    fun getNotesByStatus(status:String): LiveData<List<Note>>


}