package com.example.coursework.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_group")
data class StudentGroup(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val academicProgram: String,
    val groupNumber: Int
)