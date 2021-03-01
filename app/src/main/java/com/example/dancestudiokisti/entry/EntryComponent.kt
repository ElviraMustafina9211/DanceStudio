package com.example.dancestudiokisti.entry

import com.example.dancestudiokisti.AppComponent
import com.example.dancestudiokisti.FragmentScope
import dagger.Component

@FragmentScope
@Component(modules = [EntryModule::class], dependencies = [AppComponent::class])
interface EntryComponent {
    fun inject (entryFragment: EntryFragment)
}