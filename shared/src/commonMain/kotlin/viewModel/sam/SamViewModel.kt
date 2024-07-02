package viewModel.sam

import data.usecase.enroll.AddStudentIntoCourseUseCase
import data.usecase.enroll.GetCoursesUseCase
import data.usecase.enroll.GetStudentsByCourseIdUseCase
import data.usecase.enroll.GetStudentsUseCase
import data.usecase.enroll.RemoveStudentFromCourseUseCase
import framework.base.BaseViewModel
import kotlinx.coroutines.flow.update
import org.koin.core.component.inject

class SamViewModel : BaseViewModel<SamState, SamEvent>() {

    private val getCoursesUseCase: GetCoursesUseCase by inject()
    private val getStudentsUseCase: GetStudentsUseCase by inject()
    private val getStudentsByCourseIdUseCase: GetStudentsByCourseIdUseCase by inject()
    private val addStudentIntoCourseUseCase: AddStudentIntoCourseUseCase by inject()
    private val removeStudentFromCourseUseCase: RemoveStudentFromCourseUseCase by inject()

    override fun initialState() = SamState()
    private var currentCourseId = String()

    override fun onTriggerEvent(eventType: SamEvent) {
        when (eventType) {
            is SamEvent.InitData -> {
                onInitData()
            }

            is SamEvent.Back -> {
                onBack()
            }

            is SamEvent.RegisterStudent -> {
                onRegisterStudent(eventType.id)
            }

            is SamEvent.RemoveStudent -> {
                onRemoveStudent(eventType.id)
            }

            is SamEvent.SelectCourse -> {
                onSelectCourse(eventType.id)
            }
        }
    }

    private fun onInitData() = safeLaunch {
        executeLocalUseCase(getCoursesUseCase()) { data ->
            uiState.update { it.copy(listCourse = data) }
        }
        executeLocalUseCase(getStudentsUseCase()) { data ->
            uiState.update { it.copy(listStudent = data) }
        }
    }

    private fun onBack() = safeLaunch {
        uiState.update { it.copy(isCourseScreen = true) }
    }


    private fun onRegisterStudent(id: String) = safeLaunch {
        executeLocalUseCase(addStudentIntoCourseUseCase(id, currentCourseId)) {
            onSelectCourse(currentCourseId)
        }
    }

    private fun onRemoveStudent(id: String) = safeLaunch {
        executeLocalUseCase(removeStudentFromCourseUseCase(id)) {
            onSelectCourse(currentCourseId)
        }
    }

    private fun onSelectCourse(courseId: String) = safeLaunch {
        currentCourseId = courseId
        executeLocalUseCase(getStudentsByCourseIdUseCase(courseId)) { data ->
            uiState.update { it.copy(isCourseScreen = false, listStudentByCode = data) }
        }
    }
}