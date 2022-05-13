package com.midterm.mynotes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.midterm.mynotes.Converters
import com.midterm.mynotes.model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDAO():NoteDatabaseDAO
}