package com.example.dancestudiokisti.list

import com.example.dancestudiokisti.AppComponent
import com.example.dancestudiokisti.FragmentScope
import dagger.Component

@FragmentScope
@Component(modules = [StudentsListModule::class], dependencies = [AppComponent::class])
interface StudentsListComponent {
    fun inject(studentsListFragment: StudentsListFragment)
}