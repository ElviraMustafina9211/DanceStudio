package com.example.dancestudiokisti.imagePicker

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ImageModule::class])
interface ImageComponent {
    fun inject (imageFragment: ImageFragment)
}