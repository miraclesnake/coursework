package com.example.coursework.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.coursework.Model.Subject
import com.example.coursework.Model.Teacher
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDAO {

    @Upsert
    suspend fun upsertSublect(subject: Subject)

    @Delete
    suspend fun deleteSubject(subject: Subject)

    @Query("select * from subject where id = :subjectId")
    fun getSubjectById(subjectId: Int): Flow<Subject>
}