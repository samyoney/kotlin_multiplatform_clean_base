package framework.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NativeStateFlow<T>(private val flow: StateFlow<T>) {

    fun subscribe(scope: CoroutineScope, onValueChange: (T) -> Unit) {
        scope.launch {
            flow.collect { value ->
                onValueChange(value)
            }
        }
    }
}
