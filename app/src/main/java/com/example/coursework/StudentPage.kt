package com.example.coursework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coursework.ViewModel.SubjectViewModel
import com.example.coursework.ViewModel.TeacherViewModel
import com.example.coursework.ViewModel.WorkLoadViewModel
import com.example.coursework.databinding.ActivityStudentPageBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentPage : AppCompatActivity() {

    private lateinit var studentPage: ActivityStudentPageBinding
    private var subjectWithTeacherMap = mutableMapOf<String, String>()
    private lateinit var adapter: SubjectForStudentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        studentPage = ActivityStudentPageBinding.inflate(layoutInflater)
        setContentView(studentPage.root)

        studentPage.subjectRecyclerView.layoutManager = LinearLayoutManager(this)
        val db = CurrentControlApplication().getDatabase(CurrentControlApplication.getApplicationContext())
        /*
        val subjects = SubjectViewModel(application)
        val teachers = TeacherViewModel
        val workload = WorkLoadViewModel(application)
        */

        val studentName = intent.getStringExtra("name")
        val studentSurname = intent.getStringExtra("surname")
        val studentMiddleName = intent.getStringExtra("middlename")
        val studentGroup = intent.getIntExtra("groupId", 0)

        studentPage.greetTextView.text = getString(
            R.string.greetings_for_student,
            "$studentSurname $studentName $studentMiddleName"
        )
        /*
                CoroutineScope(Dispatchers.IO).launch {
                    workload.groupWorkload(studentGroup).collect{
                        it.forEach { work ->
                            subjects.getSubjectById(work.subjectId).collect{subj ->
                                teachers.getTeacherById(work.teacherId).collect{teacher ->
                                    subjectWithTeacherMap[subj.name] =
                                        "${teacher.surname} ${teacher.name} ${teacher.middleName}"
                                }
                            }
                        }
                    }
                }

                adapter = SubjectForStudentAdapter(subjectWithTeacherMap, this)
                studentPage.subjectRecyclerView.adapter = adapter
            }

         */
    }
}