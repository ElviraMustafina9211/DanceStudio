package com.example.dancestudiokisti

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("sectionName")
    val sectionName: String,

    @SerializedName("fullName")
    val fullName: String,

    @SerializedName("objectId")
    val objectId: String,

    @SerializedName("wasPayed")
    var wasPayed: Boolean,

    @SerializedName("numberOfLessons")
    var numberOfLessons: Int,

    @SerializedName("balanceOfLessons")
    var balanceOfLessons: Int
)