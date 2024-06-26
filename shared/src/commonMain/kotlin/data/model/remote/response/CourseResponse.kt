package data.model.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseResponse(
    override val status: Int,
    override val message: String,
    @SerialName("courses")
    val course: List<Course>
    ): BaseResponse() {

    @Serializable
    data class Course(
        @SerialName("id")
        val id: String,
        @SerialName("name")
        val name: String,
        @SerialName("instructor")
        val instructor: String,
        @SerialName("topics")
        val topics: List<String>,
    )
}
