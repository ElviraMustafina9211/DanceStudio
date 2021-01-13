package com.example.dancestudiokisti.imagePicker

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ImageRepository {

    private val imageApi: ImageApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.backendless.com/BC19FC06-5B9B-D587-FF97-395A88AA5A00/56C210DD-8C5F-4AA8-95A4-27CA82766F09/data/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        imageApi = retrofit.create(ImageApi::class.java)
    }

    fun getImageList(): Single<List<Image>> {
        return imageApi.getImageList()
    }
}