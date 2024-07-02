import data.local.provideDao
import data.provideRepository
import data.provideUseCase
import data.remote.provideHttpClient
import data.remote.provideService
import framework.pref.DataStoreManager
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import viewModel.login.LoginViewModel
import viewModel.sam.SamViewModel
import viewModel.splash.SplashViewModel

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            providePlatform(),
            provideHttpClient(),
            // メイン部分
            provideDao(),
            provideDataStoreManager(),
            provideService(),
            provideRepository(),
            provideUseCase(),
            provideViewModel()
        )
    }

private fun provideDataStoreManager() = module {
    single { DataStoreManager(get()) }
}

private fun provideViewModel() = module {
    viewModel { SplashViewModel() }
    viewModel { LoginViewModel() }
    viewModel { SamViewModel() }
}

// iOSの部分
fun KoinApplication.Companion.initKoin(): KoinApplication = initKoin {}
val Koin.splashViewModel: SplashViewModel get() = get()
val Koin.loginViewModel: LoginViewModel get() = get()
val Koin.samViewModel: SamViewModel get() = get()
val Koin.appText: AppText get() = get()
