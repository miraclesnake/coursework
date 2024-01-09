package com.example.coursework.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.dao.CurrentControlApplication
import com.example.coursework.model.StudentGroup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
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

    fun getAllGroups(){
        viewModelScope.launch(Dispatchers.IO){
            studentGroupDAO.getAllGroups()
        }
    }

    fun getGroup(groupId: Int): Flow<StudentGroup>{
        return studentGroupDAO.getGroupById(groupId).flowOn(Dispatchers.IO)
    }
}