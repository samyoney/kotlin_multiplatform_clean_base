package data.local.dao

import data.model.local.CourseEntity
import org.sam.multiplatfrom_base.AppDatabase

class CourseDao(private val database: AppDatabase) {
    fun insertListCourse(courseEntities: List<CourseEntity>) {
        courseEntities.forEach {
            database.appDatabaseQueries.insertCourse(it.id, it.name, it.instructor, it.topics.joinToString(","))
        }
    }

    fun getListCourse(): List<CourseEntity> {
        return database.appDatabaseQueries.getCourses().executeAsList().map {
            CourseEntity(it.id, it.name, it.instructor, it.topics.split(","))
        }
    }
}