package com.example.dancestudiokisti.newSection

import com.google.gson.annotations.SerializedName

data class Section(
    @SerializedName("name")
    val name: String,

    @SerializedName("objectId")
    val objectId: String,

    @SerializedName("imageLink")
    val imageLink: String
)