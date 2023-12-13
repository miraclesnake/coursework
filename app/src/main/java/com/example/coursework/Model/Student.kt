package com.example.coursework.Model

import androidx.room.ColumnInfo
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
    val name: String,
    val surname: String,
    val middleName: String,
    val groupID: Int,
    val login: String,
    val password: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
