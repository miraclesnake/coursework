package com.example.coursework.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject")
data class Subject(
    val name: String,
    val credits: Int,
    val controlType: String
) {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}