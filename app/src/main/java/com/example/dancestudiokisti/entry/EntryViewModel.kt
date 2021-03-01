package com.example.dancestudiokisti.entry

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable

class EntryViewModel : ViewModel() {

    private var disposable: Disposable? = null
    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}