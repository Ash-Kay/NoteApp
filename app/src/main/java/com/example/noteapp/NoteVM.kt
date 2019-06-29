package com.example.noteapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteVM(application: Application) : AndroidViewModel(application) {

    private var noteRepository : NoteRepository
    private var allNotes : LiveData<List<Note>>

    init {
        noteRepository = NoteRepository(application)
        allNotes = noteRepository.getAllNotes()
    }

    fun insert (note: Note){
        noteRepository.insert(note)
    }

    fun update (note: Note){
        noteRepository.update(note)
    }

    fun delete (note: Note){
        noteRepository.delete(note)
    }

    fun deleteAll (){
        noteRepository.deleteAll()
    }

    fun getAllNotes() : LiveData<List<Note>>{
        return allNotes
    }


}