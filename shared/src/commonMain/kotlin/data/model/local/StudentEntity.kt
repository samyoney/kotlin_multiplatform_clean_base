package data.model.local

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class StudentEntity(
    @SerialName("id")  val id: Long = 0,
    @SerialName("courseId") var courseId: String? = null,
    @SerialName("name")  val name: String,
    @SerialName("birth") val birth: String,
)
