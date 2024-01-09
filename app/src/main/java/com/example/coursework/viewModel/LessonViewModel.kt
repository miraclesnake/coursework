package com.example.coursework.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.dao.CurrentControlApplication
import com.example.coursework.model.Lesson
import com.example.coursework.model.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class LessonViewModel(): ViewModel() {
    private val lessonDAO = CurrentControlApplication().getDatabase(CurrentControlApplication.getApplicationContext()).getLessonDAO()

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

    fun getStudentGrades(studentId: Int, subject: Subject): Flow<List<Lesson>> {
        return lessonDAO.getAllStudentGradeBySubject(studentId, subject.id).flowOn(Dispatchers.IO)
    }
}