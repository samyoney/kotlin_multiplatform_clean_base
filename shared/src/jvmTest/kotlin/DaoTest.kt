
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import data.local.dao.CourseDao
import data.local.dao.StudentDao
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import org.sam.multiplatfrom_base.AppDatabase
import org.sam.multiplatfrombase.CourseEntity
import org.sam.multiplatfrombase.StudentEntity
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class DaoTest : KoinTest {

    private lateinit var appDatabase: AppDatabase

    @BeforeTest
    fun beforeStart() {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).apply {
            AppDatabase.Schema.create(this)
        }
        appDatabase = AppDatabase(driver)
    }

    private fun injectDependency() {
        startKoin {
            module {
                single<AppDatabase> { AppDatabase(mockk<SqlDriver>()) }
                provideDao()
            }
        }
    }

    @Test
    fun `test koin inject in DAO`() {
        injectDependency()
        runBlocking {
            assertNotNull(get<CourseDao>())
            assertNotNull(get<StudentDao>())
        }
    }

    @Test
    fun `test course database function`() {
        runBlocking {
            val mockCourseList = listOf(
                CourseEntity(
                    id = "1",
                    name = "Kotlin for Beginners",
                    instructor = "Alice Johnson",
                    topics = "Introduction to Kotlin, Basics, Functions, Classes"
                ),
                CourseEntity(
                    id = "2",
                    name = "Advanced Android Development",
                    instructor = "Bob Smith",
                    topics = "Jetpack Compose, Coroutines, Hilt, Architecture Components"
                )
            )
            val courseDao = CourseDao(appDatabase)
            courseDao.insertListCourse(mockCourseList)
            assertEquals(mockCourseList, courseDao.getListCourse())
        }
    }

    @Test
    fun `test student database function`() {
        runBlocking {
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
                ),

                )
            val studentDao = StudentDao(appDatabase)
            studentDao.insertListStudent(mockStudentList)
            assertEquals(mockStudentList, studentDao.getListStudent())
            assertEquals(mockStudentList.first { it.id == 1L }, studentDao.getStudent(id = "1"))

            studentDao.updateStudent(StudentEntity(
                id = 3,
                birth = "2000-06-15",
                name = "Michael Johnson",
                courseId = "2"
            ))
            assertEquals("2", studentDao.getStudent(id = "2").courseId)
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }
}