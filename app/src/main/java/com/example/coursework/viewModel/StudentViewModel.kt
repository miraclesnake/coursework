package com.example.coursework.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.coursework.dao.CurrentControlApplication
import com.example.coursework.model.Student
import com.example.coursework.model.StudentGroup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(): ViewModel() {
    private val studentDAO = CurrentControlApplication().getDatabase(CurrentControlApplication.getApplicationContext()).getStudentDAO()

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
