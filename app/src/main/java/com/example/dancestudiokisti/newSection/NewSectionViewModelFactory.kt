package com.example.dancestudiokisti.newSection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class NewSectionViewModelFactory (private val newSectionRepository: NewSectionRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewSectionViewModel(newSectionRepository) as T
    }
}