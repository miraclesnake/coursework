package com.example.coursework.studentSide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.coursework.viewModel.LessonViewModel
import com.example.coursework.viewModel.StudentViewModel
import com.example.coursework.viewModel.SubjectViewModel
import com.example.coursework.databinding.ActivityLessonBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LessonActivity : AppCompatActivity() {
    lateinit var lesson: ActivityLessonBinding
    lateinit var lessonViewModel: LessonViewModel
    lateinit var subjectViewModel: SubjectViewModel
    lateinit var studentViewModel: StudentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lesson = ActivityLessonBinding.inflate(layoutInflater)
        setContentView(lesson.root)

        lessonViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[LessonViewModel::class.java]

        subjectViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[SubjectViewModel::class.java]

        val subjectName = intent.getStringExtra("subject")
        lesson.textViewSubjectName.text = subjectName

        val teacherName = intent.getStringExtra("teacherName")
        val groupID = intent.getIntExtra("groupID", 0)

        CoroutineScope(Dispatchers.IO).launch {
            subjectViewModel.getSubjectByName(subjectName!!).collect{
                lessonViewModel.getStudentGrades(groupID, it).collect{
                    it.forEach {

                    }
                }
            }
        }
    }
}