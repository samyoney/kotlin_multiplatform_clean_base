package data.usecase.enroll

import data.repository.StudentRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flowOn


class RemoveStudentFromCourseUseCase(private val studentRepository: StudentRepository) {

    operator fun invoke(id: String) = flow {
        val student = studentRepository.getStudent(id)
        val removeStudent = student.copy(
            id = student.id,
            birth = student.birth,
            name = student.name,
            courseId = null
        )
        emit(studentRepository.updateStudent(removeStudent))
    }.flowOn(Dispatchers.IO)
}
