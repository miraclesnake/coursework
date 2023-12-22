package com.example.coursework

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.example.coursework.DAO.CurrentControlDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CurrentControlApplication: Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    companion object {
        private var mInstance: CurrentControlApplication? = null
        private var res: Resources? = null

        fun getApplicationContext(): Context {
            return mInstance?.applicationContext!!
        }
    }


    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    fun getDatabase(context: Context): CurrentControlDatabase {
        return CurrentControlDatabase.getDatabase(context, applicationScope)
    }
}