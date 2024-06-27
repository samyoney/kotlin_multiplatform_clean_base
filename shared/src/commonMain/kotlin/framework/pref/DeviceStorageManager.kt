package framework.pref

import getSettings

class DeviceStorageManager {

    inline fun <reified T> read(key: String): T? {
        return when (T::class) {
            Int::class -> getSettings().getIntOrNull(key) as? T
            Long::class -> getSettings().getLongOrNull(key) as? T
            String::class -> getSettings().getStringOrNull(key) as? T
            Boolean::class -> getSettings().getBooleanOrNull(key) as? T
            Float::class -> getSettings().getFloatOrNull(key) as? T
            Double::class -> getSettings().getDoubleOrNull(key) as? T
            else -> null
        }
    }

    fun <T> write(key: String, value: T) {
        when (value) {
            is Int -> getSettings().putInt(key, value)
            is Long -> getSettings().putLong(key, value)
            is String -> getSettings().putString(key, value)
            is Boolean -> getSettings().putBoolean(key, value)
            is Float -> getSettings().putFloat(key, value)
            is Double -> getSettings().putDouble(key, value)
        }
    }}