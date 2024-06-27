package data.usecase.enroll

import data.model.dto.CourseDto
import data.model.dto.toDto
import data.repository.CourseRepository

class GetCoursesUseCase(private val courseRepository: CourseRepository) {

    operator fun invoke(): List<CourseDto> {
        return courseRepository.getListCourse().map {
            it.toDto()
        }
    }
}
