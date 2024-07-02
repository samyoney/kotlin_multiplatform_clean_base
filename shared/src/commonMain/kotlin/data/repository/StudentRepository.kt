package data.repository

import data.local.dao.StudentDao
import data.remote.service.StudentService
import org.sam.multiplatfrombase.StudentEntity

class StudentRepository(
    private val studentService: StudentService,
    private val studentDao: StudentDao,
    ) {

    suspend fun fetchStudents() = studentService.fetch()

    suspend fun getStudent(id:String) = studentDao.getStudent(id)

    suspend fun getListStudent() = studentDao.getListStudent()

    suspend fun updateStudent(studentEntity: StudentEntity) = studentDao.updateStudent(studentEntity)

    suspend fun insertListStudent(studentEntities: List<StudentEntity>) = studentDao.insertListStudent(studentEntities)

    suspend fun getStudentByCourseId(courseId: String) = studentDao.getStudentByCourseId(courseId)

}