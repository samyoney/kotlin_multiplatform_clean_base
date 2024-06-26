package framework.extension

fun String?.safe(): String {
    return this ?: String()
}

fun String?.isNotNullOrBlank(): Boolean = !this.isNullOrBlank()