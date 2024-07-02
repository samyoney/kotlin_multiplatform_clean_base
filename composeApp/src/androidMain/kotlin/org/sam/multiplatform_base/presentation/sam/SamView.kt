package org.sam.multiplatform_base.presentation.sam

import AppText
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.asStateFlow
import org.koin.compose.koinInject
import org.sam.multiplatform_base.app.component.ExtraLargeSpacer
import org.sam.multiplatform_base.app.theme.*
import org.sam.multiplatform_base.app.component.*
import org.sam.multiplatform_base.presentation.sam.view.*
import viewModel.sam.SamEvent
import viewModel.sam.SamViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SamView() {
    val appText = koinInject<AppText>()
    val viewModel = koinInject<SamViewModel>()

    val uiState by viewModel.uiState.asStateFlow().collectAsState()

    BackHandler {
        viewModel.onTriggerEvent(SamEvent.Back)
    }

    LaunchedEffect(Unit) {
        viewModel.onTriggerEvent(SamEvent.InitData)
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(if (uiState.isCourseScreen) appText.courseNavTab() else appText.studentNavTab())
            },
            colors = TopAppBarDefaults.topAppBarColors(BgColor),
        )
    },
        content = { padding ->
            Column(
                modifier = Modifier.padding(
                    vertical = padding.calculateTopPadding(),
                    horizontal = dimen24()
                )
            ) {
                if (uiState.isCourseScreen) {
                    CourseListView(uiState.listCourse) {
                        viewModel.onTriggerEvent(SamEvent.SelectCourse(it))
                    }
                } else {
                    ExtraLargeSpacer()
                    Column(
                        modifier = Modifier
                            .padding(
                                horizontal = dimen8()
                            )
                            .weight(1.0F)
                    ) {
                        Text(
                            text = appText.totalStudentsTitle(),
                            style = AppTypography.displayLarge
                        )
                        LargeSpacer()
                        StudentListView(isRegistered = false, students = uiState.listStudent) {
                            viewModel.onTriggerEvent(SamEvent.RegisterStudent(it.toString()))
                        }
                    }
                    ExtraLargeSpacer()
                    Column(
                        modifier = Modifier
                            .padding(
                                horizontal = dimen8()
                            )
                            .weight(1.0F)
                    ) {
                        Text(
                            text = appText.enrolledStudent(),
                            style = AppTypography.displayLarge
                        )
                        LargeSpacer()
                        StudentListView(isRegistered = true, students = uiState.listStudentByCode) {
                            viewModel.onTriggerEvent(SamEvent.RemoveStudent(it.toString()))
                        }
                    }
                }
            }
        })
}





