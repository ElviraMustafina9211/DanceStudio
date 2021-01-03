package com.example.dancestudiokisti

import com.example.dancestudiokisti.details.StudentDetailsFragment
import com.example.dancestudiokisti.imagePicker.ImageFragment
import com.example.dancestudiokisti.list.StudentsListFragment
import com.example.dancestudiokisti.newSection.NewSectionFragment
import com.example.dancestudiokisti.newStudent.NewStudentFragment
import com.example.dancestudiokisti.sectionsList.SectionsListFragment
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(studentDetailFragment: StudentDetailsFragment)
    fun inject(studentListFragment: StudentsListFragment)
    fun inject(newStudentFragment: NewStudentFragment)
    fun inject(newStudentFragment: NewSectionFragment)
    fun inject(sectionsListFragment: SectionsListFragment)
    fun inject(imageFragment: ImageFragment)
}
