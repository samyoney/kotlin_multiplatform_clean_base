package data.model.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class BaseResponse {
    @SerialName("status")
    abstract val status: Int
    @SerialName("message")
    abstract val message: String
}