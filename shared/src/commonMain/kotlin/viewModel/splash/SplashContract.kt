package viewModel.splash

data class SplashState(var isNextScreen: Boolean = false)
sealed class SplashEvent {
    data object InitData : SplashEvent()
}