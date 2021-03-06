package com.example.dancestudiokisti.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dancestudiokisti.login.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class RegistrationViewModel(private val registrationRepository: RegistrationRepository) :
    ViewModel() {

    private val _newUser: MutableLiveData<User> = MutableLiveData<User>()
    val newUser: LiveData<User> = _newUser

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error: MutableLiveData<String?> = MutableLiveData<String?>()
    val error: MutableLiveData<String?> = _error

    private var disposable: Disposable? = null
    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    fun createNewUser(userEmail: String, userPassword: String) {
        _isLoading.value = true
        _error.value = null
        disposable = registrationRepository
            .createUser(userEmail, userPassword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { _isLoading.value = false }
            .subscribe({ user: User ->
                _newUser.value = user
            }, { error ->
                _error.value =
                    if (error is HttpException && (error.code() == 401 || error.code() == 400)) {
                        "Некорректный логин или пароль"
                    } else if (error is SocketTimeoutException || error is UnknownHostException) {
                        "Отсутствует подключение к интернету"
                    } else {
                        "Неизвестная ошибка"
                    }
            })
    }
}