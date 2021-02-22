package com.example.dancestudiokisti.sectionsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class SectionsListViewModelFactory(private val sectionsListRepository: SectionsListRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SectionsListViewModel(sectionsListRepository) as T
    }
}