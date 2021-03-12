package com.example.dancestudiokisti.login

import com.example.dancestudiokisti.TokenSaver
import io.reactivex.Single

class LoginRepositoryImpl (private val loginApi: LoginApi, private val tokenSaver: TokenSaver) : LoginRepository {

    override fun getUser(login: String, password: String): Single<User> {
        return loginApi.login(LoginRequest(login, password))
            .doOnSuccess { user -> tokenSaver.token = user.userToken }
    }
}