package com.midterm.mynotes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.midterm.mynotes.model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDAO():NoteDatabaseDAO
}