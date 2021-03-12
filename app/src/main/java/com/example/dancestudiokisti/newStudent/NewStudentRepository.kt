package com.example.dancestudiokisti.newStudent

import com.example.dancestudiokisti.Student
import io.reactivex.Single

interface NewStudentRepository {
    fun createStudent(student: Student): Single<Student>
}