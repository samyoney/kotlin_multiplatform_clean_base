package data.usecase.login

import data.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SaveAccountInfoUseCase(private val repository: AccountRepository) {

    operator fun invoke(
        username: String,
        password: String,
    ): Flow<Unit> = flow {
        repository.username = username
        repository.password = password
        emit(Unit)
    }.flowOn(Dispatchers.IO)
}