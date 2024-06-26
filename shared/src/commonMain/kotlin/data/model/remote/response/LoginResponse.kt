package data.model.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    override val status: Int,
    override val message: String,
    ): BaseResponse()
