package data.usecase.login

import data.model.remote.response.RegisterResponse
import data.repository.AccountRepository
import framework.network.RequestState
import framework.network.safeFetchApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FetchRegisterUseCase(private val repository: AccountRepository) {

    operator fun invoke(
        username: String,
        password: String,
        courseId: String,
        name: String,
        birth: String
    ): Flow<RequestState<RegisterResponse>> = flow {
        val result = safeFetchApi { repository.register(username, password, courseId, name, birth) }
        emit(result)
    }.flowOn(Dispatchers.IO)
}