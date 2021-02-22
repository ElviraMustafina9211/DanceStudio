package com.example.dancestudiokisti.list

import dagger.Module
import dagger.Provides

@Module
class StudentsListModule {

    @Provides
    fun studentsListRepository(): StudentsListRepository {
        return StudentsListRepository()
    }

    @Provides
    fun studentsListViewModelFactory (studentsListRepository: StudentsListRepository): StudentsListViewModelFactory {
        return StudentsListViewModelFactory(studentsListRepository)
    }
}