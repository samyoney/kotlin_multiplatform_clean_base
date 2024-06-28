package data.usecase.enroll

import data.model.local.CourseEntity
import data.model.remote.response.CourseResponse
import data.repository.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SaveCoursesUseCase(private val courseRepository: CourseRepository) {

    operator fun invoke(courses: List<CourseResponse.Course>) = flow {
        val listCourseEntity = courses.map {
            CourseEntity(id = it.id, name = it.name, instructor = it.instructor, topics = it.topics)
        }
        emit(courseRepository.insertListCourse(listCourseEntity))
    }.flowOn(Dispatchers.IO)
}