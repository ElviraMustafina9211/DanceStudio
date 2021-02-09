package com.example.dancestudiokisti.sectionsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dancestudiokisti.newSection.Section
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SectionsListViewModel(private val sectionsListRepository: SectionsListRepository) : ViewModel() {

    private val _sectionsList: MutableLiveData<List<Section>> = MutableLiveData<List<Section>>()
    val sectionsList: LiveData<List<Section>> = _sectionsList

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private var disposable: Disposable? = null
    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    fun getSectionsList(userToken: String) {
        _isLoading.value = true
        _error.value = false
        disposable = sectionsListRepository
            .getSectionsList(userToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ sections: List<Section> ->
                _isLoading.value = false
                _sectionsList.value = sections
            }, { _error.value = true; _isLoading.value = false })
    }

    fun deleteOneSection(objectId: String) {
        _isLoading.value = true
        _error.value = false
        disposable = sectionsListRepository
            .deleteOneSection(objectId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _isLoading.value = false
                _sectionsList.value = _sectionsList.value?.filter { it.objectId != objectId }
            }, {
                println(it)
                _error.value = true;
                _isLoading.value = false
            })
    }
}