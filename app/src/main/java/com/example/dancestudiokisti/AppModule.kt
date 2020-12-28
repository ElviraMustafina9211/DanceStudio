package com.example.dancestudiokisti

import com.example.dancestudiokisti.details.StudentDetailsRepository
import com.example.dancestudiokisti.details.StudentDetailsViewModel
import com.example.dancestudiokisti.imagePicker.ImageRepository
import com.example.dancestudiokisti.imagePicker.ImageViewModel
import com.example.dancestudiokisti.list.StudentsListRepository
import com.example.dancestudiokisti.list.StudentsListViewModel
import com.example.dancestudiokisti.newSection.NewSectionRepository
import com.example.dancestudiokisti.newSection.NewSectionViewModel
import com.example.dancestudiokisti.newStudent.NewStudentRepository
import com.example.dancestudiokisti.newStudent.NewStudentViewModel
import com.example.dancestudiokisti.sectionsList.SectionsListRepository
import com.example.dancestudiokisti.sectionsList.SectionsListViewModel

import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun studentDetailRepository(): StudentDetailsRepository {
        return StudentDetailsRepository()
    }

    @Provides
    fun studentDetailsViewModel(studentDetailRepository: StudentDetailsRepository): StudentDetailsViewModel {
        return StudentDetailsViewModel(studentDetailRepository)
    }

    @Provides
    fun studentsListRepository(): StudentsListRepository {
        return StudentsListRepository()
    }

    @Provides
    fun studentsListViewModel(studentsListRepository: StudentsListRepository): StudentsListViewModel {
        return StudentsListViewModel(studentsListRepository)
    }

    @Provides
    fun newStudentRepository(): NewStudentRepository {
        return NewStudentRepository()
    }

    @Provides
    fun newStudentViewModel(newStudentRepository: NewStudentRepository): NewStudentViewModel {
        return NewStudentViewModel(newStudentRepository, sectionsListRepository())
    }

    @Provides
    fun newSectionRepository(): NewSectionRepository {
        return NewSectionRepository()
    }

    @Provides
    fun newSectionViewModel(newSectionRepository: NewSectionRepository): NewSectionViewModel {
        return NewSectionViewModel(newSectionRepository)
    }

    @Provides
    fun sectionsListRepository(): SectionsListRepository {
        return SectionsListRepository()
    }

    @Provides
    fun sectionsListViewModel(sectionsListRepository: SectionsListRepository): SectionsListViewModel {
        return SectionsListViewModel(sectionsListRepository)
    }

    @Provides
    fun imageRepository(): ImageRepository {
        return ImageRepository()
    }

    @Provides
    fun imageViewModel(imageRepository: ImageRepository): ImageViewModel {
        return ImageViewModel(imageRepository)
    }
}