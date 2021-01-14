package com.example.dancestudiokisti

import com.example.dancestudiokisti.details.StudentDetailsRepository
import com.example.dancestudiokisti.details.StudentDetailsViewModelFactory
import com.example.dancestudiokisti.imagePicker.ImageRepository
import com.example.dancestudiokisti.imagePicker.ImageViewModelFactory
import com.example.dancestudiokisti.list.StudentsListRepository
import com.example.dancestudiokisti.list.StudentsListViewModelFactory
import com.example.dancestudiokisti.newSection.NewSectionRepository
import com.example.dancestudiokisti.newSection.NewSectionViewModelFactory
import com.example.dancestudiokisti.newStudent.NewStudentRepository
import com.example.dancestudiokisti.newStudent.NewStudentViewModelFactory
import com.example.dancestudiokisti.sectionsList.SectionsListRepository
import com.example.dancestudiokisti.sectionsList.SectionsListViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun studentDetailsRepository(): StudentDetailsRepository {
        return StudentDetailsRepository()
    }

    @Provides
    fun studentDetailsViewModelFactory(studentDetailsRepository: StudentDetailsRepository): StudentDetailsViewModelFactory {
        return StudentDetailsViewModelFactory(studentDetailsRepository)
    }

    @Provides
    fun studentsListRepository(): StudentsListRepository {
        return StudentsListRepository()
    }

    @Provides
    fun studentsListViewModelFactory (studentsListRepository: StudentsListRepository): StudentsListViewModelFactory {
        return StudentsListViewModelFactory(studentsListRepository)
    }

    @Provides
    fun newStudentRepository(): NewStudentRepository {
        return NewStudentRepository()
    }

    @Provides
    fun newStudentViewModelFactory(newStudentRepository: NewStudentRepository): NewStudentViewModelFactory {
        return NewStudentViewModelFactory(newStudentRepository, sectionsListRepository())
    }

    @Provides
    fun newSectionRepository(): NewSectionRepository {
        return NewSectionRepository()
    }

    @Provides
    fun newSectionViewModelFactory(newSectionRepository: NewSectionRepository): NewSectionViewModelFactory {
        return NewSectionViewModelFactory(newSectionRepository)
    }

    @Provides
    fun sectionsListRepository(): SectionsListRepository {
        return SectionsListRepository()
    }

    @Provides
    fun sectionsListViewModelFactory (sectionsListRepository: SectionsListRepository): SectionsListViewModelFactory {
        return SectionsListViewModelFactory (sectionsListRepository)
    }

    @Provides
    fun imageRepository(): ImageRepository {
        return ImageRepository()
    }

    @Provides
    fun imageViewModelFactory(imageRepository: ImageRepository): ImageViewModelFactory {
        return ImageViewModelFactory(imageRepository)
    }

    @Provides
    @Singleton
    fun keyboard(): Keyboard {
        return KeyboardImpl()
    }
}