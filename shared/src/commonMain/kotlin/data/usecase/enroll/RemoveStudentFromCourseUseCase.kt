package data.usecase.enroll

import data.model.dto.toEntity
import data.repository.StudentRepository
import kotlinx.coroutines.flow.flow
import data.model.local.StudentEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flowOn


class RemoveStudentFromCourseUseCase(private val studentRepository: StudentRepository) {

    operator fun invoke(id: String) = flow {
        val student = studentRepository.getStudent(id)
        emit(studentRepository.updateStudent(student.toEntity()))
    }.flowOn(Dispatchers.IO)
}
