package com.example.coursework.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.coursework.Model.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDAO {

    @Upsert
    suspend fun upsertStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("select * from student order by id")
    fun getAllStudents(): Flow<List<Student>>

    @Query("select * from student where groupID = (" +
            "select id from student_group " +
            "where academicProgram = :academicProgram and groupNumber = :groupNumber)")
    fun getAllStudentFromGroup(academicProgram: String, groupNumber: Int): Flow<List<Student>>

    @Query("delete from student where groupID = (" +
            "select id from student_group " +
            "where academicProgram = :academicProgram and groupNumber = :groupNumber)")
    suspend fun deleteAllStudentsFromGroup(academicProgram: String, groupNumber: Int)

    @Query("select * from student where login = :login and password = :password")
    fun getStudentByLoginPassword(login: String, password: String): LiveData<Student>


}