package com.example.dancestudiokisti.sectionsList

import com.example.dancestudiokisti.AppComponent
import dagger.Component

@Component(modules = [SectionsListModule::class], dependencies = [AppComponent::class])
interface SectionsListComponent {
    fun inject(sectionsListFragment: SectionsListFragment)

    fun sectionListRepository(): SectionsListRepository
}