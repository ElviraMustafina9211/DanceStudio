package com.example.dancestudiokisti.newSection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NewSectionViewModel(private val newSectionRepository: NewSectionRepository) : ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val error: LiveData<Boolean> = _error

    private var disposable: Disposable? = null
    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    fun createSection(name: String, imageLink: String?) {
        if (imageLink != null) {
            imageLink.let {
                val newSection = Section(name, "", imageLink)
                _isLoading.value = true
                _error.value = false
                disposable = newSectionRepository
                    .createSection(newSection)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _isLoading.value = false
                    }, { _error.value = true; _isLoading.value = false })
            }
        } else {
            _isLoading.value = true
        }
    }
}