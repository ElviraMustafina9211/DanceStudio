package com.example.dancestudiokisti.details

import dagger.Module
import dagger.Provides

@Module
class StudentDetailsModule {

    @Provides
    fun studentDetailsRepository(): StudentDetailsRepository {
        return StudentDetailsRepository()
    }

    @Provides
    fun studentDetailsViewModelFactory(studentDetailsRepository: StudentDetailsRepository): StudentDetailsViewModelFactory {
        return StudentDetailsViewModelFactory(studentDetailsRepository)
    }
}