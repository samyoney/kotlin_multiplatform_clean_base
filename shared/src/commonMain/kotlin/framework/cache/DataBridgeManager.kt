package framework.cache

import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.update
import kotlinx.atomicfu.updateAndGet
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
class DataBridgeManager {

    companion object {
        private val storage = atomic(mutableMapOf<String, Any?>())
    }

    fun <T : Any> setValue(value: T, type: KClass<T>) {
        val key = type.qualifiedName ?: error("Cannot get the qualified name of the type")
        storage.update { it.toMutableMap().apply { this[key] = value } }
    }

    fun <T : Any> getValue(type: KClass<T>): T? {
        val key = type.qualifiedName ?: error("Cannot get the qualified name of the type")
        return storage.updateAndGet {it.toMutableMap().apply { remove(key) }
        }[key] as? T
    }
}