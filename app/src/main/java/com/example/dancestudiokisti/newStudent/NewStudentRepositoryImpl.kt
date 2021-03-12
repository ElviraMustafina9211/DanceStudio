package com.example.dancestudiokisti.newStudent

import com.example.dancestudiokisti.Student
import com.example.dancestudiokisti.list.StudentsListApi
import io.reactivex.Single

class NewStudentRepositoryImpl (private val studentsListApi: StudentsListApi) : NewStudentRepository {

    override fun createStudent(student: Student): Single<Student> {
        return studentsListApi.createStudent(student)
    }
}