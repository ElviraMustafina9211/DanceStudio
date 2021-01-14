package com.example.dancestudiokisti.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class StudentDetailsViewModelFactory (private val studentDetailsRepository: StudentDetailsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StudentDetailsViewModel(studentDetailsRepository) as T
    }
}