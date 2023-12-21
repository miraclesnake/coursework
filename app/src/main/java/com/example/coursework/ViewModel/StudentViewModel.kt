package com.example.coursework.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.coursework.CurrentControlApplication
import com.example.coursework.DAO.CurrentControlDatabase
import com.example.coursework.DAO.StudentDAO
import com.example.coursework.Model.Student
import com.example.coursework.Model.StudentGroup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class StudentViewModel(application: Application): AndroidViewModel(application) {
    private val studentDAO = CurrentControlApplication().database.getStudentDAO()

    fun upsert(student: Student) {
        viewModelScope.launch {
            studentDAO.upsertStudent(student)
        }
    }

    fun delete(student: Student) {
        viewModelScope.launch {
            studentDAO.deleteStudent(student)
        }
    }

    fun getAllStudents() {
        viewModelScope.launch {
            studentDAO.getAllStudents()
        }
    }

    fun getStudentByLoginPassword(login: String, password: String): LiveData<Student> {
        return liveData(Dispatchers.IO) {
            emitSource(
                studentDAO.getStudentByLoginPassword(login, password)
            )
        }
    }

    fun getAllStudentsFromGroup(studentGroup: StudentGroup) {
        viewModelScope.launch {
            studentDAO.getAllStudentFromGroup(
                studentGroup.academicProgram,
                studentGroup.groupNumber
            )
        }
    }

    fun deleteAllStudentsFromGroup(studentGroup: StudentGroup) {
        viewModelScope.launch {
            studentDAO.deleteAllStudentsFromGroup(
                studentGroup.academicProgram,
                studentGroup.groupNumber
            )
        }
    }
}
