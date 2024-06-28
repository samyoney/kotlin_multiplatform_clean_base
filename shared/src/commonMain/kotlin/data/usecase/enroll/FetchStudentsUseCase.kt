package data.usecase.enroll

import data.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FetchStudentsUseCase(private val repository: StudentRepository) {

    operator fun invoke() = flow {
        emit(repository.fetchStudents())
    }.flowOn(Dispatchers.IO)
}