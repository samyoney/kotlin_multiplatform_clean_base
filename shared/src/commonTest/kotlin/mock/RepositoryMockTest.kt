package mock

import data.repository.CourseRepository
import framework.network.RequestState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class RepositoryMockTest {

    @Test
    fun `check mock call API if occur error`() {
        val courseRepository: CourseRepository = mockk()
        coEvery { courseRepository.fetchCourses() } returns RequestState.Error(NullPointerException())
        runBlocking {
            val result = courseRepository.fetchCourses()
            assertTrue(result is RequestState.Error)
        }
        coVerify { courseRepository.fetchCourses() }
    }
}