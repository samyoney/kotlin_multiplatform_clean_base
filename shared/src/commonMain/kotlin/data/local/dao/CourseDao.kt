package data.local.dao

import framework.network.safeQuery
import org.sam.multiplatfrom_base.AppDatabase
import org.sam.multiplatfrombase.CourseEntity

class CourseDao(private val database: AppDatabase) {
    suspend fun insertListCourse(courseEntities: List<CourseEntity>) = safeQuery {
        courseEntities.forEach {
            database.appDatabaseQueries.insertCourse(it.id, it.name, it.instructor, it.topics)
        }
    }

    suspend fun getListCourse() = safeQuery  {
        return@safeQuery database.appDatabaseQueries.getCourses().executeAsList()
    }
}