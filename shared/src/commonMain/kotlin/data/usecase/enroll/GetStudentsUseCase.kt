package data.usecase.enroll

import data.model.dto.toDto
import data.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetStudentsUseCase(private val studentRepository: StudentRepository) {

    operator fun invoke() = flow {
        emit(studentRepository.getListStudent().map {
            it.toDto()
        })
    }.flowOn(Dispatchers.IO)
}
