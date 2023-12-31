package com.example.coursework.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "student",
    foreignKeys = [ForeignKey(
        entity = StudentGroup::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("groupID")
    )]
)
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val surname: String,
    val middleName: String,
    val groupID: Int,
    val login: String,
    val password: String
)
