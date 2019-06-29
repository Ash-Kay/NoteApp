package com.example.noteapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import org.jetbrains.anko.doAsync

@Database(entities = arrayOf(Note::class), version = 1)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun noteDao() : NoteDao

    companion object {
        private var INSTANCE: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase? {
            if (INSTANCE == null) {
                synchronized(NoteDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        NoteDatabase::class.java, "note_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                doAsync {
                                    getInstance(context)?.noteDao()?.insert(Note("T1","D1",1))
                                }
                            }
                        })
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }


    }

}