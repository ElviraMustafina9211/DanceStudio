package com.example.dancestudiokisti.login

import com.example.dancestudiokisti.TokenSaver
import io.reactivex.Single

class LoginRepository (private val loginApi: LoginApi, private val tokenSaver: TokenSaver) {

    fun getUser(login: String, password: String): Single<User> {
        return loginApi.login(LoginRequest(login, password))
            .doOnSuccess { user -> tokenSaver.token = user.userToken }
    }
}