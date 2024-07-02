package data.usecase.enroll

import data.model.dto.toDto
import data.repository.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetCoursesUseCase(private val courseRepository: CourseRepository) {

    operator fun invoke() = flow {
        emit(courseRepository.getListCourse().map {
            it.toDto()
        })
    }.flowOn(Dispatchers.IO)
}
