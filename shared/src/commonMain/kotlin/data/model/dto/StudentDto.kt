package data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class StudentDto(
    val id: Long,
    val courseId: String?,
    val name: String,
    val birth: String,
)
