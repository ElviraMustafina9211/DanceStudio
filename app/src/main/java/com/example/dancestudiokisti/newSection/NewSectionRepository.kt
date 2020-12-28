package com.example.dancestudiokisti.newSection

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewSectionRepository {

    private val sectionsApi: SectionsApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.backendless.com/49B0F014-9A2B-0420-FFBA-A5382B5FDA00/D584F591-91A7-4457-AB0D-E67686CC207A/data/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        sectionsApi = retrofit.create(SectionsApi::class.java)
    }

    fun createSection(section: Section): Single<Section> {
        return sectionsApi.createSection(section)
    }
}