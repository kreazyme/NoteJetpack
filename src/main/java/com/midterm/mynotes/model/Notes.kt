package com.midterm.mynotes.model

import androidx.room.*
import com.midterm.mynotes.Converters
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
    val Date: Date

)
