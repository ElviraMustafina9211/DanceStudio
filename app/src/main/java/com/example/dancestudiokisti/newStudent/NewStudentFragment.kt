package com.example.dancestudiokisti.newStudent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dancestudiokisti.Injector
import com.example.dancestudiokisti.Keyboard
import com.example.dancestudiokisti.R
import com.example.dancestudiokisti.databinding.NewStudentFragmentBinding
import com.example.dancestudiokisti.views.Toolbars
import javax.inject.Inject

class NewStudentFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory : NewStudentViewModelFactory

    private var newStudentFragmentBinding: NewStudentFragmentBinding? = null

    @Inject
    lateinit var keyboard: Keyboard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Injector.instance.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_student_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = NewStudentFragmentBinding.bind(view)
        newStudentFragmentBinding = binding
        binding.appbar.toolbar.title = getString(R.string.new_student_title)
        Toolbars.enableBackButton(view, findNavController())

        val newStudentViewModel = ViewModelProvider(this, viewModelFactory).get(
            NewStudentViewModel::class.java)

        binding.saveButton.setOnClickListener {
            val sectionSpinner: Spinner = binding.spinner
            val fullNameEditText: EditText = binding.editFullName
            val purchasedLessonsEditText: EditText = binding.purchasedLessons
            val paymentCheckBox: CheckBox = binding.payment
            val balanceLessonsEditText: EditText = binding.balanceLessons

            val section = sectionSpinner.selectedItem.toString()
            val fullName = fullNameEditText.text.toString()
            val numberOfLessons = purchasedLessonsEditText.text.toString().toInt()
            val payment = paymentCheckBox.isChecked
            val balanceLessons = balanceLessonsEditText.text.toString().toInt()

            newStudentViewModel.createStudent(fullName, section, numberOfLessons, payment, balanceLessons)
            //Скрыть клавиатуру
            keyboard.hideKeyboard(view)
        }

        newStudentViewModel.closeScreen.observe(viewLifecycleOwner, { closeScreen: Boolean ->
            if (closeScreen) {
                findNavController().navigateUp()
            }
        })

        newStudentViewModel.getSections()

        newStudentViewModel.sectionNames.observe(viewLifecycleOwner, { sectionsList: List<String> ->
            context?.let {
                binding.spinner.adapter = ArrayAdapter(
                    it, R.layout.sections_spinner_item, sectionsList
                )
            }
        })

        newStudentViewModel.isLoading.observe(viewLifecycleOwner, { isLoading: Boolean ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        newStudentViewModel.error.observe(viewLifecycleOwner, { error: Boolean ->
            if (error) {
                binding.noInternetConnection.visibility = View.VISIBLE
            } else {
                binding.noInternetConnection.visibility = View.GONE
            }
        })
    }
}