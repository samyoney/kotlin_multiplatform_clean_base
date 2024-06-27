package data.usecase.enroll

import data.model.remote.response.CourseResponse
import data.repository.CourseRepository
import framework.network.RequestState
import framework.network.safeFetchApi

class FetchCoursesUseCase(private val repository: CourseRepository) {

    suspend operator fun invoke(): RequestState<CourseResponse> {
        return safeFetchApi { repository.fetchCourses() }
    }
}