package com.example.coursework.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.CurrentControlApplication
import com.example.coursework.Model.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SubjectViewModel(): ViewModel() {
    private val subjectDAO = CurrentControlApplication().getDatabase(CurrentControlApplication.getApplicationContext()).getSubjectDAO()

    fun upsertSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO){
            subjectDAO?.upsertSublect(subject)
        }
    }

    fun deleteSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO) {
            subjectDAO?.deleteSubject(subject)
        }
    }

    fun getSubjectById(subjectId: Int): Flow<Subject> {
        return subjectDAO.getSubjectById(subjectId).flowOn(Dispatchers.IO)
    }
}