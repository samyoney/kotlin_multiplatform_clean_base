package data.usecase.enroll

import data.model.dto.toDto
import data.repository.StudentRepository
import kotlinx.coroutines.flow.flow

class GetStudentsByCourseIdUseCase(private val studentRepository: StudentRepository) {

    operator fun invoke(courseId: String) = flow {
        emit(studentRepository.getStudentByCourseId(courseId).map {
            it.toDto()
        })
    }
}
