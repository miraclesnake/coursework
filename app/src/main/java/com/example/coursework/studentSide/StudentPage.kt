package com.example.coursework.studentSide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coursework.adapter.SubjectForStudentAdapter
import com.example.coursework.R
import com.example.coursework.viewModel.SubjectViewModel
import com.example.coursework.viewModel.TeacherViewModel
import com.example.coursework.viewModel.WorkLoadViewModel
import com.example.coursework.databinding.ActivityStudentPageBinding
import com.example.coursework.viewModel.StudentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class StudentPage : AppCompatActivity() {

    private lateinit var studentPage: ActivityStudentPageBinding
    private var subjectWithTeacherMap = mutableMapOf<String, String>()
    private lateinit var adapter: SubjectForStudentAdapter
    private lateinit var teacherViewModel: TeacherViewModel
    private lateinit var subjectViewModel: SubjectViewModel
    private lateinit var studentViewModel: StudentViewModel
    private lateinit var workloadViewModel: WorkLoadViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        studentPage = ActivityStudentPageBinding.inflate(layoutInflater)
        setContentView(studentPage.root)

        studentPage.subjectRecyclerView.layoutManager = LinearLayoutManager(this)

        teacherViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[TeacherViewModel::class.java]

        subjectViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[SubjectViewModel::class.java]

        workloadViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[WorkLoadViewModel::class.java]

        studentViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[StudentViewModel::class.java]

        val studentId = intent.getIntExtra("id", 0)
        val studentName = intent.getStringExtra("name")
        val studentSurname = intent.getStringExtra("surname")
        val studentMiddleName = intent.getStringExtra("middlename")
        val studentGroup = intent.getIntExtra("groupId", 0)

        studentPage.greetTextView.text = getString(
            R.string.greetings_for_student,
            "$studentSurname $studentName $studentMiddleName"
        )
        CoroutineScope(Dispatchers.IO).launch {
            workloadViewModel.groupWorkload(studentGroup).collect {
                it.forEach { work ->
                    val subjectFlow = subjectViewModel.getSubjectById(work.subjectId)
                    val teacherFlow = teacherViewModel.getTeacherById(work.teacherId)

                    combine(subjectFlow, teacherFlow){subject, teacher ->
                        Pair(subject.name,
                            "${teacher.surname} ${teacher.name} ${teacher.middleName}")
                    }.collect{(subjectName, teacherName) ->
                        subjectWithTeacherMap[subjectName] = teacherName
                    }
                    runOnUiThread { adapter.notifyDataSetChanged()}
                }
            }
        }
        adapter = SubjectForStudentAdapter(subjectWithTeacherMap, this)
        studentPage.subjectRecyclerView.adapter = adapter
    }


}
