package com.example.dancestudiokisti.details

import com.example.dancestudiokisti.Student
import io.reactivex.Completable
import io.reactivex.Single


class StudentDetailsRepository (private val studentsDetailsApi: StudentDetailsApi) {

    fun getStudentDetails(objectId: String): Single<Student> {
        return studentsDetailsApi.getStudentDetails(objectId)
    }

    fun updateStudentDetails(objectId: String, student: Student): Single<Student> {
        return studentsDetailsApi.updateStudent(objectId, student)
    }
    fun deleteStudentDetails(objectId: String): Completable {
        return studentsDetailsApi.deleteStudent(objectId)
    }
}