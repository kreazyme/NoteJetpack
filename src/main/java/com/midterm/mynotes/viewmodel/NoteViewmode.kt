package com.midterm.mynotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midterm.mynotes.data.NoteRepository
import com.midterm.mynotes.model.Notes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewmode @Inject constructor(private val repository: NoteRepository) : ViewModel(){
    private val _notelist = MutableStateFlow<List<Notes>>(emptyList())
    val notelist = _notelist.asStateFlow()

    init {
        viewModelScope.launch (Dispatchers.IO){
            repository.getAllNotes().distinctUntilChanged()
                .collect {
                    listofNotes ->
                    if(listofNotes.isNullOrEmpty()){
                        println("Empty notes")
                    }
                    else{
                        _notelist.value = listofNotes
                    }
                }
        }
    }

    fun addnotes(notes: Notes)= viewModelScope.launch {
        println("OK")
        repository.addNotes(notes)
    }

    fun deleteAllnote() = viewModelScope.launch {
        repository.deleteAllNotes()
    }

    fun getNotes()= viewModelScope.launch {
        repository.getAllNotes()
    }

    fun updatenote(notes: Notes)= viewModelScope.launch {
        repository.updateNotes(notes)
    }

    fun deletenotes(notes: Notes)= viewModelScope.launch {
        repository.deleteNotes(notes)
    }


}