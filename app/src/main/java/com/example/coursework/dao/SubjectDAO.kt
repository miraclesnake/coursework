package com.example.coursework.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.coursework.model.Subject
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDAO {

    @Upsert
    suspend fun upsertSublect(subject: Subject)

    @Delete
    suspend fun deleteSubject(subject: Subject)

    @Query("select * from subject where id = :subjectId")
    fun getSubjectById(subjectId: Int): Flow<Subject>

    @Query("select * from subject where name = :subjectName")
    fun getSubjectIdByName(subjectName: String): Flow<Subject>
}