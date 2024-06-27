import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Build
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.mp.KoinPlatform.getKoin
import org.sam.multiplatfrom_base.AppDatabase

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getSettings(): Settings {
    val context: Context = getKoin().get()
    val name = context.packageName.toString() + "_pref"
    val prefs: SharedPreferences = context.getSharedPreferences(name, MODE_PRIVATE)
    return SharedPreferencesSettings(prefs)
}

actual fun getDriver(): SqlDriver {
    val context: Context = getKoin().get()
    return AndroidSqliteDriver(AppDatabase.Schema, context, "enroll.db")
}