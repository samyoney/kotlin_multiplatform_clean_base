package data.usecase.enroll

import data.repository.StudentRepository
import kotlinx.coroutines.flow.flow

class FetchStudentsUseCase(private val repository: StudentRepository) {

    operator fun invoke() = flow {
        emit(repository.fetchStudents())
    }
}