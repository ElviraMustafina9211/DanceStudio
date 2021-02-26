package com.example.dancestudiokisti.imagePicker

import com.example.dancestudiokisti.AppComponent
import com.example.dancestudiokisti.FragmentScope
import dagger.Component

@FragmentScope
@Component(modules = [ImageModule::class], dependencies = [AppComponent::class])
interface ImageComponent {
    fun inject (imageFragment: ImageFragment)
}