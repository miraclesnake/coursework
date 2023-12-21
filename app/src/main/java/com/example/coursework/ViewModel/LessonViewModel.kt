package com.example.coursework.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.CurrentControlApplication
import com.example.coursework.DAO.CurrentControlDatabase
import com.example.coursework.Model.Lesson
import com.example.coursework.Model.Student
import com.example.coursework.Model.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LessonViewModel(application: Application): AndroidViewModel(application) {
    private val lessonDAO = CurrentControlApplication().database.getLessonDAO()

    fun upsert(lesson: Lesson){
        viewModelScope.launch(Dispatchers.IO){
            lessonDAO.upsert(lesson)
        }
    }

    fun delete(lesson: Lesson){
        viewModelScope.launch(Dispatchers.IO) {
            lessonDAO.delete(lesson)
        }
    }

    fun getStudentGrades(student: Student, subject: Subject){
        viewModelScope.launch(Dispatchers.IO) {
            lessonDAO.getAllStudentGradeBySubject(student.id, subject.id)
        }
    }
}