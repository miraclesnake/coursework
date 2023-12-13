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
    val studentID: Int,
    val subjectID: Int,
    val studentGrade: Double,
    val date: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}