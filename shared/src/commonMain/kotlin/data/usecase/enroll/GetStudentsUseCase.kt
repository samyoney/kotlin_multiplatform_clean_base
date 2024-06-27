package data.usecase.enroll

import data.model.dto.toDto
import data.repository.StudentRepository
import kotlinx.coroutines.flow.flow

class GetStudentsUseCase(private val studentRepository: StudentRepository) {

    operator fun invoke() = flow {
        emit(studentRepository.getListStudent().map {
            it.toDto()
        })
    }
}
