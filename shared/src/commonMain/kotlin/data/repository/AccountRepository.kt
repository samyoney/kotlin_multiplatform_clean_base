package data.repository

import data.model.remote.request.LoginRequest
import data.model.remote.request.RegisterRequest
import data.remote.service.LoginService
import data.remote.service.RegisterService
import framework.pref.DeviceStorageManager


class AccountRepository(
    private val loginService: LoginService,
    private val registerService: RegisterService,
    private val deviceStorageManager: DeviceStorageManager,
    ) {

    companion object {
        private const val USERNAME_KEY = "USERNAME_KEY"
        private const val PASSWORD_KEY = "PASSWORD_KEY"
    }

    suspend fun login(username: String, password: String) =
        loginService.fetch(LoginRequest(username, password))

    suspend fun register(username: String, password: String, courseId: String, name: String, birth: String) =
        registerService.fetch(RegisterRequest(username, password, courseId, name, birth))

    var username: String
        get() {
            return deviceStorageManager.read(USERNAME_KEY) ?: String()
        }
        set(value) {
            deviceStorageManager.write(USERNAME_KEY, value)
        }

    var password: String
        get() {
            return deviceStorageManager.read(PASSWORD_KEY) ?: String()
        }
        set(value) {
            deviceStorageManager.write(PASSWORD_KEY, value)
        }
}