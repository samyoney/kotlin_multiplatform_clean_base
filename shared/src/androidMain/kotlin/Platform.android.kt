import android.content.Context
import android.os.Build
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.dsl.module
import org.koin.mp.KoinPlatform.getKoin
import org.sam.multiplatfrom_base.AppDatabase

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun providePlatform() = module {
    single { AndroidPlatform() }
    single { provideAppText() }
    single { provideDataStore() }
    single<AppDatabase> { AppDatabase(AndroidSqliteDriver(AppDatabase.Schema, get(), "enroll.db")) }
}

private fun provideAppText(): AppText {
    val context: Context = getKoin().get()
    return AndroidResource(context)
}

private fun provideDataStore(): DataStore<Preferences> {
    val context: Context = getKoin().get()
    return createDataStore(
        producePath = { context.filesDir.resolve("${context.packageName}_pref.preferences_pb").absolutePath }
    )
}