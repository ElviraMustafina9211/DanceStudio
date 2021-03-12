package com.example.dancestudiokisti.details

import com.example.dancestudiokisti.Student
import io.reactivex.Completable
import io.reactivex.Single

class StudentDetailsRepositoryImpl (private val studentsDetailsApi: StudentDetailsApi) : StudentDetailsRepository {

    override fun getStudentDetails(objectId: String): Single<Student> {
        return studentsDetailsApi.getStudentDetails(objectId)
    }

    override fun updateStudentDetails(objectId: String, student: Student): Single<Student> {
        return studentsDetailsApi.updateStudent(objectId, student)
    }
    override fun deleteStudentDetails(objectId: String): Completable {
        return studentsDetailsApi.deleteStudent(objectId)
    }
}