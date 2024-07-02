package data.local.dao

import framework.network.safeQuery
import org.sam.multiplatfrom_base.AppDatabase
import org.sam.multiplatfrombase.StudentEntity

class StudentDao(private val database: AppDatabase) {

    suspend fun getStudent(id: String) = safeQuery {
        return@safeQuery database.appDatabaseQueries.getStudentById(id.toLong()).executeAsOne()
    }

    suspend fun getListStudent() = safeQuery {
        return@safeQuery database.appDatabaseQueries.getStudents().executeAsList()
    }

    suspend fun updateStudent(studentEntity: StudentEntity) = safeQuery {
        database.appDatabaseQueries.updateStudent(
            studentEntity.birth,
            studentEntity.name,
            studentEntity.courseId,
            studentEntity.id,
        )
    }

    suspend fun insertListStudent(studentEntities: List<StudentEntity>) = safeQuery {
        studentEntities.forEach {
            database.appDatabaseQueries.insertStudent(
                it.birth,
                it.name,
                it.courseId,
            )
        }
    }

    suspend fun getStudentByCourseId(courseId: String): List<StudentEntity> = safeQuery {
        return@safeQuery database.appDatabaseQueries.getStudentsByCourseId(courseId).executeAsList()
    }
}