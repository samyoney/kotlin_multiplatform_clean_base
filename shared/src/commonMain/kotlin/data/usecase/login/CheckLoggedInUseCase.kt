package data.usecase.login

import data.repository.AccountRepository


class CheckLoggedInUseCase(private val repository: AccountRepository) {
    operator fun invoke(): Boolean = repository.username.isNotEmpty() && repository.password.isNotEmpty()
}