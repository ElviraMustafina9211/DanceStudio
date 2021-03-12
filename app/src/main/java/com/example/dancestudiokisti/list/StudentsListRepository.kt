package com.example.dancestudiokisti.list

import com.example.dancestudiokisti.Student
import io.reactivex.Single

interface StudentsListRepository {
    fun getStudentList(): Single<List<Student>>
}