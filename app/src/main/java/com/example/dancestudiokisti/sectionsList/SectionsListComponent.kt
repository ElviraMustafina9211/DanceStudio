package com.example.dancestudiokisti.sectionsList

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SectionsListModule::class])
interface SectionsListComponent {
    fun inject(sectionsListFragment: SectionsListFragment)
}
