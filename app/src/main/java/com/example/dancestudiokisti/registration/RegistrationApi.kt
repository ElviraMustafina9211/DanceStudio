package com.example.dancestudiokisti.registration

import com.example.dancestudiokisti.login.User
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationApi {
    @POST("users/register")
    fun newUser(@Body registrationRequest: RegistrationRequest): Single<User>
}