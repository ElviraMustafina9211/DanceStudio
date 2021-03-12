package com.example.dancestudiokisti.sectionsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dancestudiokisti.TokenSaver

@Suppress("UNCHECKED_CAST")
class SectionsListViewModelFactory(private val sectionsListRepository: SectionsListRepository,
                                   private val tokenSaver: TokenSaver): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SectionsListViewModel(sectionsListRepository, tokenSaver) as T
    }
}