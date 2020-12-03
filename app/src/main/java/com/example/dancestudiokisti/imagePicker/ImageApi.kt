package com.example.dancestudiokisti.imagePicker

import io.reactivex.Single
import retrofit2.http.GET

interface ImageApi {
    @GET("Image")
    fun getImageList(): Single<List<Image>>
}
