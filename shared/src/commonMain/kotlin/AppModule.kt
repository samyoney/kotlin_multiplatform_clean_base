import data.local.provideDao
import data.local.provideDeviceStorage
import data.local.provideDriver
import data.local.provideSqlDelight
import data.provideRepository
import data.provideUseCase
import data.remote.provideBaseURL
import data.remote.provideHttpClient
import data.remote.provideService
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            provideDriver,
            provideSqlDelight,
            provideDeviceStorage,
            provideBaseURL,
            provideHttpClient,
            // メイン部分
            provideDao,
            provideService,
            provideRepository,
            provideUseCase
        )
    }

