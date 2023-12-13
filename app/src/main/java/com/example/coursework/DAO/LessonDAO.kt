package com.example.coursework.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.coursework.Model.Lesson
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDAO {

    @Upsert
    suspend fun upsert(lesson: Lesson)

    @Delete
    suspend fun delete(lesson: Lesson)

    @Query("select * from lessons where studentID = :studentId and subjectID = :subjectId")
    fun getAllStudentGradeBySubject(studentId: Int, subjectId: Int): Flow<List<Lesson>>

}