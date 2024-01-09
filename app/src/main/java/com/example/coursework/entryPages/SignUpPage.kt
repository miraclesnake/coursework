package com.example.coursework.entryPages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.coursework.dao.CurrentControlDatabase
import com.example.coursework.model.Student
import com.example.coursework.model.StudentGroup
import com.example.coursework.model.Teacher
import com.example.coursework.R
import com.example.coursework.viewModel.StudentViewModel
import com.example.coursework.viewModel.TeacherViewModel
import com.example.coursework.databinding.ActivitySignUpPageBinding

class SignUpPage : AppCompatActivity() {
    private lateinit var sign: ActivitySignUpPageBinding
    private lateinit var studentViewModel: StudentViewModel
    private lateinit var teacherViewModel: TeacherViewModel
    private lateinit var currentControlDatabase: CurrentControlDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sign = ActivitySignUpPageBinding.inflate(layoutInflater)
        setContentView(sign.root)

        studentViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory(application)
            )[StudentViewModel::class.java]
        teacherViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory(application)
            )[TeacherViewModel::class.java]

        val arrayAdapterGroups = ArrayAdapter.createFromResource(
            applicationContext, R.array.groups,
            android.R.layout.simple_spinner_item
        )
        arrayAdapterGroups.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val arrayAdapterPositions = ArrayAdapter.createFromResource(
            applicationContext, R.array.positions,
            android.R.layout.simple_spinner_item
        )
        arrayAdapterPositions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        sign.spinnerForStudentGroup.adapter = arrayAdapterGroups
        sign.spinnerForTeacherPosition.adapter = arrayAdapterPositions

        sign.isStudent.setOnClickListener {
            sign.spinnerForStudentGroup.visibility = View.VISIBLE
            sign.spinnerForTeacherPosition.visibility = View.INVISIBLE
        }
        sign.isTeacher.setOnClickListener {
            sign.spinnerForTeacherPosition.isVisible = true
            sign.spinnerForStudentGroup.isVisible = false
        }


        sign.signUpUser.setOnClickListener {
            insertDataToDatabase()
            val intent = Intent(applicationContext, LogInPage::class.java)
            startActivity(intent)
        }
    }

    private fun insertDataToDatabase() {
        val firstName = sign.newName.text.toString()
        val surname = sign.newSurname.text.toString()
        val middleName = sign.newMiddleName.text.toString()
        val login = sign.newLogin.text.toString()
        val password = sign.newPassword.text.toString()
        if (sign.isTeacher.isChecked) {
            val position = sign.spinnerForTeacherPosition.selectedItem.toString()
            if (inputCheck(firstName, surname, middleName, login, password)) {
                val teacher = Teacher(
                    name = firstName, surname = surname,
                    middleName = middleName, position = position,
                    login = login, password = password, isAdmin = false
                )
                teacherViewModel.upsertTeacher(teacher)
            }
        } else {
            val studentGroupString = sign.spinnerForStudentGroup.selectedItem.toString()
            val studentGroupProgram = studentGroupString.subSequence(0, 2).toString()
            val studentGroupNumber = studentGroupString.subSequence(3, 5).toString().toInt()

            if (inputCheck(firstName, surname, middleName, login, password)) {
                val student = Student(
                    name = firstName, surname = surname,
                    middleName = middleName, groupID = StudentGroup(
                        academicProgram =
                        studentGroupProgram, groupNumber = studentGroupNumber
                    ).id,
                    login = login, password = password
                )
                studentViewModel.upsert(student)
            }
        }
    }

    private fun inputCheck(
        name: String, surname: String,
        middleName: String, login: String, password: String
    ): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(surname)
                && TextUtils.isEmpty(middleName) && TextUtils.isEmpty(login)
                && TextUtils.isEmpty(password))
    }
}