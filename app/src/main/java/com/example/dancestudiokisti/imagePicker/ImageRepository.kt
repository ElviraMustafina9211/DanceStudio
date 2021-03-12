package com.example.dancestudiokisti.imagePicker

import io.reactivex.Single

interface ImageRepository {
    fun getImageList(): Single<List<Image>>
}