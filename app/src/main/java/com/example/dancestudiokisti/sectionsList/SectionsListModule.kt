package com.example.dancestudiokisti.sectionsList

import dagger.Module
import dagger.Provides

@Module
class SectionsListModule {

    @Provides
    fun sectionsListRepository(): SectionsListRepository {
        return SectionsListRepository()
    }

    @Provides
    fun sectionsListViewModelFactory (sectionsListRepository: SectionsListRepository): SectionsListViewModelFactory {
        return SectionsListViewModelFactory (sectionsListRepository)
    }
}