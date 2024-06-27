package data.usecase.enroll

import data.repository.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CheckDataInitializedUseCase(private val courseRepository: CourseRepository) {

    operator fun invoke(): Flow<Boolean> = flow {
        emit(courseRepository.getListCourse().isNotEmpty())
    }.flowOn(Dispatchers.IO)

}