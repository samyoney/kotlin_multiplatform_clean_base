import data.local.dao.CourseDao
import data.local.dao.StudentDao
import data.remote.service.CourseService
import data.remote.service.LoginService
import data.remote.service.RegisterService
import data.remote.service.StudentService
import data.repository.AccountRepository
import data.repository.CourseRepository
import data.repository.StudentRepository
import data.usecase.enroll.AddStudentIntoCourseUseCase
import data.usecase.enroll.CheckDataInitializedUseCase
import data.usecase.enroll.FetchCoursesUseCase
import data.usecase.enroll.FetchStudentsUseCase
import data.usecase.enroll.GetCoursesUseCase
import data.usecase.enroll.GetStudentsByCourseIdUseCase
import data.usecase.enroll.GetStudentsUseCase
import data.usecase.enroll.RemoveStudentFromCourseUseCase
import data.usecase.enroll.SaveCoursesUseCase
import data.usecase.enroll.SaveStudentsUseCase
import data.usecase.login.CheckLoggedInUseCase
import data.usecase.login.FetchAutoLoginUseCase
import data.usecase.login.FetchLoginUseCase
import data.usecase.login.FetchRegisterUseCase
import data.usecase.login.SaveAccountInfoUseCase
import framework.pref.DataStoreManager
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
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


private fun provideViewModel() = module {
    viewModel { SplashViewModel() }
    viewModel { LoginViewModel() }
    viewModel { SamViewModel() }
}

private fun provideDao() = module {
    factory { CourseDao(get()) }
    factory { StudentDao(get()) }
}

private fun provideRepository() = module {
    factory { AccountRepository(get(), get(), get()) }
    factory { CourseRepository(get(), get()) }
    factory { StudentRepository(get(), get()) }
}

private fun provideUseCase() = module {
    factory { AddStudentIntoCourseUseCase(get()) }
    factory { CheckDataInitializedUseCase(get()) }
    factory { FetchCoursesUseCase(get()) }
    factory { FetchStudentsUseCase(get()) }
    factory { GetCoursesUseCase(get()) }
    factory { GetStudentsByCourseIdUseCase(get()) }
    factory { GetStudentsUseCase(get()) }
    factory { RemoveStudentFromCourseUseCase(get()) }
    factory { SaveCoursesUseCase(get()) }
    factory { SaveStudentsUseCase(get()) }

    factory { CheckLoggedInUseCase(get()) }
    factory { FetchAutoLoginUseCase(get()) }
    factory { FetchLoginUseCase(get()) }
    factory { FetchRegisterUseCase(get()) }
    factory { SaveAccountInfoUseCase(get()) }
}

private fun provideDataStoreManager() = module {
    single { DataStoreManager(get()) }
}

private fun provideHttpClient() = module {
    single(named("BaseURL")) {
        "https://us-central1-samyoney.cloudfunctions.net/api/"
    }
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        useAlternativeNames = false
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }
}

private fun provideService() = module {
    factory { CourseService(get(named("BaseURL")), get()) }
    factory { LoginService(get(named("BaseURL")), get()) }
    factory { RegisterService(get(named("BaseURL")), get()) }
    factory { StudentService(get(named("BaseURL")), get()) }
}