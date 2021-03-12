package com.example.dancestudiokisti.newStudent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dancestudiokisti.sectionsList.SectionsListRepository

@Suppress("UNCHECKED_CAST")
class NewStudentViewModelFactory (private val newStudentRepository: NewStudentRepository,
                                  private val sectionsListRepository: SectionsListRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewStudentViewModel(newStudentRepository, sectionsListRepository) as T
    }
}