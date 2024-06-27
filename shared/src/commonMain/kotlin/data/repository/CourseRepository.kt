package data.repository

import data.local.dao.CourseDao
import data.model.local.CourseEntity
import data.remote.service.CourseService

class CourseRepository(
    private val service: CourseService,
    private val courseDao: CourseDao,
) {
    suspend fun fetchCourses() = service.fetch()

    fun insertListCourse(courseEntities: List<CourseEntity>) = courseDao.insertListCourse(courseEntities)

    fun getListCourse() = courseDao.getListCourse()
}