package com.example.coursework.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.coursework.Model.WorkLoad
import kotlinx.coroutines.flow.Flow
@Dao
interface WorkLoadDAO {
    @Upsert
    suspend fun upsertWorkLoad(workLoad: WorkLoad)

    @Delete
    suspend fun deleteWorkLoad(workLoad: WorkLoad)

    @Query("select * from workload where studentGroupId = :groupId")
    fun getWorkLoadOfStudentGroup(groupId: Int): Flow<List<WorkLoad>>
}