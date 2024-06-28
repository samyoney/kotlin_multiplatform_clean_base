package data.usecase.enroll

import data.repository.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FetchCoursesUseCase(private val repository: CourseRepository) {

    operator fun invoke() = flow {
        emit(repository.fetchCourses())
    }.flowOn(Dispatchers.IO)
}