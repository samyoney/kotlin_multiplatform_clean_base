import data.model.remote.request.LoginRequest
import data.model.remote.request.RegisterRequest
import data.remote.service.CourseService
import data.remote.service.LoginService
import data.remote.service.RegisterService
import data.remote.service.StudentService
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertNotNull

class ServiceTest : KoinTest {

    private fun injectDependency() {
        startKoin {
            modules(
                provideBaseUrl(),
                provideHttpClient {},
                provideService(),
            )
        }
    }

    @Test
    fun `test koin inject in service`() {
        injectDependency()
        runBlocking {
            assertNotNull(get<CourseService>())
            assertNotNull(get<StudentService>())
            assertNotNull(get<LoginService>())
            assertNotNull(get<RegisterService>())
        }
    }

    @Test
    fun `test fetch course api`() {
        injectDependency()
        runBlocking {
            spyk(get<CourseService>()).apply {
                val response = fetch()
                println(response)
                assertNotNull(response)
            }
        }
    }

    @Test
    fun `test fetch student api`() {
        injectDependency()
        runBlocking {
            spyk(get<StudentService>()).apply {
                val response = fetch()
                println(response)
                assertNotNull(response)
            }
        }
    }

    @Test
    fun `test fetch login api`() {
        injectDependency()
        runBlocking {
            spyk(get<LoginService>()).apply {
                val request = LoginRequest("username", "password")
                val response = fetch(request)
                println(response)
                assertNotNull(response)
            }
        }
    }

    @Test
    fun `test fetch register api`() {
        injectDependency()
        runBlocking {
            spyk(get<RegisterService>()).apply {
                val request = RegisterRequest("username", "password", null, "sam", "14/05/94")
                val response = fetch(request)
                println(response)
                assertNotNull(response)
            }
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }
}