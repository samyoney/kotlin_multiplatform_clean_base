package data.usecase.login

import data.model.remote.response.LoginResponse
import data.repository.AccountRepository
import framework.network.RequestState
import framework.network.safeFetchApi

class FetchAutoLoginUseCase(private val repository: AccountRepository) {

    suspend operator fun invoke(
    ): RequestState<LoginResponse> {
        return safeFetchApi { repository.login(repository.username, repository.password) }
    }
}