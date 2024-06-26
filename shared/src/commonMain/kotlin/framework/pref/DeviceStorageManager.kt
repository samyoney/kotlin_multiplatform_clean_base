package framework.pref

expect class DeviceStorageManager {
    fun <T> read(key: String, defaultValue: T): T

    fun <T> write(key: String, value: T)
}