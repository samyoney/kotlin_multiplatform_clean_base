package data.usecase.login

import data.repository.AccountRepository

class SaveAccountInfoUseCase(private val repository: AccountRepository) {

    operator fun invoke(
        username: String,
        password: String,
    ) {
        repository.username = username
        repository.password = password
    }
}