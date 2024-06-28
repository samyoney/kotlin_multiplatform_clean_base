import android.content.Context
import android.os.Build
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.dsl.module
import org.koin.mp.KoinPlatform.getKoin
import org.sam.multiplatfrom_base.AppDatabase

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual val providePlatform = module {
    single {
        AndroidPlatform()
        provideDriver
    }

}

private val provideDataStore = {
    val context: Context = getKoin().get()
    createDataStore(
        producePath = { context.filesDir.resolve(context.packageName.toString() + "_pref").absolutePath }
    )
}

private val provideDriver = {
    val context: Context = getKoin().get()
    AndroidSqliteDriver(AppDatabase.Schema, context, "enroll.db")
}