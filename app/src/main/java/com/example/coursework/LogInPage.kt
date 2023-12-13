package com.example.coursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coursework.DAO.CurrentControlDatabase
import com.example.coursework.Model.Student
import com.example.coursework.databinding.ActivityLogInPageBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LogInPage : AppCompatActivity() {
    lateinit var log: ActivityLogInPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log = ActivityLogInPageBinding.inflate(layoutInflater)
        setContentView(log.root)

        log.action.setOnClickListener {
            val currentControlDatabase = CurrentControlApplication().database
            if (log.radioButtonStudent.isChecked){
                val intent = Intent(this, StudentPage::class.java)
                val studentLogin = log.login.text.toString()
                val studentPassword = log.password.text.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    val student = currentControlDatabase
                        .getStudentDAO()
                        .getStudentByLoginPassword(studentLogin, studentPassword)
                    intent.putExtra("name", student.name)
                    intent.putExtra("surname", student.surname)
                    intent.putExtra("middlename", student.middleName)
                    intent.putExtra("groupId", student.groupID)
                    startActivity(intent)
                }
            }else if (log.radioButtonTeacher.isChecked){
                val intent = Intent(this, TeacherPage::class.java)
                val teacherLogin = log.login.text.toString()
                val teacherPassword = log.password.text.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    val teacher = currentControlDatabase
                        .getTeacherDAO()
                        .getTeacherByLoginPassword(teacherLogin, teacherPassword)
                    intent.putExtra("name", teacher.name)
                    intent.putExtra("surname", teacher.surname)
                    intent.putExtra("middlename", teacher.middleName)
                    intent.putExtra("isAdmin", teacher.isAdmin)
                    startActivity(intent)
                }
            }
        }
    }
}