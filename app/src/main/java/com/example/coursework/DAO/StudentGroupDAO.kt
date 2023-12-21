package com.example.coursework.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.coursework.Model.StudentGroup
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentGroupDAO {

    @Upsert
    suspend fun upsertStudentGroup(studentGroup: StudentGroup)

    @Delete
    suspend fun deleteStudentGroup(studentGroup: StudentGroup)

    @Query("select * from student_group")
    fun getAllGroups(): Flow<List<StudentGroup>>


}