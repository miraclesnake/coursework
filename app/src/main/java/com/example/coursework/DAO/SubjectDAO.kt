package com.example.coursework.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.coursework.Model.Subject
import com.example.coursework.Model.Teacher

@Dao
interface SubjectDAO {

    @Upsert
    suspend fun upsertSublect(subject: Subject)

    @Delete
    suspend fun deleteSubject(subject: Subject)
}