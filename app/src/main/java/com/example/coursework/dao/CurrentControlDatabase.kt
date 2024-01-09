package com.example.coursework.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.coursework.model.Lesson
import com.example.coursework.model.Student
import com.example.coursework.model.StudentGroup
import com.example.coursework.model.Subject
import com.example.coursework.model.Teacher
import com.example.coursework.model.WorkLoad
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [Student::class, StudentGroup::class, Subject::class,
        Teacher::class, WorkLoad::class, Lesson::class], version = 1, exportSchema = true
)
abstract class CurrentControlDatabase : RoomDatabase() {
    abstract fun getStudentDAO(): StudentDAO
    abstract fun getStudentGroupDAO(): StudentGroupDAO
    abstract fun getSubjectDAO(): SubjectDAO
    abstract fun getTeacherDAO(): TeacherDAO
    abstract fun getWorkLoadDAO(): WorkLoadDAO
    abstract fun getLessonDAO(): LessonDAO

    companion object {
        @Volatile
        private var INSTANCE: CurrentControlDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CurrentControlDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CurrentControlDatabase::class.java,
                    "current_control_db"
                ).addCallback(GroupCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class GroupCallback(private val scope: CoroutineScope): Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                scope.launch {
                    val groupDao = it.getStudentGroupDAO()
                    val workloadDao = it.getWorkLoadDAO()
                    val subjectDao = it.getSubjectDAO()
                    val studentDao = it.getStudentDAO()
                    val teacherDao = it.getTeacherDAO()
                    groupDao.upsertStudentGroup(StudentGroup(academicProgram = "IK", groupNumber = 11))
                    groupDao.upsertStudentGroup(StudentGroup(academicProgram = "IK", groupNumber = 12))
                    groupDao.upsertStudentGroup(StudentGroup(academicProgram = "IK", groupNumber = 13))
                    groupDao.upsertStudentGroup(StudentGroup(academicProgram = "IA", groupNumber = 11))
                    groupDao.upsertStudentGroup(StudentGroup(academicProgram = "IA", groupNumber = 12))
                    groupDao.upsertStudentGroup(StudentGroup(academicProgram = "IA", groupNumber = 13))
                    groupDao.upsertStudentGroup(StudentGroup(academicProgram = "IA", groupNumber = 14))
                    groupDao.upsertStudentGroup(StudentGroup(academicProgram = "IC", groupNumber = 11))
                    groupDao.upsertStudentGroup(StudentGroup(academicProgram = "IC", groupNumber = 12))
                    groupDao.upsertStudentGroup(StudentGroup(academicProgram = "IC", groupNumber = 13))
                    studentDao.upsertStudent(Student(name = "Valeriia", middleName = "Viktorivna",
                        surname = "Yemtseva", groupID = 3, login = "calcifer", password = "setenu75"))
                    studentDao.upsertStudent(Student(name = "Iryna", middleName = "Oleksandrivna",
                        surname = "Voronina", groupID = 4, login = "seen_to_sunrise", password = "1234"))
                    teacherDao.upsertTeacher(Teacher(name = "Bohdan", surname = "Karashchuk",
                        middleName = "Serhiiovich", position = "Асистент", login = "eternityduck",
                        password = "Lera2003", isAdmin = true))
                    teacherDao.upsertTeacher(Teacher(name = "Olena", surname = "Yemtseva",
                        middleName = "Pavlivna", position = "Доцент", login = "olenaPav",
                        password = "Lera2003", isAdmin = false))
                    subjectDao.upsertSublect(Subject(name = "Вища математика",
                        credits = 18, controlType = "іспит"))
                    subjectDao.upsertSublect(Subject(name = "Бази даних",
                        credits = 36, controlType = "іспит"))
                    subjectDao.upsertSublect(Subject(name = "Філософія",
                        credits = 12, controlType = "залік"))
                    workloadDao.upsertWorkLoad(WorkLoad(studentGroupId = 3, subjectId = 1, teacherId = 2))
                    workloadDao.upsertWorkLoad(WorkLoad(studentGroupId = 3, subjectId = 2, teacherId = 1))
                    workloadDao.upsertWorkLoad(WorkLoad(studentGroupId = 4, subjectId = 3, teacherId = 2))
                }
            }
        }
    }
}