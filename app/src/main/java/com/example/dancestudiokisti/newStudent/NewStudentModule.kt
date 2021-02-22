package com.example.dancestudiokisti.newStudent

import com.example.dancestudiokisti.sectionsList.SectionsListRepository
import dagger.Module
import dagger.Provides

@Module
class NewStudentModule {
    @Provides
    fun newStudentRepository(): NewStudentRepository {
        return NewStudentRepository()
    }

    @Provides
    fun newStudentViewModelFactory(
        newStudentRepository: NewStudentRepository,
        sectionsListRepository: SectionsListRepository
    ): NewStudentViewModelFactory {
        return NewStudentViewModelFactory(newStudentRepository, sectionsListRepository)
    }
}