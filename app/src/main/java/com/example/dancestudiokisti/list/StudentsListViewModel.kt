package com.example.dancestudiokisti.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dancestudiokisti.Student
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class StudentsListViewModel(private val studentsListRepository: StudentsListRepository) : ViewModel() {

    private val _studentsList: MutableLiveData<List<Student>> = MutableLiveData<List<Student>>()
    val studentsList: LiveData<List<Student>> = _studentsList

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private var disposable: Disposable? = null
    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    fun getFullNames(sectionName: String?) {
        _isLoading.value = true
        _error.value = false
        disposable = studentsListRepository
            .getStudentList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ students: List<Student> ->
                _isLoading.value = false
                _studentsList.value = students.filter { it.sectionName == sectionName }
            }, { _error.value = true; _isLoading.value = false })
    }
}