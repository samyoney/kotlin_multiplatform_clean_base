package framework.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Suppress("UNCHECKED_CAST")
class DataStoreManager(val setting: DataStore<Preferences>) {

    inline fun <reified T> read(key: String): T? = runBlocking {
        val preferencesKey = getKey<T>(key)
        setting.data.first()[preferencesKey]
    }

    inline fun <reified T> write(key: String, value: T) = runBlocking {
        val preferencesKey = getKey<T>(key)
        setting.edit { preferences ->
            when (value) {
                is Int -> preferences[preferencesKey as Preferences.Key<Int>] = value
                is Long -> preferences[preferencesKey as Preferences.Key<Long>] = value
                is String -> preferences[preferencesKey as Preferences.Key<String>] = value
                is Boolean -> preferences[preferencesKey as Preferences.Key<Boolean>] = value
                is Float -> preferences[preferencesKey as Preferences.Key<Float>] = value
                is Double -> preferences[preferencesKey as Preferences.Key<Double>] = value
            }
        }
    }

    inline fun <reified T> getKey(key: String): Preferences.Key<T> {
        return when (T::class) {
            Int::class -> intPreferencesKey(key)
            Long::class -> longPreferencesKey(key)
            String::class -> stringPreferencesKey(key)
            Boolean::class -> booleanPreferencesKey(key)
            Float::class -> floatPreferencesKey(key)
            Double::class -> doublePreferencesKey(key)
            else -> throw IllegalArgumentException("Unsupported type")
        } as Preferences.Key<T>
    }
}
