package com.example.dancestudiokisti.imagePicker

import io.reactivex.Single

class ImageRepositoryImpl (private val imageApi: ImageApi) :ImageRepository {

    override fun getImageList(): Single<List<Image>> {
        return imageApi.getImageList()
    }
}