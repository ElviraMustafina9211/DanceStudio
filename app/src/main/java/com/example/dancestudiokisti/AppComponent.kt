package com.example.dancestudiokisti

import com.example.dancestudiokisti.details.StudentDetailsFragment
import com.example.dancestudiokisti.imagePicker.ImageFragment
import com.example.dancestudiokisti.list.StudentsListFragment
import com.example.dancestudiokisti.login.LoginFragment
import com.example.dancestudiokisti.newSection.NewSectionFragment
import com.example.dancestudiokisti.newStudent.NewStudentFragment
import com.example.dancestudiokisti.sectionsList.SectionsListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(studentDetailsFragment: StudentDetailsFragment)
    fun inject(studentsListFragment: StudentsListFragment)
    fun inject(newStudentFragment: NewStudentFragment)
    fun inject(newSectionFragment: NewSectionFragment)
    fun inject(sectionsListFragment: SectionsListFragment)
    fun inject(imageFragment: ImageFragment)
    fun inject(loginFragment: LoginFragment)
}
