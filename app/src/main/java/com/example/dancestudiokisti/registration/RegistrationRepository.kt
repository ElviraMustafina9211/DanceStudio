package com.example.dancestudiokisti.registration

import com.example.dancestudiokisti.login.User
import io.reactivex.Single

interface RegistrationRepository {
    fun createUser(email: String, password: String): Single<User>
}