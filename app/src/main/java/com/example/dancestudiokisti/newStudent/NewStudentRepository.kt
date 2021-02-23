package com.example.dancestudiokisti.newStudent

import com.example.dancestudiokisti.Student
import com.example.dancestudiokisti.list.StudentsListApi
import io.reactivex.Single

class NewStudentRepository (private val studentsListApi: StudentsListApi) {

    fun createStudent(student: Student): Single<Student> {
        return studentsListApi.createStudent(student)
    }
}