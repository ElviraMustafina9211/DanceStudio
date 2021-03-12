package com.example.dancestudiokisti.newSection

import io.reactivex.Single

class NewSectionRepositoryImpl (private val sectionsApi: SectionsApi) : NewSectionRepository {

    override fun createSection(section: Section): Single<Section> {
        return sectionsApi.createSection(section)
    }
}