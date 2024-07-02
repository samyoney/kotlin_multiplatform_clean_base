package data.repository

import data.local.dao.CourseDao
import data.remote.service.CourseService
import org.sam.multiplatfrombase.CourseEntity

class CourseRepository(
    private val service: CourseService,
    private val courseDao: CourseDao,
) {
    suspend fun fetchCourses() = service.fetch()

    suspend fun insertListCourse(courseEntities: List<CourseEntity>) = courseDao.insertListCourse(courseEntities)

    suspend fun getListCourse() = courseDao.getListCourse()
}