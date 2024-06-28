package data.usecase.login

import data.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FetchLoginUseCase(private val repository: AccountRepository) {

    suspend operator fun invoke(username: String, password: String) = flow {
        emit(repository.login(username, password))
    }.flowOn(Dispatchers.IO)
}