package com.example.coursework.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.coursework.Model.Teacher
import kotlinx.coroutines.flow.Flow

@Dao
interface TeacherDAO {
    @Upsert
    suspend fun upsertTeacher(teacher: Teacher)

    @Delete
    suspend fun deleteTeacher(teacher: Teacher)

    @Query("select * from teacher where login = :login and password = :password")
    fun getTeacherByLoginPassword(login: String, password: String): LiveData<Teacher>

    @Query("update teacher set isAdmin = 1 where id = :teacherID")
    suspend fun makeTeacherAdmin(teacherID: Int)

    @Query("update teacher set isAdmin = 0 where id = :teacherID")
    suspend fun deleteTeacherFromAdmin(teacherID: Int)

    @Query("select * from teacher where id = :teacherID")
    fun getTeacherById(teacherID: Int): Flow<Teacher>
}