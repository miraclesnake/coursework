package com.example.coursework.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.CurrentControlApplication
import com.example.coursework.DAO.CurrentControlDatabase
import com.example.coursework.Model.Teacher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeacherViewModel(application: Application): AndroidViewModel(application) {
    private val teacherDAO = CurrentControlApplication().database.getTeacherDAO()

    fun upsertTeacher(teacher: Teacher){
        viewModelScope.launch(Dispatchers.IO) {
            teacherDAO.upsertTeacher(teacher)
        }
    }

    fun deleteTeacher(teacher: Teacher){
        viewModelScope.launch(Dispatchers.IO){
            teacherDAO.deleteTeacher(teacher)
        }
    }
}