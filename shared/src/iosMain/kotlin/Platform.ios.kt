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

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual val providePlatform = module {
    single {
        IOSPlatform()
        provideDataStore
        provideDriver
    }

}

@OptIn(ExperimentalForeignApi::class)
private val provideDataStore = createDataStore(
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

private val provideDriver = {
     NativeSqliteDriver(AppDatabase.Schema, "enroll.db")
}