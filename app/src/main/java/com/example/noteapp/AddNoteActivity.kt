package com.example.noteapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*
import org.jetbrains.anko.toast

class AddNoteActivity : AppCompatActivity() {

    //constant key
    companion object{
        val EXTRA_TITLE = "com.example.noteapp.EXTRA_TITLE"
        val EXTRA_DESC = "com.example.noteapp.EXTRA_DESC"
        val EXTRA_PRIORITY = "com.example.noteapp.EXTRA_PRIORITY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        number_picker.minValue = 1
        number_picker.maxValue = 10


        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        setTitle("Add Note")

    }

    private fun saveNote(){

        val title = edit_text_title.text.toString()
        val desc = edit_text_description.text.toString()

        if(title.isBlank() || desc.isBlank()){
            Toast.makeText(this,"Title or Description can't be empty",Toast.LENGTH_LONG).show()
            return
        }

        val data = Intent()
        data.putExtra(EXTRA_TITLE,title)
        data.putExtra(EXTRA_DESC,desc)
        data.putExtra(EXTRA_PRIORITY,number_picker.value)

        setResult(Activity.RESULT_OK,data)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.save_note -> {
                saveNote()
                return true
            }

            else -> super.onOptionsItemSelected(item)

        }
        return false
    }

}
