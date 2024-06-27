package data.usecase.enroll

import data.repository.CourseRepository
import kotlinx.coroutines.flow.flow

class FetchCoursesUseCase(private val repository: CourseRepository) {

    operator fun invoke() = flow {
        emit(repository.fetchCourses())
    }
}