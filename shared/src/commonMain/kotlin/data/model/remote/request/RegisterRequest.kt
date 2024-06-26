package data.model.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    @SerialName("username") val username: String,
    @SerialName("password") val password: String,
    @SerialName("course_id") val courseId: String,
    @SerialName("name") val name: String,
    @SerialName("birth") val birth: String,
)