package com.example.coursework.teacherSide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coursework.adapter.SubjectForTeacherAdapter
import com.example.coursework.R
import com.example.coursework.viewModel.StudentGroupViewModel
import com.example.coursework.viewModel.SubjectViewModel
import com.example.coursework.viewModel.TeacherViewModel
import com.example.coursework.viewModel.WorkLoadViewModel
import com.example.coursework.databinding.ActivityTeacherPageBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class TeacherPage : AppCompatActivity() {
    lateinit var teacher: ActivityTeacherPageBinding
    private lateinit var adapter: SubjectForTeacherAdapter
    private var teachersListOfSubject = ArrayList<String>()
    private var teachersListOfGroups = ArrayList<String>()
    private lateinit var teacherViewModel: TeacherViewModel
    private lateinit var subjectViewModel: SubjectViewModel
    private lateinit var studentGroupViewModel: StudentGroupViewModel
    private lateinit var workloadViewModel: WorkLoadViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        teacher = ActivityTeacherPageBinding.inflate(layoutInflater)
        setContentView(teacher.root)

        teacherViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[TeacherViewModel::class.java]

        subjectViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[SubjectViewModel::class.java]

        studentGroupViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[StudentGroupViewModel::class.java]

        workloadViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[WorkLoadViewModel::class.java]

        val teacherId = intent.getIntExtra("id", 0)
        val teacherName = intent.getStringExtra("name")
        val teacherSurname = intent.getStringExtra("surname")
        val teacherMiddleName = intent.getStringExtra("middlename")

        teacher.teacherGreetTextView.text = getString(
            R.string.greetings_for_teacher,
            "$teacherSurname $teacherName $teacherMiddleName"
        )

        CoroutineScope(Dispatchers.IO).launch {
            workloadViewModel.teacherWorkload(teacherId).collect{
                it.forEach { work ->
                    val subjectFlow = subjectViewModel.getSubjectById(work.subjectId)
                    val groupFlow = studentGroupViewModel.getGroup(work.studentGroupId)

                    combine(subjectFlow, groupFlow){subject, group ->
                        Pair(subject.name, "${group.academicProgram}-${group.groupNumber}")
                    }.collect{(subjectName, groupName) ->
                        teachersListOfSubject.add(subjectName)
                        teachersListOfGroups.add(groupName)
                    }

                    runOnUiThread { adapter.notifyDataSetChanged()}
                    /*subjectViewModel.getSubjectById(work.subjectId).collect{subject->
                        teachersListOfSubject.add(subject.name)
                    }
                    studentGroupViewModel.getGroup(work.studentGroupId).collect{group->
                        teachersListOfGroups.add("${group.academicProgram}-${group.groupNumber}")
                    }*/
                }
            }
        }
        teacher.teacherSubjectRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SubjectForTeacherAdapter(teachersListOfSubject, teachersListOfGroups, this)
        teacher.teacherSubjectRecyclerView.adapter = adapter
    }
}