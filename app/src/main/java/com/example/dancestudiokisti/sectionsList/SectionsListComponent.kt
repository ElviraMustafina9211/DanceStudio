package com.example.dancestudiokisti.sectionsList

import dagger.Component

@Component(modules = [SectionsListModule::class])
interface SectionsListComponent {
    fun inject(sectionsListFragment: SectionsListFragment)

    fun sectionListRepository(): SectionsListRepository
}