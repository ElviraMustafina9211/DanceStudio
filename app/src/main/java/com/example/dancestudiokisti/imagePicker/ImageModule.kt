package com.example.dancestudiokisti.imagePicker

import dagger.Module
import dagger.Provides

@Module
class ImageModule {

    @Provides
    fun imageRepository(): ImageRepository {
        return ImageRepository()
    }

    @Provides
    fun imageViewModelFactory(imageRepository: ImageRepository): ImageViewModelFactory {
        return ImageViewModelFactory(imageRepository)
    }
}