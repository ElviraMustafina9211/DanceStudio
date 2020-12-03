package com.example.dancestudiokisti.imagePicker

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ImageRepository {

    private val imageApi: ImageApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.backendless.com/5DB01662-49AB-46C2-FF61-D1BE9E31E700/FCA93BA4-4832-4000-87B1-2A27B27356C4/data/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        imageApi = retrofit.create(ImageApi::class.java)
    }

    fun getImageList(): Single<List<Image>> {
        return imageApi.getImageList()
    }
}