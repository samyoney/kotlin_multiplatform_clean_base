package data.model.local

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseEntity(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("instructor")
    val instructor: String,
    @SerialName("topics")
    val topics: List<String>, )