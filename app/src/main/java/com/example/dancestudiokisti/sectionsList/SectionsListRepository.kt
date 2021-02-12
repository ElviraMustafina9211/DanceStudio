package com.example.dancestudiokisti.sectionsList

import com.example.dancestudiokisti.newSection.Section
import com.example.dancestudiokisti.newSection.SectionsApi
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SectionsListRepository {
    private val sectionsApi: SectionsApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.backendless.com/FFE00D00-0065-AC54-FF29-8E6A4D2D4200/1E545F28-B2D8-43E5-9BF3-0E1E350F051D/data/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        sectionsApi = retrofit.create(SectionsApi::class.java)
    }

    fun getSectionsList(): Single<List<Section>> {
        return sectionsApi.getSectionsList()
    }

    fun deleteOneSection(objectId: String): Completable {
        return sectionsApi.deleteOneSection(objectId)
    }
}