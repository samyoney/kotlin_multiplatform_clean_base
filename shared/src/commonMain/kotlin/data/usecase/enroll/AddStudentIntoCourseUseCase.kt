package data.usecase.enroll

import data.repository.StudentRepository

class AddStudentIntoCourseUseCase(private val studentRepository: StudentRepository) {

    operator fun invoke(id: String, courseId: String) {
        val student = studentRepository.getStudent(id)
        student.courseId = courseId
        studentRepository.updateStudent(student)
    }
}
