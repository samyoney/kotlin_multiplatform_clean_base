package data.local.dao

import data.model.local.StudentEntity
import org.sam.multiplatfrom_base.AppDatabase

class StudentDao(private val database: AppDatabase) {

    fun getStudent(id: String): StudentEntity {
        val query = database.appDatabaseQueries.getStudentById(id.toLong()).executeAsOne()
        return StudentEntity(query.id, query.courseId, query.name, query.birth)
    }

    fun getListStudent(): List<StudentEntity> {
        return database.appDatabaseQueries.getStudents().executeAsList().map {
            StudentEntity(it.id, it.courseId, it.name, it.birth)
        }
    }

    fun updateStudent(studentEntity: StudentEntity) {
        database.appDatabaseQueries.updateStudent(
            studentEntity.birth,
            studentEntity.name,
            studentEntity.courseId,
            studentEntity.id,
        )
    }

    fun insertListStudent(studentEntities: List<StudentEntity>) {
        studentEntities.forEach {
            database.appDatabaseQueries.insertStudent(
                it.birth,
                it.name,
                it.courseId,
            )
        }
    }

    fun getStudentByCourseId(courseId: String): List<StudentEntity> {
        return database.appDatabaseQueries.getStudentsByCourseId(courseId).executeAsList().map {
            StudentEntity(it.id, it.courseId, it.name, it.birth)
        }
    }
}