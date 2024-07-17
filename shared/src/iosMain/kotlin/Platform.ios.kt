import app.cash.sqldelight.driver.native.NativeSqliteDriver
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.dsl.module
import org.sam.multiplatfrom_base.AppDatabase
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask
import platform.UIKit.UIDevice

internal const val dataStoreFileName = "sam.preferences_pb"

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion

    override val isAndroid: Boolean = false
}

actual fun providePlatform() = module {
    single { IOSPlatform() }
    single { provideAppText() }
    single { provideDataStore() }
    single<AppDatabase> { AppDatabase(NativeSqliteDriver(AppDatabase.Schema, "enroll.db")) }
}

private fun provideAppText(): AppText {
    return NSBundleResource()
}

@OptIn(ExperimentalForeignApi::class)
private fun provideDataStore() = createDataStore(
    producePath = {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        requireNotNull(documentDirectory).path + "/$dataStoreFileName"
    }
)