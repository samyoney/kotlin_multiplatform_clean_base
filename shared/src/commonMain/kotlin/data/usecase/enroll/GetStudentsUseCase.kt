package data.usecase.enroll

import data.model.dto.StudentDto
import data.model.dto.toDto
import data.repository.StudentRepository

class GetStudentsUseCase(private val studentRepository: StudentRepository) {

    operator fun invoke(): List<StudentDto> {
        return studentRepository.getListStudent().map {
            it.toDto()
        }
    }
}
