package com.example.dancestudiokisti.newStudent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dancestudiokisti.Student
import com.example.dancestudiokisti.sectionsList.SectionsListRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NewStudentViewModel(
    private val newStudentRepository: NewStudentRepository,
    private val sectionsListRepository: SectionsListRepository,
) : ViewModel() {

    private val _sectionNames: MutableLiveData<List<String>> = MutableLiveData<List<String>>()
    val sectionNames: LiveData<List<String>> = _sectionNames

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val error: LiveData<Boolean> = _error

    private val _closeScreen: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val closeScreen: LiveData<Boolean> = _closeScreen

    private var disposable: Disposable? = null
    private var getSectionsDisposable: Disposable? = null

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    fun getSections() {
        _isLoading.value = true
        _error.value = false
        getSectionsDisposable = sectionsListRepository
            .getSectionsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ sections ->
                _isLoading.value = false
                _sectionNames.value = sections.map { it.name }
            }, { _error.value = true; _isLoading.value = false })
    }

    fun createStudent(
        fullName: String,
        section: String,
        numberOfLessons: Int,
        wasPayed: Boolean,
        balanceOfLessons: Int,
    ) {
        val newStudent = Student(section, fullName, "", wasPayed, numberOfLessons, balanceOfLessons)
        _isLoading.value = true
        _error.value = false
        disposable = newStudentRepository
            .createStudent(newStudent)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _isLoading.value = false
                _closeScreen.value = true
            }, { _error.value = true; _isLoading.value = false })
    }
}