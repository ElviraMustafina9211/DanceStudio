package com.example.dancestudiokisti.login

import io.reactivex.Single

interface LoginRepository {
    fun getUser(login: String, password: String): Single<User>
}