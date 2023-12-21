package com.example.coursework

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SubjectForStudentAdapter(
    private var subjectWithTeacherMap: MutableMap<String, String>,
    var context: Context): RecyclerView.Adapter<SubjectForStudentAdapter.SubjectViewHolder>(){

    class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val subjectName: TextView = itemView.findViewById(R.id.subjectNameStudentPage)
        val teacherName: TextView = itemView.findViewById(R.id.teacherNameStudentPage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.subject_design, parent, false)
        return SubjectViewHolder(view)
    }

    override fun getItemCount(): Int {
        return subjectWithTeacherMap.size
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val key: String = subjectWithTeacherMap.keys.elementAt(position)
        holder.subjectName.text = key
        holder.teacherName.text = subjectWithTeacherMap[key]
    }
}