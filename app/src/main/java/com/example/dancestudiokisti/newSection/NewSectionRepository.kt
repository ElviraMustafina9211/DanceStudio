package com.example.dancestudiokisti.newSection

import io.reactivex.Single

interface NewSectionRepository {
    fun createSection(section: Section): Single<Section>
}