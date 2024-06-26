package data.model.dto

import kotlinx.serialization.Serializable


@Serializable
data class CourseDto(
    val id: String,
    val name: String,
    val instructor: String,
    val topics: List<String>
)