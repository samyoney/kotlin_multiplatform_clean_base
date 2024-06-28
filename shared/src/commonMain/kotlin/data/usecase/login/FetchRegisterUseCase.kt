package data.usecase.login

import data.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FetchRegisterUseCase(private val repository: AccountRepository) {

    suspend operator fun invoke(
        username: String,
        password: String,
        courseId: String,
        name: String,
        birth: String
    ) = flow {
        emit(repository.register(username, password, courseId, name, birth))
    }.flowOn(Dispatchers.IO)
}