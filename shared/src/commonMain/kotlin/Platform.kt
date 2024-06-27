import app.cash.sqldelight.db.SqlDriver
import com.russhwolf.settings.Settings

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getSettings(): Settings

expect fun getDriver(): SqlDriver
