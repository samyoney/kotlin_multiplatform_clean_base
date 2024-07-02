package data.usecase.enroll

import data.repository.StudentRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flowOn
import org.sam.multiplatfrombase.StudentEntity

class AddStudentIntoCourseUseCase(private val studentRepository: StudentRepository) {

    operator fun invoke(id: String, courseId: String) = flow {
        val student = studentRepository.getStudent(id)
        val newStudent = StudentEntity(student.id, student.birth, student.name, courseId)
        emit(studentRepository.updateStudent(newStudent))
    }.flowOn(Dispatchers.IO)
}
