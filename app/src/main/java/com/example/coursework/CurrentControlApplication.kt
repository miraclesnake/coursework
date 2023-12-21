package com.example.coursework

import android.app.Application
import android.content.Context
import com.example.coursework.DAO.CurrentControlDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CurrentControlApplication: Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { CurrentControlDatabase.getDatabase(applicationContext, applicationScope) }
}