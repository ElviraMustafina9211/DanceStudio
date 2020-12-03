package com.example.dancestudiokisti.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dancestudiokisti.Student
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class StudentDetailsViewModel(private val studentDetailsRepository: StudentDetailsRepository) :
    ViewModel() {

    private val _detailsLiveData: MutableLiveData<Student> = MutableLiveData<Student>()
    val detailsLiveData: LiveData<Student> = _detailsLiveData

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _closeScreen: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val closeScreen: LiveData<Boolean> = _closeScreen

    private var disposable: Disposable? = null
    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    fun onBalanceMinusClicked() {
        val studentDetails = detailsLiveData.value
        studentDetails?.balanceOfLessons = (studentDetails?.balanceOfLessons ?: 0) - 1
        _detailsLiveData.value = studentDetails
    }

    fun onBalancePlusClicked() {
        val studentDetails = detailsLiveData.value
        studentDetails?.balanceOfLessons = (studentDetails?.balanceOfLessons ?: 0) + 1
        _detailsLiveData.value = studentDetails
    }

    fun onMinusNumberOfLessonsClicked() {
        val studentDetails = detailsLiveData.value
        studentDetails?.numberOfLessons = (studentDetails?.numberOfLessons ?: 0) - 1
        _detailsLiveData.value = studentDetails
    }

    fun onPlusNumberOfLessonsClicked() {
        val studentDetails = detailsLiveData.value
        studentDetails?.numberOfLessons = (studentDetails?.numberOfLessons ?: 0) + 1
        _detailsLiveData.value = studentDetails
    }

    fun onPaymentChanged(isChecked: Boolean) {
        val studentDetails = detailsLiveData.value
        studentDetails?.wasPayed = isChecked
        _detailsLiveData.value = studentDetails
    }

    fun getStudentDetails(objectId: String) {
        _isLoading.value = true
        _error.value = false
        disposable = studentDetailsRepository
            .getStudentDetails(objectId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ details ->
                _isLoading.value = false
                _detailsLiveData.value = details
            }, { _error.value = true; _isLoading.value = false })
    }

    fun updateStudentDetails(numberOfLessons: Int, payment: Boolean, balanceLessons: Int) {
        detailsLiveData.value?.let { student ->
            val saveDetails = Student(student.sectionName, student.fullName, "", payment, numberOfLessons, balanceLessons)
            _isLoading.value = true
            _error.value = false
            disposable = studentDetailsRepository
                .updateStudentDetails(student.objectId, saveDetails)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ details ->
                    _isLoading.value = false
                    _detailsLiveData.value = details
                    _closeScreen.value = true
                }, { _error.value = true; _isLoading.value = false })
        }
    }

    fun deleteStudentDetails() {
        detailsLiveData.value?.let { student ->
            _isLoading.value = true
            _error.value = false
            disposable = studentDetailsRepository
                .deleteStudentDetails(student.objectId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _isLoading.value = false
                    _closeScreen.value = true
                }, { _error.value = true; _isLoading.value = false })
        }
    }
}