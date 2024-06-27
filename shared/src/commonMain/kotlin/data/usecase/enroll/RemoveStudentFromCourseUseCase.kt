package data.usecase.enroll

import data.repository.StudentRepository
import kotlinx.coroutines.flow.flow
import org.sam.multiplatfrombase.StudentEntity


class RemoveStudentFromCourseUseCase(private val studentRepository: StudentRepository) {

    operator fun invoke(id: String) = flow {
        val student = studentRepository.getStudent(id)
        val newStudent = StudentEntity(student.id, student.birth, student.name, null)
        emit(studentRepository.updateStudent(newStudent))
    }
}
