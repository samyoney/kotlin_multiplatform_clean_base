import data.model.dto.toDto
import data.repository.StudentRepository
import data.usecase.enroll.AddStudentIntoCourseUseCase
import data.usecase.enroll.CheckDataInitializedUseCase
import data.usecase.enroll.FetchCoursesUseCase
import data.usecase.enroll.FetchStudentsUseCase
import data.usecase.enroll.GetCoursesUseCase
import data.usecase.enroll.GetStudentsByCourseIdUseCase
import data.usecase.enroll.GetStudentsUseCase
import data.usecase.enroll.RemoveStudentFromCourseUseCase
import data.usecase.enroll.SaveCoursesUseCase
import data.usecase.enroll.SaveStudentsUseCase
import data.usecase.login.CheckLoggedInUseCase
import data.usecase.login.FetchAutoLoginUseCase
import data.usecase.login.FetchLoginUseCase
import data.usecase.login.FetchRegisterUseCase
import data.usecase.login.SaveAccountInfoUseCase
import framework.pref.DataStoreManager
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import org.sam.multiplatfrom_base.AppDatabase
import org.sam.multiplatfrombase.StudentEntity
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class UseCaseTest : KoinTest {

    private fun injectDependency() {
        startKoin {
            modules(
                module {
                    single<AppDatabase> { mockk() }
                    single<DataStoreManager> { mockk() }
                },
                provideBaseUrl(),
                provideHttpClient {},
                provideDao(),
                provideService(),
                provideRepository(),
                provideUseCase()
            )
        }
    }

    @Test
    fun `test koin inject in use case`() {
        injectDependency()
        runBlocking {
            assertNotNull(get<AddStudentIntoCourseUseCase>())
            assertNotNull(get<CheckDataInitializedUseCase>())
            assertNotNull(get<FetchCoursesUseCase>())
            assertNotNull(get<FetchStudentsUseCase>())
            assertNotNull(get<GetCoursesUseCase>())
            assertNotNull(get<GetStudentsByCourseIdUseCase>())
            assertNotNull(get<GetStudentsUseCase>())
            assertNotNull(get<RemoveStudentFromCourseUseCase>())
            assertNotNull(get<SaveCoursesUseCase>())
            assertNotNull(get<SaveStudentsUseCase>())
            assertNotNull(get<CheckLoggedInUseCase>())
            assertNotNull(get<FetchAutoLoginUseCase>())
            assertNotNull(get<FetchLoginUseCase>())
            assertNotNull(get<FetchRegisterUseCase>())
            assertNotNull(get<SaveAccountInfoUseCase>())
        }
    }

    @Test
    fun `test GetStudentsByCourseIdUseCase`() {
        runBlocking {
            val courseId = "2"
            val mockStudentList = listOf(
                StudentEntity(
                    id = 1,
                    birth = "1995-04-29",
                    name = "John Doe",
                    courseId = "1"
                ),
                StudentEntity(
                    id = 2,
                    birth = "1998-12-12",
                    name = "Jane Smith",
                    courseId = "2"
                ),
                StudentEntity(
                    id = 3,
                    birth = "2000-06-15",
                    name = "Michael Johnson",
                    courseId = null
                )
            )
            val studentListDto = mockStudentList.map { it.toDto() }
            val studentRepository: StudentRepository = mockk()
            val getStudentsByCourseIdUseCase = GetStudentsByCourseIdUseCase(studentRepository)

            coEvery { studentRepository.getStudentByCourseId(courseId) } returns mockStudentList

            val student = getStudentsByCourseIdUseCase(courseId).first()
            assertEquals(studentListDto, student)
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }
}