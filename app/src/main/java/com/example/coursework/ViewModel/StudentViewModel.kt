package com.example.coursework.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.CurrentControlApplication
import com.example.coursework.DAO.CurrentControlDatabase
import com.example.coursework.Model.Student
import com.example.coursework.Model.StudentGroup
import kotlinx.coroutines.launch

class StudentViewModel(application: Application): AndroidViewModel(application) {
    private val studentDAO = CurrentControlApplication().database.getStudentDAO()

    fun upsert(student: Student){
        viewModelScope.launch {
            studentDAO.upsertStudent(student)
        }
    }

    fun delete(student: Student){
        viewModelScope.launch {
            studentDAO.deleteStudent(student)
        }
    }

    fun getAllStudents(){
        viewModelScope.launch {
            studentDAO.getAllStudents()
        }
    }

    fun getAllStudentsFromGroup(studentGroup: StudentGroup){
        viewModelScope.launch {
            studentDAO.getAllStudentFromGroup(studentGroup.academicProgram, studentGroup.groupNumber)
        }
    }

    fun deleteAllStudentsFromGroup(studentGroup: StudentGroup){
        viewModelScope.launch {
            studentDAO.deleteAllStudentsFromGroup(studentGroup.academicProgram, studentGroup.groupNumber)
        }
    }
}