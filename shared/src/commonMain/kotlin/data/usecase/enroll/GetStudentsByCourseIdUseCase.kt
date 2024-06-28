package data.usecase.enroll

import data.model.dto.toDto
import data.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetStudentsByCourseIdUseCase(private val studentRepository: StudentRepository) {

    operator fun invoke(courseId: String) = flow {
        emit(studentRepository.getStudentByCourseId(courseId).map {
            it.toDto()
        })
    }.flowOn(Dispatchers.IO)
}
