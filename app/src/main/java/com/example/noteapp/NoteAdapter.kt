package com.example.noteapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    private var notes : List<Note> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        return NoteHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false))
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currNote : Note = notes.get(position)

        holder.noteTitle.text = currNote.title
        holder.noteDescription.text = currNote.description
        holder.notePriority.text = currNote.priority.toString()
    }

    fun setNotes(notes : List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }

    fun getNoteAt(i:Int) : Note{
        return notes[i]
    }

    class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public val noteTitle : TextView
        public val noteDescription : TextView
        public val notePriority : TextView

        init {
            noteTitle = itemView.findViewById(R.id.textview_title)
            noteDescription = itemView.findViewById(R.id.textview_description)
            notePriority = itemView.findViewById(R.id.textview_priority)
        }

    }

}