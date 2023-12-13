package com.example.coursework.DAO

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabase.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.coursework.Model.Lesson
import com.example.coursework.Model.Student
import com.example.coursework.Model.StudentGroup
import com.example.coursework.Model.Subject
import com.example.coursework.Model.Teacher
import com.example.coursework.Model.WorkLoad
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [Student::class, StudentGroup::class, Subject::class,
        Teacher::class, WorkLoad::class, Lesson::class], version = 2
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

    private class GroupCallback(private val scope: CoroutineScope): RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                scope.launch {
                    val groupDao = it.getStudentGroupDAO()
                    groupDao.upsertStudentGroup(StudentGroup("IK", 11))
                    groupDao.upsertStudentGroup(StudentGroup("IK", 12))
                    groupDao.upsertStudentGroup(StudentGroup("IK", 13))
                    groupDao.upsertStudentGroup(StudentGroup("IA", 11))
                    groupDao.upsertStudentGroup(StudentGroup("IA", 12))
                    groupDao.upsertStudentGroup(StudentGroup("IA", 13))
                    groupDao.upsertStudentGroup(StudentGroup("IA", 14))
                    groupDao.upsertStudentGroup(StudentGroup("IC", 11))
                    groupDao.upsertStudentGroup(StudentGroup("IC", 12))
                    groupDao.upsertStudentGroup(StudentGroup("IC", 13))
                }
            }
        }
    }
}