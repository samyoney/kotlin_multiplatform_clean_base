package data.usecase.enroll

import org.sam.multiplatfrombase.StudentEntity
import data.model.remote.response.StudentResponse
import data.repository.StudentRepository
import kotlinx.coroutines.flow.flow


class SaveStudentsUseCase(private val studentRepository: StudentRepository) {

    operator fun invoke(students: List<StudentResponse.Student>) = flow {
        val listStudentEntity = students.map {
            StudentEntity(id = it.id.toLong(), name = it.name, birth = it.birth, courseId = null)
        }
        emit(studentRepository.insertListStudent(listStudentEntity))
    }
}