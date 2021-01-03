package com.example.dancestudiokisti.newStudent

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.dancestudiokisti.Injector
import com.example.dancestudiokisti.R
import com.example.dancestudiokisti.databinding.NewStudentFragmentBinding
import javax.inject.Inject

class NewStudentFragment : Fragment() {

    private lateinit var binding: NewStudentFragmentBinding

    @Inject
    lateinit var newStudentViewModel: NewStudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = NewStudentActivityBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        title = getString(R.string.new_student_title)
//        setSupportActionBar(binding.toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Injector.instance.inject(this)

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

            newStudentViewModel.createStudent(
                fullName,
                section,
                numberOfLessons,
                payment,
                balanceLessons
            )

//            val view = this.currentFocus
//            view?.let { v ->
//                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
//                imm?.hideSoftInputFromWindow(v.windowToken, 0)
//            }
        }
        newStudentViewModel.closeScreen.observe(this, { closeScreen: Boolean ->
            if (closeScreen) {
//                finish()
            }
        })

        newStudentViewModel.getSections()

        newStudentViewModel.sectionNames.observe(this, { sectionsList: List<String> ->
            context?.let {
                binding.spinner.adapter = ArrayAdapter(
                    it,
                    R.layout.sections_spinner_item,
                    sectionsList
                )
            }
        })

        newStudentViewModel.isLoading.observe(this, { isLoading: Boolean ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        newStudentViewModel.error.observe(this, { error: Boolean ->
            if (error) {
                binding.noInternetConnection.visibility = View.VISIBLE
            } else {
                binding.noInternetConnection.visibility = View.GONE
            }
        })
    }
}