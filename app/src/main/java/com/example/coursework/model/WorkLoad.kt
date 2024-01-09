package com.example.coursework.model

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
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val studentGroupId: Int,
    val subjectId: Int,
    val teacherId: Int
)