package com.example.coursework.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework.R
import com.example.coursework.teacherSide.TeachersGroupPage

class SubjectForTeacherAdapter(
    private var teachersListOfSubject: ArrayList<String>,
    private var teachersListOfGroups: ArrayList<String>,
    var context: Context
) :
    RecyclerView.Adapter<SubjectForTeacherAdapter.SubjectViewHolder>() {
    class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView2)
        val subjectName: TextView = itemView.findViewById(R.id.subjectNameStudentPage)
        val groupNumber: TextView = itemView.findViewById(R.id.teacherNameStudentPage)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubjectViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.subject_design, parent, false)
        return SubjectViewHolder(view)
    }

    override fun getItemCount(): Int {
        return teachersListOfSubject.size
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.imageView.setImageResource(R.drawable.baseline_class_24)

        val subject: String = teachersListOfSubject[position]
        val group: String = teachersListOfGroups[position]

        holder.subjectName.text = subject
        holder.groupNumber.text = group

        holder.itemView.setOnClickListener {
            val intent = Intent(context, TeachersGroupPage::class.java)
            intent.putExtra("subject", subject)
            intent.putExtra("groupNumber", group)
            context.startActivity(intent)
        }
    }
}