package com.example.dancestudiokisti.login

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    val email: String,

    @SerializedName("user-token")
    val userToken: String,

    @SerializedName ("objectId")
    val objectId: String?
)