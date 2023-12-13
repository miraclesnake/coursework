package com.example.coursework.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import com.example.coursework.Model.WorkLoad

@Dao
interface WorkLoadDAO {
    @Upsert
    suspend fun upsertWorkLoad(workLoad: WorkLoad)

    @Delete
    suspend fun deleteWorkLoad(workLoad: WorkLoad)

}