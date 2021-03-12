package com.example.dancestudiokisti.list

import com.example.dancestudiokisti.Student
import io.reactivex.Single

class StudentsListRepositoryImpl (private val studentsListApi: StudentsListApi) : StudentsListRepository {

    override fun getStudentList(): Single<List<Student>> {
        return studentsListApi.getStudentsList()
    }
}