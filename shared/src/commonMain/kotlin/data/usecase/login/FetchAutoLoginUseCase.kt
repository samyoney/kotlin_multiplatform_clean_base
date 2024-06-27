package data.usecase.login

import data.repository.AccountRepository
import kotlinx.coroutines.flow.flow

class FetchAutoLoginUseCase(private val repository: AccountRepository) {

    suspend operator fun invoke(
    ) = flow {
        emit(repository.login(repository.username, repository.password))
    }
}