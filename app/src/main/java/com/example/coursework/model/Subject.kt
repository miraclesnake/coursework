package com.example.coursework.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val credits: Int,
    val controlType: String
)