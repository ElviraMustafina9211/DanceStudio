package com.example.dancestudiokisti.sectionsList

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class SectionsListModule (private val context: Context) {
    @Provides
    fun sectionsListRepository(): SectionsListRepository {
        return SectionsListRepository()
    }

    @Provides
    fun sectionsListViewModelFactory(sectionsListRepository: SectionsListRepository): SectionsListViewModelFactory {
        return SectionsListViewModelFactory(sectionsListRepository)
    }
}