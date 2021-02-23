package com.example.dancestudiokisti.newSection

import com.example.dancestudiokisti.AppComponent
import com.example.dancestudiokisti.KeyboardComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NewSectionModule::class], dependencies = [AppComponent::class, KeyboardComponent::class])
interface NewSectionComponent {
    fun inject (newSectionFragment: NewSectionFragment)
}