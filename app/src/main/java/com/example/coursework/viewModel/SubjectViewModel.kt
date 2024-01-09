package com.example.coursework.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.dao.CurrentControlApplication
import com.example.coursework.model.Subject
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

    fun getSubjectByName(subjectName: String): Flow<Subject>{
        return subjectDAO.getSubjectIdByName(subjectName).flowOn(Dispatchers.IO)
    }

}