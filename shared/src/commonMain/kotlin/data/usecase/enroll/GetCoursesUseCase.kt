package data.usecase.enroll

import data.model.dto.toDto
import data.repository.CourseRepository
import kotlinx.coroutines.flow.flow

class GetCoursesUseCase(private val courseRepository: CourseRepository) {

    operator fun invoke() = flow {
        emit(courseRepository.getListCourse().map {
            it.toDto()
        })
    }
}
