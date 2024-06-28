package data.usecase.enroll

import data.repository.StudentRepository
import kotlinx.coroutines.flow.flow
import data.model.local.StudentEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flowOn

class AddStudentIntoCourseUseCase(private val studentRepository: StudentRepository) {

    operator fun invoke(id: String, courseId: String) = flow {
        val student = studentRepository.getStudent(id)
        val newStudent = StudentEntity(student.id, student.birth, student.name, courseId)
        emit(studentRepository.updateStudent(newStudent))
    }.flowOn(Dispatchers.IO)
}
