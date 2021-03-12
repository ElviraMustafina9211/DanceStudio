package com.example.dancestudiokisti.sectionsList

import com.example.dancestudiokisti.newSection.Section
import io.reactivex.Completable
import io.reactivex.Single

interface SectionsListRepository {
    fun getSectionsList(): Single<List<Section>>
    fun deleteOneSection(objectId: String): Completable
}