package com.example.coursework.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.coursework.CurrentControlApplication
import com.example.coursework.Model.Teacher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class TeacherViewModel(): ViewModel() {
    private val teacherDAO = CurrentControlApplication().getDatabase(CurrentControlApplication.getApplicationContext()).getTeacherDAO()

    fun upsertTeacher(teacher: Teacher) {
        viewModelScope.launch(Dispatchers.IO) {
            teacherDAO.upsertTeacher(teacher)
        }
    }

    fun deleteTeacher(teacher: Teacher) {
        viewModelScope.launch(Dispatchers.IO) {
            teacherDAO.deleteTeacher(teacher)
        }
    }

    fun getTeacherByLoginPassword(login: String, password: String): LiveData<Teacher> {
        return liveData(Dispatchers.IO) {
            emitSource(
                teacherDAO.getTeacherByLoginPassword(login, password)
            )
        }
    }

    fun getTeacherById(teacherId: Int): Flow<Teacher> {
        return teacherDAO.getTeacherById(teacherId).flowOn(Dispatchers.IO)
    }

    fun makeAdmin(teacherId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            teacherDAO.makeTeacherAdmin(teacherId)
        }
    }

    fun deleteAdmin(teacherId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            teacherDAO.deleteTeacherFromAdmin(teacherId)
        }
    }
}