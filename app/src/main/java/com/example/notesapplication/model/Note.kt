package com.example.notesapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(@PrimaryKey(autoGenerate = true)val id: Long = 0, val title: String, val description: String, val timeStamp: Long =  System.currentTimeMillis()){
}