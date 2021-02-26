package com.example.dancestudiokisti.sectionsList

import com.example.dancestudiokisti.AppComponent
import dagger.Component

@SectionListScope
@Component(modules = [SectionsListModule::class], dependencies = [AppComponent::class])
interface SectionsListComponent {
    fun inject(sectionsListFragment: SectionsListFragment)

    fun sectionListRepository(): SectionsListRepository
}