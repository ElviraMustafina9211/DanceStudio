package com.example.dancestudiokisti.login

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("login")
    val email: String,

    @SerializedName("password")
    val password: String
)