import app.cash.sqldelight.db.SqlDriver
import framework.pref.DataStoreManager
import io.mockk.mockk
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.sam.multiplatfrom_base.AppDatabase
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

class ViewModelTest: KoinTest {
    @BeforeTest
    fun setUp() {
        startKoin {
            modules(
                module {
                    single<AppDatabase> { AppDatabase(mockk<SqlDriver>()) }
                    single<DataStoreManager> { mockk<DataStoreManager>() }
                },
                provideBaseUrl(),
                provideHttpClient {},
                provideDao(),
                provideService(),
                provideRepository(),
                provideUseCase(),
                provideViewModel()
            )
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }
}