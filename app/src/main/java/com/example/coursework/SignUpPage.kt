package com.example.coursework

import android.content.Intent
import android.media.midi.MidiDevice
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.coursework.DAO.CurrentControlDatabase
import com.example.coursework.Model.Student
import com.example.coursework.Model.StudentGroup
import com.example.coursework.Model.Teacher
import com.example.coursework.ViewModel.StudentViewModel
import com.example.coursework.ViewModel.TeacherViewModel
import com.example.coursework.databinding.ActivitySignUpPageBinding
import kotlinx.coroutines.CoroutineScope

class SignUpPage : AppCompatActivity() {
    private lateinit var sign: ActivitySignUpPageBinding
    lateinit var studentViewModel: StudentViewModel
    lateinit var teacherViewModel: TeacherViewModel
    lateinit var currentControlDatabase: CurrentControlDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sign = ActivitySignUpPageBinding.inflate(layoutInflater)
        setContentView(sign.root)

        currentControlDatabase = CurrentControlApplication().database

        studentViewModel = ViewModelProvider(this)[StudentViewModel::class.java]
        teacherViewModel = ViewModelProvider(this)[TeacherViewModel::class.java]

        val arrayAdapterGroups = ArrayAdapter.createFromResource(applicationContext, R.array.groups,
            android.R.layout.simple_spinner_item)
        arrayAdapterGroups.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val arrayAdapterPositions = ArrayAdapter.createFromResource(applicationContext, R.array.positions,
            android.R.layout.simple_spinner_item)
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
            val intent = Intent(this, LogInPage::class.java)
            startActivity(intent)
        }
    }

    private fun insertDataToDatabase() {
        val firstName = sign.newName.text.toString()
        val surname = sign.newSurname.text.toString()
        val middleName = sign.newMiddleName.text.toString()
        val login = sign.newLogin.text.toString()
        val password = sign.newPassword.text.toString()
        if(sign.isTeacher.isChecked){
            val position = sign.spinnerForTeacherPosition.selectedItem.toString()
            if (inputCheck(firstName, surname, middleName, login, password)){
                val teacher = Teacher( firstName, surname,
                    middleName, position, login, password, false)
                teacherViewModel.upsertTeacher(teacher)
            }
        }else{
            val studentGroupString = sign.spinnerForStudentGroup.selectedItem.toString()
            val studentGroupProgram = studentGroupString.subSequence(0,2).toString()
            val studentGroupNumber = studentGroupString.subSequence(3,5).toString().toInt()

            if(inputCheck(firstName, surname, middleName, login, password)){
                val student = Student( firstName, surname,
                    middleName, StudentGroup(studentGroupProgram, studentGroupNumber).id, login, password)
                studentViewModel.upsert(student)
            }
        }
    }

    private fun inputCheck(name: String, surname: String,
                           middleName: String, login: String, password: String): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(surname)
                && TextUtils.isEmpty(middleName) && TextUtils.isEmpty(login)
                && TextUtils.isEmpty(password))
    }
}