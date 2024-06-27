package data.usecase.enroll

import org.sam.multiplatfrombase.CourseEntity
import data.model.remote.response.CourseResponse
import data.repository.CourseRepository
import kotlinx.coroutines.flow.flow

class SaveCoursesUseCase(private val courseRepository: CourseRepository) {

    operator fun invoke(courses: List<CourseResponse.Course>) = flow {
        val listCourseEntity = courses.map {
            CourseEntity(id = it.id, name = it.name, instructor = it.instructor, topics = it.topics.joinToString(";"))
        }
        emit(courseRepository.insertListCourse(listCourseEntity))
    }
}