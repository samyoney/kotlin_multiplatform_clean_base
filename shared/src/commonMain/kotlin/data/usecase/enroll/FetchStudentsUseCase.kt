package data.usecase.enroll

import data.model.remote.response.StudentResponse
import data.repository.StudentRepository
import framework.network.RequestState
import framework.network.safeFetchApi

class FetchStudentsUseCase(private val repository: StudentRepository) {

    suspend operator fun invoke(): RequestState<StudentResponse> {
        return safeFetchApi { repository.fetchStudents() }
    }
}