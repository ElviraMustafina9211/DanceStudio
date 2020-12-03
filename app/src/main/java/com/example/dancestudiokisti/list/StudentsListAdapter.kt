package com.example.dancestudiokisti.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dancestudiokisti.R
import com.example.dancestudiokisti.Student

class StudentsListAdapter : RecyclerView.Adapter<StudentsItemHolder>() {

    private var students: List<Student> = emptyList()

    fun setStudents(students: List<Student>) {
        this.students = students
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsItemHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return StudentsItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentsItemHolder, position: Int) {
        holder.bind(students[position].fullName, students[position].objectId)
    }

    override fun getItemCount(): Int {
        return students.size
    }
}