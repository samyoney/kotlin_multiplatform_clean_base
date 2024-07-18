package spy

import app.cash.sqldelight.db.SqlDriver
import data.repository.CourseRepository
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.sam.multiplatfrom_base.AppDatabase
import provideDao
import provideDataStoreManager
import provideHttpClient
import provideRepository
import provideService
import provideUseCase
import provideViewModel
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull

class RepositorySpyTest: KoinTest {

    private val _courseRepository: CourseRepository by inject()

    @BeforeTest
    fun setUp() {
        startKoin {
            modules(
                module {
                    single<AppDatabase> { AppDatabase(mockk<SqlDriver>()) }
                },
                provideHttpClient {},
                provideDao(),
                provideDataStoreManager(),
                provideService(),
                provideRepository(),
                provideUseCase(),
                provideViewModel()
            )
        }
    }

    @Test
    fun `check spy call API if occur error`() {
        val courseRepository = spyk(_courseRepository)
        runBlocking {
            val result = courseRepository.fetchCourses()
            print(result)
            assertNotNull(result)
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }
}