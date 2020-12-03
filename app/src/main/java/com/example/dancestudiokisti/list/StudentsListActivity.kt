package com.example.dancestudiokisti.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.dancestudiokisti.Injector
import com.example.dancestudiokisti.R
import com.example.dancestudiokisti.Student
import com.example.dancestudiokisti.databinding.ListStudentsActivityBinding
import com.example.dancestudiokisti.newStudent.NewStudentActivity
import kotlinx.android.synthetic.main.list_sections_activity.*
import javax.inject.Inject


class StudentsListActivity : AppCompatActivity() {

    private lateinit var binding: ListStudentsActivityBinding

    @Inject
    lateinit var studentsListViewModel: StudentsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListStudentsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.students_list_title)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sectionName = intent.getStringExtra("sectionName")

        binding.fabAddStudent.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            intent.putExtra("sectionName", "New Section")
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
        }

        Injector.instance.inject(this)

        studentsListViewModel.getFullNames(sectionName)

        val recyclerView = binding.recyclerviewListStudentsActivity
        val studentsListAdapter = StudentsListAdapter()
        recyclerView.adapter = studentsListAdapter

        studentsListViewModel.studentsList.observe(this, { studentsList: List<Student> ->
            studentsListAdapter.setStudents(studentsList)
        })

        studentsListViewModel.isLoading.observe(this, { isLoading: Boolean ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.swipeRefreshLayout.isRefreshing = false
            }
        })

        studentsListViewModel.error.observe(this, { error: Boolean ->
            if (error) {
                binding.noInternetConnection.visibility = View.VISIBLE
            } else {
                binding.noInternetConnection.visibility = View.GONE
            }
        })

        binding.noInternetConnection.setOnClickListener {
            studentsListViewModel.getFullNames(sectionName)
        }

        swipeRefreshLayout.setOnRefreshListener {
            studentsListViewModel.getFullNames(sectionName)
        }
    }
}