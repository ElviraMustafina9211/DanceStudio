package com.example.dancestudiokisti.registration

import com.example.dancestudiokisti.TokenSaver
import com.example.dancestudiokisti.login.LoginApi
import com.example.dancestudiokisti.login.LoginRequest
import com.example.dancestudiokisti.login.User
import io.reactivex.Single

class RegistrationRepositoryImpl(
    private val registrationApi: RegistrationApi,
    private val loginApi: LoginApi,
    private val tokenSaver: TokenSaver
) : RegistrationRepository  {

    override fun createUser(email: String, password: String): Single<User> {
        return registrationApi.newUser(RegistrationRequest(email, password))
            .flatMap { loginApi.login(LoginRequest(email, password)) }
            .doOnSuccess { user -> tokenSaver.token = user.userToken }
    }
}