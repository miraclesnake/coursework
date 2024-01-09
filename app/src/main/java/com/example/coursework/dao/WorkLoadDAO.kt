package com.example.coursework.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.coursework.model.WorkLoad
import kotlinx.coroutines.flow.Flow
@Dao
interface WorkLoadDAO {
    @Upsert
    suspend fun upsertWorkLoad(workLoad: WorkLoad)

    @Delete
    suspend fun deleteWorkLoad(workLoad: WorkLoad)

    @Query("select * from workload where studentGroupId = :groupId")
    fun getWorkLoadOfStudentGroup(groupId: Int): Flow<List<WorkLoad>>

    @Query("select * from workload where teacherId = :teacherId")
    fun getWorkLoadOfTeacher(teacherId: Int): Flow<List<WorkLoad>>
}