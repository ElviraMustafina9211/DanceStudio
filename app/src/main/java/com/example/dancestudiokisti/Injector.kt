package com.example.dancestudiokisti

import com.example.dancestudiokisti.details.DaggerStudentDetailsComponent
import com.example.dancestudiokisti.details.StudentDetailsComponent
import com.example.dancestudiokisti.imagePicker.DaggerImageComponent
import com.example.dancestudiokisti.imagePicker.ImageComponent
import com.example.dancestudiokisti.list.DaggerStudentsListComponent
import com.example.dancestudiokisti.list.StudentsListComponent
import com.example.dancestudiokisti.newSection.DaggerNewSectionComponent
import com.example.dancestudiokisti.newSection.NewSectionComponent
import com.example.dancestudiokisti.newStudent.DaggerNewStudentComponent
import com.example.dancestudiokisti.newStudent.NewStudentComponent
import com.example.dancestudiokisti.sectionsList.DaggerSectionsListComponent
import com.example.dancestudiokisti.sectionsList.SectionsListComponent

object Injector {

    private val keyboardComponent: KeyboardComponent by lazy {
        DaggerKeyboardComponent.builder().build()
    }

    val instance: KeyboardComponent by lazy { DaggerKeyboardComponent.create() }

    val sectionsList: SectionsListComponent by lazy {
        DaggerSectionsListComponent.builder().build()
    }

    val studentsList: StudentsListComponent by lazy {
        DaggerStudentsListComponent.builder().build()
    }

    val studentDetailsComponent: StudentDetailsComponent by lazy {
        DaggerStudentDetailsComponent.builder().keyboardComponent(keyboardComponent).build()
    }

    val newSectionComponent: NewSectionComponent by lazy {
        DaggerNewSectionComponent.builder().keyboardComponent(keyboardComponent).build()
    }

    val newStudentComponent: NewStudentComponent by lazy {
        DaggerNewStudentComponent.builder().sectionsListComponent(sectionsList).keyboardComponent(keyboardComponent).build()
    }

    val imageComponent: ImageComponent by lazy {
        DaggerImageComponent.builder().build()
    }
}