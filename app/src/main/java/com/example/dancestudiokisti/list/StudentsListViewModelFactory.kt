package com.example.dancestudiokisti.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class StudentsListViewModelFactory (private val studentsListRepository: StudentsListRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StudentsListViewModel(studentsListRepository) as T
    }
}