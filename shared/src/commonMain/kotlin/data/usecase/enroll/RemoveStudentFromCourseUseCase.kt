package data.usecase.enroll

import data.repository.StudentRepository


class RemoveStudentFromCourseUseCase(private val studentRepository: StudentRepository) {

    operator fun invoke(id: String) {
        val student = studentRepository.getStudent(id)
        student.courseId = null
        studentRepository.updateStudent(student)
    }
}
