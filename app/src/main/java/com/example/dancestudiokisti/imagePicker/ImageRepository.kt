package com.example.dancestudiokisti.imagePicker

import io.reactivex.Single

class ImageRepository (private val imageApi: ImageApi) {

    fun getImageList(): Single<List<Image>> {
        return imageApi.getImageList()
    }
}