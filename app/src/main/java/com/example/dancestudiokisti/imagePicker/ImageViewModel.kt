package com.example.dancestudiokisti.imagePicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ImageViewModel (private val imageRepository: ImageRepository) : ViewModel() {

    private val _imagesLiveData: MutableLiveData<List<Image>> = MutableLiveData<List<Image>>()
    val imagesLiveData: LiveData<List<Image>> = _imagesLiveData

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _closeScreen: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val closeScreen: LiveData<Boolean> = _closeScreen

    private var disposable: Disposable? = null
    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    fun getImageList() {
        _isLoading.value = true
        _error.value = false
        disposable = imageRepository
            .getImageList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ images ->
                _isLoading.value = false
                _imagesLiveData.value = images
            }, { _error.value = true; _isLoading.value = false })
    }
}