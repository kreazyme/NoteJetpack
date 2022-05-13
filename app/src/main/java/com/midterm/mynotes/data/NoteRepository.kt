package com.midterm.mynotes.data

import android.view.KeyEvent
import com.midterm.mynotes.model.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDAO: NoteDatabaseDAO) {
    suspend fun addNotes(notes: Notes) = noteDatabaseDAO.InsertNotes(notes)
    suspend fun updateNotes(notes: Notes) = noteDatabaseDAO.UpdateNotes(notes)
    suspend fun deleteNotes(notes: Notes) = noteDatabaseDAO.DeleteNote(notes)
    suspend fun deleteAllNotes() = noteDatabaseDAO.DeleteAll()
    suspend fun getAllNotes() : Flow<List<Notes>> = noteDatabaseDAO.GetNotes().flowOn(Dispatchers.IO)

}