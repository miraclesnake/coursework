package com.example.coursework.entryPages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.coursework.studentSide.LessonActivity
import com.example.coursework.studentSide.StudentPage
import com.example.coursework.teacherSide.TeacherPage
import com.example.coursework.viewModel.StudentViewModel
import com.example.coursework.viewModel.TeacherViewModel
import com.example.coursework.databinding.ActivityLogInPageBinding


class LogInPage : AppCompatActivity() {
    private lateinit var log: ActivityLogInPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log = ActivityLogInPageBinding.inflate(layoutInflater)
        setContentView(log.root)

        val studentViewModel = ViewModelProvider(this)[StudentViewModel::class.java]
        val teacherViewModel = ViewModelProvider(this)[TeacherViewModel::class.java]

        log.action.setOnClickListener {
            if (log.radioButtonStudent.isChecked) {
                val intent = Intent(this, StudentPage::class.java)
                val intent2 = Intent(this, LessonActivity::class.java)
                val studentLogin = log.login.text.toString()
                val studentPassword = log.password.text.toString()
                studentViewModel
                    .getStudentByLoginPassword(studentLogin, studentPassword)
                    .observe(this) {
                        intent.putExtra("id", it.id)
                        intent.putExtra("name", it.name)
                        intent.putExtra("surname", it.surname)
                        intent.putExtra("middlename", it.middleName)
                        intent.putExtra("groupId", it.groupID)
                        startActivity(intent)
                        intent2.putExtra("groupID", it.groupID)
                        startActivity(intent2)
                    }
            } else if (log.radioButtonTeacher.isChecked) {
                val intent = Intent(this, TeacherPage::class.java)
                val teacherLogin = log.login.text.toString()
                val teacherPassword = log.password.text.toString()
                teacherViewModel
                    .getTeacherByLoginPassword(teacherLogin, teacherPassword)
                    .observe(this) {
                        intent.putExtra("id", it.id)
                        intent.putExtra("name", it.name)
                        intent.putExtra("surname", it.surname)
                        intent.putExtra("middlename", it.middleName)
                        intent.putExtra("isAdmin", it.isAdmin)
                        startActivity(intent)
                    }
            }
        }
    }
}