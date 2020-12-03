package com.example.dancestudiokisti

import com.example.dancestudiokisti.details.StudentDetailsActivity
import com.example.dancestudiokisti.imagePicker.ImageActivity
import com.example.dancestudiokisti.list.StudentsListActivity
import com.example.dancestudiokisti.newSection.NewSectionActivity
import com.example.dancestudiokisti.newStudent.NewStudentActivity
import com.example.dancestudiokisti.sectionsList.SectionsListActivity

import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(studentDetailActivity: StudentDetailsActivity)
    fun inject(studentListActivity: StudentsListActivity)
    fun inject(newStudentActivity: NewStudentActivity)
    fun inject(newStudentActivity: NewSectionActivity)
    fun inject(sectionsListActivity: SectionsListActivity)
    fun inject(imageActivity: ImageActivity)
}
