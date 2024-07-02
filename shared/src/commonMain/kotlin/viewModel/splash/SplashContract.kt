package viewModel.splash

data class SplashState(var isNextLogin: Boolean = false)
sealed class SplashEvent {
    data object InitData : SplashEvent()
}