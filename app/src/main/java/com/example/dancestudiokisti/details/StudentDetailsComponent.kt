package com.example.dancestudiokisti.details

import com.example.dancestudiokisti.AppComponent
import com.example.dancestudiokisti.FragmentScope
import com.example.dancestudiokisti.KeyboardComponent
import dagger.Component

@FragmentScope
@Component(modules = [StudentDetailsModule::class], dependencies = [AppComponent::class, KeyboardComponent::class])
interface StudentDetailsComponent {
    fun inject (studentDetailsFragment: StudentDetailsFragment)
}