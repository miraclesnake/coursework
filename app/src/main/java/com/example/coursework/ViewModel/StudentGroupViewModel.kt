package com.example.coursework.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.CurrentControlApplication
import com.example.coursework.DAO.CurrentControlDatabase
import com.example.coursework.Model.StudentGroup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentGroupViewModel(application: Application): AndroidViewModel(application) {
    private val studentGroupDAO = CurrentControlApplication().database.getStudentGroupDAO()

    fun upsertGroup(studentGroup: StudentGroup){
        viewModelScope.launch(Dispatchers.IO){
            studentGroupDAO.upsertStudentGroup(studentGroup)
        }
    }

    fun deleteGroup(studentGroup: StudentGroup){
        viewModelScope.launch(Dispatchers.IO) {
            studentGroupDAO.deleteStudentGroup(studentGroup)
        }
    }
}