package com.example.dancestudiokisti.sectionsList

import com.example.dancestudiokisti.newSection.Section
import com.example.dancestudiokisti.newSection.SectionsApi
import io.reactivex.Completable
import io.reactivex.Single

class SectionsListRepositoryImpl(private val sectionsApi: SectionsApi) : SectionsListRepository {

    override fun getSectionsList(): Single<List<Section>> {
        return sectionsApi.getSectionsList()
    }

    override fun deleteOneSection(objectId: String): Completable {
        return sectionsApi.deleteOneSection(objectId)
    }
}