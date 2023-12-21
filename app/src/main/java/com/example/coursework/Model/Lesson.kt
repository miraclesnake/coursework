package com.example.coursework.Model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "lessons",
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("studentID")
        ),
        ForeignKey(
            entity = Subject::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("subjectID")
        )
    ]
)
data class Lesson(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val studentID: Int,
    val subjectID: Int,
    val studentGrade: Double,
    val date: String
)