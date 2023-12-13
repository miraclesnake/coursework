package com.example.coursework.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.CurrentControlApplication
import com.example.coursework.DAO.CurrentControlDatabase
import com.example.coursework.Model.WorkLoad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkLoadViewModel(application: Application): AndroidViewModel(application) {
    private val workLoadDAO = CurrentControlApplication().database.getWorkLoadDAO()

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
}