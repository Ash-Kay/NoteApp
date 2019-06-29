package com.example.noteapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivityForResult

class MainActivity : AppCompatActivity() {

    lateinit var noteVM : NoteVM
    lateinit var recyclerView : RecyclerView
    val ADD_NOTE_REQ = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = recycler_view
        recyclerView.layoutManager =LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val adapter : NoteAdapter = NoteAdapter()
        recyclerView.adapter = adapter

        noteVM = ViewModelProviders.of(this).get(NoteVM::class.java)
        noteVM.getAllNotes().observe( this, Observer {
            adapter.setNotes(it)
            Toast.makeText(this, "I think it changed!",Toast.LENGTH_LONG).show()
        } )

        fab_add_node.setOnClickListener {
            intent = Intent(this@MainActivity, AddNoteActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_REQ)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteVM.delete(adapter.getNoteAt(viewHolder.adapterPosition))
            }
        }).attachToRecyclerView(recyclerView)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == ADD_NOTE_REQ && resultCode == RESULT_OK){
            val note = Note(
                data?.getStringExtra(AddNoteActivity.EXTRA_TITLE),
                data?.getStringExtra(AddNoteActivity.EXTRA_DESC),
                data!!.getIntExtra(AddNoteActivity.EXTRA_PRIORITY,1)
            )
            noteVM.insert(note)

            Toast.makeText(this, "Note added",Toast.LENGTH_LONG).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.delete_all_notes -> {
                noteVM.deleteAll()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

}
