import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.getOriginalKotlinClass

object SwiftKoin {

    val application = lazy { initKoin { }  }.value

    val appText = lazy {
        application.koin.get<AppText>()
    }.value

    @OptIn(BetaInteropApi::class)
    fun get(objCClass: ObjCClass): Any {
        val kClass = requireNotNull(getOriginalKotlinClass(objCClass))
        return application.koin.get(kClass, qualifier = null, parameters = null)
    }
}