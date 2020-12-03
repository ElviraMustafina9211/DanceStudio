package com.example.dancestudiokisti.list

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dancestudiokisti.R
import com.example.dancestudiokisti.details.StudentDetailsActivity

class StudentsItemHolder(view: View): RecyclerView.ViewHolder(view) {

    private lateinit var objectId: String

    private val tvFullName: TextView = view.findViewById(R.id.student_name)

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, StudentDetailsActivity::class.java)
            intent.putExtra("objectId",  objectId)
            view.context.startActivity(intent)
        }
    }

    fun bind(fullName: String, objectId: String) {
        tvFullName.text = fullName
        this.objectId = objectId
    }
}
