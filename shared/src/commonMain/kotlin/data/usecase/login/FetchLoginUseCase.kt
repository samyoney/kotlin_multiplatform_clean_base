package data.usecase.login

import data.model.remote.response.LoginResponse
import data.repository.AccountRepository
import framework.network.RequestState
import framework.network.safeFetchApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FetchLoginUseCase(private val repository: AccountRepository) {

    operator fun invoke(username: String, password: String): Flow<RequestState<LoginResponse>> = flow {
        val result = safeFetchApi { repository.login(username, password) }
        emit(result)
    }.flowOn(Dispatchers.IO)
}