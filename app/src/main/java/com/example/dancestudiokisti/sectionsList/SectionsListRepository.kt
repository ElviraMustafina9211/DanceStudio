package com.example.dancestudiokisti.sectionsList

import com.example.dancestudiokisti.newSection.Section
import com.example.dancestudiokisti.newSection.SectionsApi
import io.reactivex.Completable
import io.reactivex.Single

class SectionsListRepository (private val sectionsApi: SectionsApi) {

    fun getSectionsList(): Single<List<Section>> {
        return sectionsApi.getSectionsList()
    }

    fun deleteOneSection(objectId: String): Completable {
        return sectionsApi.deleteOneSection(objectId)
    }
}