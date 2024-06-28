package data.usecase.login

import data.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FetchAutoLoginUseCase(private val repository: AccountRepository) {

    suspend operator fun invoke(
    ) = flow {
        emit(repository.login(repository.username, repository.password))
    }.flowOn(Dispatchers.IO)
}