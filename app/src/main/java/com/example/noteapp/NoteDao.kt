package com.example.noteapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    fun insert(note : Note)

    @Update
    fun update(note : Note)

    @Delete
    fun delete(note : Note)

    @Query("DELETE FROM note_table")
    fun deleteAll()

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun getAll() : LiveData<List<Note>>
}