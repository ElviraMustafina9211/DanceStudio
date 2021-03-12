package com.example.dancestudiokisti.details

import com.example.dancestudiokisti.Student
import io.reactivex.Completable
import io.reactivex.Single


interface StudentDetailsRepository {
    fun getStudentDetails(objectId: String): Single<Student>
    fun updateStudentDetails(objectId: String, student: Student): Single<Student>
    fun deleteStudentDetails(objectId: String): Completable
}