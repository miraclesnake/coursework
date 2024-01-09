package com.example.coursework.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.coursework.model.StudentGroup
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentGroupDAO {

    @Upsert
    suspend fun upsertStudentGroup(studentGroup: StudentGroup)

    @Delete
    suspend fun deleteStudentGroup(studentGroup: StudentGroup)

    @Query("select * from student_group")
    fun getAllGroups(): Flow<List<StudentGroup>>

    @Query("select * from student_group where id = :groupId")
    fun getGroupById(groupId: Int): Flow<StudentGroup>
}