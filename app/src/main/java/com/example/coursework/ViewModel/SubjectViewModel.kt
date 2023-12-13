package com.example.coursework.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.CurrentControlApplication
import com.example.coursework.DAO.CurrentControlDatabase
import com.example.coursework.Model.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubjectViewModel(application: Application): AndroidViewModel(application) {
    private val subjectDAO = CurrentControlApplication().database.getSubjectDAO()

    fun upsertSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO){
            subjectDAO.upsertSublect(subject)
        }
    }

    fun deleteSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO) {
            subjectDAO.deleteSubject(subject)
        }
    }
}