package com.example.dancestudiokisti.login

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("users/login")
    fun login(@Body loginRequest: LoginRequest): Single<User>
}