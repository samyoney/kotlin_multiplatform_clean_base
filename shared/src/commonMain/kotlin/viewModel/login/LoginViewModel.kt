package viewModel.login

import AppText
import data.model.remote.response.StudentResponse
import data.usecase.enroll.FetchStudentsUseCase
import data.usecase.enroll.SaveStudentsUseCase
import data.usecase.login.FetchLoginUseCase
import data.usecase.login.FetchRegisterUseCase
import data.usecase.login.SaveAccountInfoUseCase
import framework.base.BaseViewModel
import framework.base.LoadingState
import kotlinx.coroutines.flow.update
import org.koin.core.component.inject

class LoginViewModel : BaseViewModel<LoginState, LoginEvent>() {
    private val fetchRegisterUseCase: FetchRegisterUseCase by inject()
    private val fetchLoginUseCase: FetchLoginUseCase by inject()
    private val fetchStudentsUseCase: FetchStudentsUseCase by inject()
    private val saveStudentsUseCase: SaveStudentsUseCase by inject()
    private val saveAccountInfoUseCase: SaveAccountInfoUseCase by inject()
    private val appText: AppText by inject()

    override fun initialState() = LoginState()

    override fun onTriggerEvent(eventType: LoginEvent) {
        when (eventType) {
            is LoginEvent.InputUsername -> {
                onInputUsername(eventType.text)
            }
            is LoginEvent.InputPassword -> {
                onInputPassword(eventType.text)
            }
            is LoginEvent.InputName -> {
                onInputName(eventType.text)
            }
            is LoginEvent.InputBirth -> {
                onInputBirth(eventType.year, eventType.month)
            }
            is LoginEvent.Register -> {
                onRegister()
            }
            is LoginEvent.Login -> {
                onLogin()
            }
            is LoginEvent.ChangeLoginMode -> {
                onChangeMode()
            }
            is LoginEvent.IdleReturn -> {
                onIdle()
            }
        }
    }

    override fun startLoading() {
        super.startLoading()
        uiState.update { it.copy(loadingState = LoadingState.Loading) }
    }

    override fun handleError(errorText: String) {
        uiState.update { it.copy(loadingState = LoadingState.Error(errorText)) }
    }

    private fun onChangeMode() = safeLaunch {
        uiState.update { it.copy(isRegisterScreen = !it.isRegisterScreen) }
    }

    private fun onLogin() = safeLaunch {
        if (!checkValidation(uiState.value.username, uiState.value.password)) {
            uiState.update { it.copy(loadingState = LoadingState.Error(appText.validationFailText())) }
            return@safeLaunch
        }
        executeRemoteUseCase(
            fetchLoginUseCase(uiState.value.username, uiState.value.password)
        ) { res ->
            if (res.status == 0) {
                val info = uiState.value
                handleAfterLogin(info.username, info.password)
            } else {
                handleError(res.message)
            }
        }
    }

    private fun onRegister() = safeLaunch {
        if (!checkValidation(uiState.value.username, uiState.value.password, uiState.value.birth)) {
            uiState.update { it.copy(loadingState = LoadingState.Error(appText.validationFailText())) }
            return@safeLaunch
        }
        executeRemoteUseCase(
            fetchRegisterUseCase(uiState.value.username, uiState.value.password, null, uiState.value.name, uiState.value.birth)
        ) { res ->
            if (res.status == 0) {
                val info = uiState.value
                handleAfterLogin(info.username, info.password)
            } else {
                handleError(res.message)
            }
        }
    }

    private fun handleAfterLogin(username: String, password: String) = safeLaunch {
            fetchStudentsData {res ->
                saveStudents(res)
                saveAccountInfo(username, password)
                uiState.update { it.copy(loadingState = LoadingState.Loaded()) }
            }
    }

    private fun fetchStudentsData(onFinish: (res: StudentResponse) -> Unit) = safeLaunch {
        executeRemoteUseCase(fetchStudentsUseCase()) { res ->
            if (res.status == 0) {
                onFinish(res)
            } else {
                handleError(res.message)
            }
        }
    }

    private fun saveAccountInfo(username: String, password: String) = safeLaunch {
        saveAccountInfoUseCase(username, password)
    }

    private fun saveStudents(res: StudentResponse) = safeLaunch {
        executeLocalUseCase(saveStudentsUseCase(res.students))
    }

    private fun onInputUsername(text: String) = safeLaunch {
        if ((text.isEmpty() || text.matches("^[A-Z0-9a-z]+$".toRegex())) && text.length <= 16) {
            uiState.update {
                    it.copy(username = text.uppercase())
            }
        }
    }

    private fun onInputPassword(text: String) = safeLaunch {
        if ((text.isEmpty() || text.matches("^[A-Z0-9a-z]+$".toRegex())) && text.length <= 16) {
            uiState.update {
                it.copy(password = text.uppercase())
            }
        }
    }

    private fun onInputName(text: String) = safeLaunch {
        uiState.update {
            it.copy(name = text)
        }
    }

    private fun onInputBirth(year: Int, month: Int) = safeLaunch {
        val birthText = "$year/$month"
        uiState.update {
            it.copy(birth = birthText)
        }
    }

    private fun onIdle() = safeLaunch {
        uiState.update { it.copy(loadingState = LoadingState.Idle) }
    }

    private fun checkValidation(username: String?, password: String?, birthDay: String? = null): Boolean {
        val isCompanyIDValid = isValidString(username)
        val isUserValid = isValidString(password)

        val isBirthdayValid = if (birthDay != null) isValidString(birthDay) else true
        return isCompanyIDValid && isUserValid && isBirthdayValid
    }

    private fun isValidString(input: String?): Boolean {
        val checkedInput = input.orEmpty()
        return checkedInput.isNotEmpty()
    }
}