package com.midterm.mynotes.data

import androidx.room.*
import com.midterm.mynotes.model.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDAO {

    @Query("Select * from notes")
    suspend fun GetNotes(): Flow<List<Notes>>

    @Query("Select * from notes where id = :id")
    suspend fun GetNotesbyID(id: String): Notes

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertNotes(notes: Notes)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun UpdateNotes(notes: Notes)

    @Query("Delete from notes")
    suspend fun DeleteAll()

    @Delete
    suspend fun DeleteNote(notes: Notes)
}