package com.example.coursework.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.CurrentControlApplication
import com.example.coursework.Model.WorkLoad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class WorkLoadViewModel(): ViewModel() {
    private val workLoadDAO = CurrentControlApplication().getDatabase(CurrentControlApplication.getApplicationContext()).getWorkLoadDAO()

    fun upsert(workLoad: WorkLoad){
        viewModelScope.launch(Dispatchers.IO){
            workLoadDAO.upsertWorkLoad(workLoad)
        }
    }

    fun delete(workLoad: WorkLoad){
        viewModelScope.launch(Dispatchers.IO){
            workLoadDAO.deleteWorkLoad(workLoad)
        }
    }

    suspend fun groupWorkload(groupId: Int): Flow<List<WorkLoad>>{
        return workLoadDAO.getWorkLoadOfStudentGroup(groupId).flowOn(Dispatchers.IO)
    }
}