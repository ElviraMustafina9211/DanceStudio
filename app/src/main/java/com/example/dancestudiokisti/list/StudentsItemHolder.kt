package com.example.dancestudiokisti.list

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dancestudiokisti.R

class StudentsItemHolder(view: View, onStudentClickListener: OnStudentClickListener): RecyclerView.ViewHolder(view) {

    private lateinit var objectId: String

    private val tvFullName: TextView = view.findViewById(R.id.student_name)

    init {
        view.setOnClickListener {
            onStudentClickListener.onClicked(objectId)

//            val intent = Intent(view.context, StudentDetailsActivity::class.java)
//            intent.putExtra("objectId",  objectId)
//            view.context.startActivity(intent)
        }
    }

    fun bind(fullName: String, objectId: String) {
        tvFullName.text = fullName
        this.objectId = objectId
    }
}
