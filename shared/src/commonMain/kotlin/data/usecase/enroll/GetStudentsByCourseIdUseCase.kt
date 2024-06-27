package data.usecase.enroll

import data.model.dto.StudentDto
import data.model.dto.toDto
import data.repository.StudentRepository

class GetStudentsByCourseIdUseCase(private val studentRepository: StudentRepository) {

    operator fun invoke(courseId: String): List<StudentDto> {
        return studentRepository.getStudentByCourseId(courseId).map {
            it.toDto()
        }
    }
}
