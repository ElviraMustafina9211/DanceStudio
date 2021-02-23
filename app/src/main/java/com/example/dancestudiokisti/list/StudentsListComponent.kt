package com.example.dancestudiokisti.list

import com.example.dancestudiokisti.AppComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [StudentsListModule::class], dependencies = [AppComponent::class])
interface StudentsListComponent {
    fun inject(studentsListFragment: StudentsListFragment)
}