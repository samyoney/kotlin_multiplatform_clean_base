package data.model.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudentResponse(
    override val status: Int,
    override val message: String,
    @SerialName("students")
    val students: List<Student>
    ): BaseResponse() {

    @Serializable
    data class Student(
        @SerialName("id")
        val id: String,
        @SerialName("name")
        val name: String,
        @SerialName("birth")
        val birth: String,
    )
}
