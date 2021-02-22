package com.example.dancestudiokisti.newSection

import dagger.Module
import dagger.Provides

@Module
class NewSectionModule {

    @Provides
    fun newSectionRepository(): NewSectionRepository {
        return NewSectionRepository()
    }

    @Provides
    fun newSectionViewModelFactory(newSectionRepository: NewSectionRepository): NewSectionViewModelFactory {
        return NewSectionViewModelFactory(newSectionRepository)
    }
}