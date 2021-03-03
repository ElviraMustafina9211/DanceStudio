package com.example.dancestudiokisti.entry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dancestudiokisti.TokenSaver

@Suppress("UNCHECKED_CAST")
class EntryViewModelFactory (private val tokenSaver: TokenSaver) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EntryViewModel(tokenSaver) as T
    }
}