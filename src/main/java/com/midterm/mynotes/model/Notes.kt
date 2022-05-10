package com.midterm.mynotes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class Notes(

    @PrimaryKey
    val ID: UUID = UUID.randomUUID(),

    @ColumnInfo
    val title: String,

    @ColumnInfo
    val Name: String,

    @ColumnInfo
    val Datetime: Date

)
