import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import org.sam.multiplatfrom_base.AppDatabase
import platform.Foundation.NSUserDefaults
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun getSettings(): Settings {
    val delegate = NSUserDefaults()
    return NSUserDefaultsSettings(delegate)
}

actual fun getDriver(): SqlDriver {
    return NativeSqliteDriver(AppDatabase.Schema, "enroll.db")
}