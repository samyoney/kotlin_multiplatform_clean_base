package data.repository

import data.local.dao.StudentDao
import data.model.local.StudentEntity
import data.remote.service.StudentService

class StudentRepository(
    private val studentService: StudentService,
    private val studentDao: StudentDao,
    ) {

    suspend fun fetchStudents() = studentService.fetch()

    fun getStudent(id:String) = studentDao.getStudent(id)

    fun getListStudent() = studentDao.getListStudent()

    fun updateStudent(studentEntity: StudentEntity) = studentDao.updateStudent(studentEntity)

    fun insertListStudent(studentEntities: List<StudentEntity>) = studentDao.insertListStudent(studentEntities)

    fun getStudentByCourseId(courseId: String) = studentDao.getStudentByCourseId(courseId)

}