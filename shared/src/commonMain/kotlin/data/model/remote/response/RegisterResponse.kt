package data.model.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    override val status: Int,
    override val message: String,
    ): BaseResponse()
