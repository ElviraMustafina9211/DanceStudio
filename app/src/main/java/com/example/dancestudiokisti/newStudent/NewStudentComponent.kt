package com.example.dancestudiokisti.newStudent

import com.example.dancestudiokisti.AppComponent
import com.example.dancestudiokisti.FragmentScope
import com.example.dancestudiokisti.KeyboardComponent
import com.example.dancestudiokisti.sectionsList.SectionsListComponent
import dagger.Component

@FragmentScope
@Component(
    modules = [NewStudentModule::class],
    dependencies = [SectionsListComponent::class, AppComponent::class, KeyboardComponent::class]
)
interface NewStudentComponent {
    fun inject(newStudentFragment: NewStudentFragment)
}