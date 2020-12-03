package com.example.dancestudiokisti.newSection

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewSectionRepository {

    private val sectionsApi: SectionsApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.backendless.com/5DB01662-49AB-46C2-FF61-D1BE9E31E700/BFE4B2B4-C4D0-488E-98CC-31B43E75F466/data/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        sectionsApi = retrofit.create(SectionsApi::class.java)
    }

    fun createSection(section: Section): Single<Section> {
        return sectionsApi.createSection(section)
    }
}