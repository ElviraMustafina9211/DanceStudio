package com.example.dancestudiokisti.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dancestudiokisti.R
import com.example.dancestudiokisti.utils.StringResources
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val stringResources: StringResources
) : ViewModel() {

    private val _user: MutableLiveData<User> = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error: MutableLiveData<String> = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private var disposable: Disposable? = null
    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    fun getUser(userEmail: String, userPassword: String) {
        _isLoading.value = true
        _error.value = null
        disposable = loginRepository
            .getUser(userEmail, userPassword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { _isLoading.value = false }
            .subscribe({ user: User ->
                _user.value = user
            }, { error ->
                _error.value = if (error is HttpException && (error.code() == 401 || error.code() == 400)) {
                    stringResources.get(R.string.incorrect_login_or_password)
                } else if (error is SocketTimeoutException || error is UnknownHostException) {
                    "Отсутствует подключение к интернету"
                } else {
                    "Неизвестная ошибка"
                }
            })
    }
}