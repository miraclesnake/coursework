package com.example.coursework.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teacher")
data class Teacher(
    val name: String,
    val surname: String,
    val middleName: String,
    val position: String,
    val login: String,
    val password: String,
    val isAdmin: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}