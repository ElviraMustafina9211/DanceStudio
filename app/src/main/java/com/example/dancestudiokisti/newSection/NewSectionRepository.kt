package com.example.dancestudiokisti.newSection

import io.reactivex.Single

class NewSectionRepository (private val sectionsApi: SectionsApi) {

    fun createSection(section: Section): Single<Section> {
        return sectionsApi.createSection(section)
    }
}