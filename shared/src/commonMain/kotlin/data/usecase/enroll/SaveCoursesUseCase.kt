package data.usecase.enroll

import data.model.local.CourseEntity
import data.model.remote.response.CourseResponse
import data.repository.CourseRepository

class SaveCoursesUseCase(private val courseRepository: CourseRepository) {

    operator fun invoke(courses: List<CourseResponse.Course>) {
        val listCourseEntity = courses.map {
            CourseEntity(id = it.id, name = it.name, instructor = it.instructor, topics = it.topics)
        }
        courseRepository.insertListCourse(listCourseEntity)
    }
}