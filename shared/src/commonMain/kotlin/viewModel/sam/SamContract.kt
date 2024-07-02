package viewModel.sam

import data.model.dto.CourseDto
import data.model.dto.StudentDto

data class SamState(
    var listCourse: List<CourseDto> = arrayListOf(),
    var listStudent: List<StudentDto> = arrayListOf(),
    var listStudentByCode: List<StudentDto> = arrayListOf(),

    var isCourseScreen: Boolean = true,
)

sealed class SamEvent {
    data object Back: SamEvent()

    data object InitData: SamEvent()

    data class RemoveStudent(val id: String) : SamEvent()
    data class RegisterStudent(val id: String) : SamEvent()

    data class SelectCourse(val id: String) : SamEvent()
}