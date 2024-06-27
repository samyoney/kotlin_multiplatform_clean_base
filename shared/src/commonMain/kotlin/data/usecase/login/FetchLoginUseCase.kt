package data.usecase.login

import data.model.remote.response.LoginResponse
import data.repository.AccountRepository
import framework.network.RequestState
import framework.network.safeFetchApi

class FetchLoginUseCase(private val repository: AccountRepository) {

    suspend operator fun invoke(username: String, password: String): RequestState<LoginResponse> {
        return safeFetchApi { repository.login(username, password) }
    }
}