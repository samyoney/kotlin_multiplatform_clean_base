package data.usecase.enroll

import data.model.local.StudentEntity
import data.model.remote.response.StudentResponse
import data.repository.StudentRepository


class SaveStudentsUseCase(private val studentRepository: StudentRepository) {

    operator fun invoke(students: List<StudentResponse.Student>) {
        val listStudentEntity = students.map {
            StudentEntity(name = it.name, birth = it.birth)
        }
        studentRepository.insertListStudent(listStudentEntity)
    }
}