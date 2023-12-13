package com.example.coursework.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.coursework.Model.Teacher

@Dao
interface TeacherDAO {
    @Upsert
    suspend fun upsertTeacher(teacher: Teacher)

    @Delete
    suspend fun deleteTeacher(teacher: Teacher)

    @Query("select * from teacher where login = :login and password = :password")
    suspend fun getTeacherByLoginPassword(login: String, password: String): Teacher

    @Query("update teacher set isAdmin = 1 where id = :teacherID")
    suspend fun makeTeacherAdmin(teacherID: Int)

    @Query("update teacher set isAdmin = 0 where id = :teacherID")
    suspend fun deleteTeacherFromAdmin(teacherID: Int)
}