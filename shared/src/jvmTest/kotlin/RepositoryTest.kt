
import data.local.dao.CourseDao
import data.local.dao.StudentDao
import data.model.remote.request.LoginRequest
import data.model.remote.request.RegisterRequest
import data.model.remote.response.CourseResponse
import data.model.remote.response.LoginResponse
import data.model.remote.response.RegisterResponse
import data.model.remote.response.StudentResponse
import data.remote.service.CourseService
import data.remote.service.LoginService
import data.remote.service.RegisterService
import data.remote.service.StudentService
import data.repository.AccountRepository
import data.repository.CourseRepository
import data.repository.StudentRepository
import framework.network.RequestState
import framework.pref.DataStoreManager
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.koin.core.context.stopKoin
import org.sam.multiplatfrombase.CourseEntity
import org.sam.multiplatfrombase.StudentEntity
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RepositoryTest {

    @Test
    fun `test dependency and flow in course repository`() {
        val service: CourseService = mockk()
        val courseDao: CourseDao = mockk(relaxed = true)
        val courseRepository = CourseRepository(service, courseDao)
        val listEntity = arrayListOf(CourseEntity("", "", "", ""))
        val courseResponse = CourseResponse(0, "", arrayListOf())

        runBlocking {
            coEvery { service.fetch() } returns RequestState.Success(courseResponse)
            val responseSuccess = courseRepository.fetchCourses()
            assertTrue(responseSuccess is RequestState.Success)
            coVerify { service.fetch() }

            coEvery { service.fetch() } returns RequestState.Error(NullPointerException())
            val responseFail = courseRepository.fetchCourses()
            assertTrue(responseFail is RequestState.Error)
            coVerify { service.fetch() }

            courseRepository.insertListCourse(listEntity)
            coVerify { courseDao.insertListCourse(listEntity) }

            coEvery { courseDao.getListCourse() } returns listEntity
            val query = courseRepository.getListCourse()
            assertContentEquals(query, listEntity)
            coVerify { courseDao.getListCourse() }
        }
    }

    @Test
    fun `test dependency and flow in student repository`() {
        val service: StudentService = mockk()
        val studentDao: StudentDao = mockk(relaxed = true)
        val studentRepository = StudentRepository(service, studentDao)
        val studentResponse = StudentResponse(0, "", arrayListOf())
        val entity = StudentEntity(0, "", "", "")
        val listEntity = arrayListOf(StudentEntity(0, "", "", ""))
        val id = ""

        runBlocking {
            coEvery { service.fetch() } returns RequestState.Success(studentResponse)
            val responseSuccess = studentRepository.fetchStudents()
            assertTrue(responseSuccess is RequestState.Success)
            coVerify { service.fetch() }

            coEvery { service.fetch() } returns RequestState.Error(NullPointerException())
            val responseFail = studentRepository.fetchStudents()
            assertTrue(responseFail is RequestState.Error)
            coVerify { service.fetch() }

            studentRepository.updateStudent(entity)
            coVerify { studentDao.updateStudent(entity) }

            studentRepository.insertListStudent(listEntity)
            coVerify { studentDao.insertListStudent(listEntity) }

            coEvery { studentDao.getStudent(id) } returns entity
            val query = studentRepository.getStudent(id)
            assertEquals(query, entity)
            coVerify { studentDao.getStudent(id) }

            coEvery { studentDao.getListStudent() } returns listEntity
            val queryList = studentRepository.getListStudent()
            assertContentEquals(queryList, listEntity)
            coVerify { studentDao.getListStudent() }
        }
    }


    @Test
    fun `test dependency and flow in account repository`() {
        val loginService: LoginService = mockk()
        val registerService: RegisterService = mockk()
        val dataStoreManager: DataStoreManager = mockk(relaxed = true)
        val accountRepository = AccountRepository(loginService, registerService, dataStoreManager)
        val loginRequest = LoginRequest("", "")
        val loginResponse = LoginResponse(0, "")
        val registerRequest = RegisterRequest("", "", null, "", "")
        val registerResponse = RegisterResponse(0, "")

        runBlocking {
            coEvery { loginService.fetch(loginRequest) } returns RequestState.Success(loginResponse)
            val responseLoginSuccess =
                accountRepository.login(loginRequest.username, loginRequest.password)
            assertTrue(responseLoginSuccess is RequestState.Success)
            coVerify { loginService.fetch(loginRequest) }

            coEvery { loginService.fetch(loginRequest) } returns RequestState.Error(
                NullPointerException()
            )
            val responseLoginFail =
                accountRepository.login(loginRequest.username, loginRequest.password)
            assertTrue(responseLoginFail is RequestState.Error)
            coVerify { loginService.fetch(loginRequest) }

            coEvery { registerService.fetch(registerRequest) } returns RequestState.Success(
                registerResponse
            )
            val responseRegisterSuccess = accountRepository.register(
                registerRequest.username,
                registerRequest.password,
                registerRequest.courseId,
                registerRequest.name,
                registerRequest.birth
            )
            assertTrue(responseRegisterSuccess is RequestState.Success)
            coVerify { registerService.fetch(registerRequest) }

            coEvery { registerService.fetch(registerRequest) } returns RequestState.Error(
                NullPointerException()
            )
            val responseRegisterFail = accountRepository.register(
                registerRequest.username,
                registerRequest.password,
                registerRequest.courseId,
                registerRequest.name,
                registerRequest.birth
            )
            assertTrue(responseRegisterFail is RequestState.Error)
            coVerify { registerService.fetch(registerRequest) }
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }
}