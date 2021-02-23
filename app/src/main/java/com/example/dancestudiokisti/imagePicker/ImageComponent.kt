package com.example.dancestudiokisti.imagePicker

import com.example.dancestudiokisti.AppComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ImageModule::class], dependencies = [AppComponent::class])
interface ImageComponent {
    fun inject (imageFragment: ImageFragment)
}