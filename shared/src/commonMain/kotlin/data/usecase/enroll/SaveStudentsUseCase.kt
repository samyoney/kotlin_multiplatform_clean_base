package data.usecase.enroll

import data.model.remote.response.StudentResponse
import data.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.sam.multiplatfrombase.StudentEntity


class SaveStudentsUseCase(private val studentRepository: StudentRepository) {

    operator fun invoke(students: List<StudentResponse.Student>) = flow {
        val listStudentEntity = students.map {
            StudentEntity(id = it.id.toLong(), courseId = null, name = it.name, birth = it.birth)
        }
        emit(studentRepository.insertListStudent(listStudentEntity))
    }.flowOn(Dispatchers.IO)
}