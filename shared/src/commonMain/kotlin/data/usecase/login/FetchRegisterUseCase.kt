package data.usecase.login

import data.model.remote.response.RegisterResponse
import data.repository.AccountRepository
import framework.network.RequestState
import framework.network.safeFetchApi

class FetchRegisterUseCase(private val repository: AccountRepository) {

    suspend operator fun invoke(
        username: String,
        password: String,
        courseId: String,
        name: String,
        birth: String
    ): RequestState<RegisterResponse> {
        return safeFetchApi { repository.register(username, password, courseId, name, birth) }
    }
}