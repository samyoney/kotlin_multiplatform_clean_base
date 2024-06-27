package data.usecase.login

import data.repository.AccountRepository
import kotlinx.coroutines.flow.flow

class FetchLoginUseCase(private val repository: AccountRepository) {

    suspend operator fun invoke(username: String, password: String) = flow {
        emit(repository.login(username, password))
    }
}