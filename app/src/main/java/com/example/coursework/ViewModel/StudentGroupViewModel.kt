package com.example.coursework.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.CurrentControlApplication
import com.example.coursework.Model.StudentGroup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentGroupViewModel(): ViewModel() {
    private val studentGroupDAO = CurrentControlApplication().getDatabase(CurrentControlApplication.getApplicationContext()).getStudentGroupDAO()

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

    fun getGroups(){
        viewModelScope.launch(Dispatchers.IO){
            studentGroupDAO.getAllGroups()
        }
    }
}