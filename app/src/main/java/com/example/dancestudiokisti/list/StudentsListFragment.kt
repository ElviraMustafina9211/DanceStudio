package com.example.dancestudiokisti.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dancestudiokisti.Injector
import com.example.dancestudiokisti.R
import com.example.dancestudiokisti.Student
import com.example.dancestudiokisti.databinding.ListStudentsFragmentBinding
import javax.inject.Inject


class StudentsListFragment : Fragment() {

    private var listStudentsFragmentBinding: ListStudentsFragmentBinding? = null

    @Inject
    lateinit var studentsListViewModel: StudentsListViewModel

    private val args: StudentsListFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //каждый раз пересоздает ViewModel, ViewModel НЕ пересоздается - это правило
        Injector.instance.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_students_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ListStudentsFragmentBinding.bind(view)
        listStudentsFragmentBinding = binding


        binding.fabAddStudent.setOnClickListener {
                val action = StudentsListFragmentDirections.actionStudentsListFragmentToNewStudentFragment()
                findNavController().navigate(action)
        }


//        val sectionName = intent.getStringExtra("sectionName")
        studentsListViewModel.getFullNames(args.sectionName)

        val recyclerView = binding.recyclerviewListStudentsFragment
        val studentsListAdapter = StudentsListAdapter(object : OnStudentClickListener {
            override fun onClicked(objectId: String) {
                val action = StudentsListFragmentDirections.actionStudentsListFragmentToStudentDetailsFragment(objectId)
                findNavController().navigate(action)
            }
        })

        recyclerView.adapter = studentsListAdapter

        studentsListViewModel.studentsList.observe(
            viewLifecycleOwner,
            { studentsList: List<Student> ->
                studentsListAdapter.setStudents(studentsList)
            })

        studentsListViewModel.isLoading.observe(viewLifecycleOwner, { isLoading: Boolean ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.swipeRefreshLayout.isRefreshing = false
            }
        })

        studentsListViewModel.error.observe(viewLifecycleOwner, { error: Boolean ->
            if (error) {
                binding.noInternetConnection.visibility = View.VISIBLE
            } else {
                binding.noInternetConnection.visibility = View.GONE
            }
        })

        binding.noInternetConnection.setOnClickListener {
            studentsListViewModel.getFullNames(args.sectionName)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            studentsListViewModel.getFullNames(args.sectionName)
        }
    }

    override fun onResume() {
        super.onResume()
//        val sectionName = intent.getStringExtra("sectionName")
        studentsListViewModel.getFullNames(args.sectionName)
    }
}