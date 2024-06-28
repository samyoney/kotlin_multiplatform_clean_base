package viewModel.splash

import data.model.remote.response.CourseResponse
import data.usecase.enroll.CheckDataInitializedUseCase
import data.usecase.enroll.FetchCoursesUseCase
import data.usecase.enroll.SaveCoursesUseCase
import data.usecase.login.CheckLoggedInUseCase
import data.usecase.login.FetchAutoLoginUseCase
import framework.base.BaseLoadingViewModel
import framework.base.LoadingState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import org.koin.core.component.inject

class SplashViewModel : BaseLoadingViewModel<SplashState, SplashEvent>() {

    private val fetchAutoLoginUseCase: FetchAutoLoginUseCase by inject()
    private val fetchCoursesUseCase: FetchCoursesUseCase by inject()
    private val saveCoursesUseCase: SaveCoursesUseCase by inject()
    private val checkLoggedInUseCase: CheckLoggedInUseCase by inject()
    private val checkDataInitializedUseCase: CheckDataInitializedUseCase by inject()

    override fun initialLoadingState(): SplashState = SplashState()

    override fun onTriggerEvent(eventType: SplashEvent) {
        when (eventType) {
            is SplashEvent.InitData -> {
                onInitializedData()
            }
        }
    }

    private fun onInitializedData() = safeLaunch {
        executeLocalUseCase(checkDataInitializedUseCase()) { isDataInitialized ->
            if (isDataInitialized) {
                login()
            } else {
                fetchCoursesData()
            }
        }
    }

    private fun fetchCoursesData() = safeLaunch {
        executeRemoteUseCase(fetchCoursesUseCase()) { res ->
            if (res.status == 0) {
                saveCourses(res)
                login()
            } else {
                handleError(res.message)
            }
        }
    }

    private fun saveCourses(res: CourseResponse) = safeLaunch {
        executeLocalUseCase(saveCoursesUseCase(res.course))
    }

    override fun handleError(errorText: String) {
        uiState.update { LoadingState.Error(errorText) }
    }

    private fun login() = safeLaunch {
        if (checkLoggedInUseCase()) {
            executeRemoteUseCase(fetchAutoLoginUseCase()) { res ->
                if (res.status == 0) {
                    onNextScreen()
                } else {
                    handleError(res.message)
                }
            }
        } else {
            onNextScreen()
        }
    }

    private fun onNextScreen() = safeLaunch {
        delay(2000)
        uiState.updateLoaded { it.copy(isNextScreen = true) }
    }}