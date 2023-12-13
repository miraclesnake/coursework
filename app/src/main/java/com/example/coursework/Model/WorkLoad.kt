package com.example.coursework.Model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "workload", foreignKeys = [
        ForeignKey(
            StudentGroup::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("studentGroupId")
        ),
        ForeignKey(
            Subject::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("subjectId")
        ),
        ForeignKey(
            Teacher::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("teacherId")
        )]
)
data class WorkLoad(
    val studentGroupId: Int,
    val subjectId: Int,
    val teacherId: Int
) {
    @PrimaryKey(autoGenerate = true)
    val workLoadId: Int = 0
}