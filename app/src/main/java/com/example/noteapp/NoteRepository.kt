package com.example.noteapp

import android.app.Application
import androidx.lifecycle.LiveData
import org.jetbrains.anko.doAsync

class NoteRepository(application: Application) {

    private var noteDao : NoteDao
    private var allNotes : LiveData<List<Note>>

    init {
        val database : NoteDatabase? = NoteDatabase.getInstance(application)
        noteDao = database!!.noteDao()
        allNotes = noteDao.getAll()
    }

    fun insert (note: Note){
        doAsync {
            noteDao.insert(note)
        }
    }

    fun update (note: Note){
        doAsync {
            noteDao.update(note)
        }
    }

    fun delete (note: Note){
        doAsync {
            noteDao.delete(note)
        }
    }

    fun deleteAll (){
        doAsync {
            noteDao.deleteAll()
        }
    }

    fun getAllNotes() : LiveData<List<Note>>{
        return allNotes
    }

}