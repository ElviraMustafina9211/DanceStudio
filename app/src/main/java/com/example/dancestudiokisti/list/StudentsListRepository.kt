package com.example.dancestudiokisti.list

import com.example.dancestudiokisti.Student
import io.reactivex.Single

class StudentsListRepository (private val studentsListApi: StudentsListApi) {

    fun getStudentList(): Single<List<Student>> {
        return studentsListApi.getStudentsList()
    }
}