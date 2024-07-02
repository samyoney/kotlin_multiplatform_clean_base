package org.sam.multiplatform_base.presentation.sam.view

import AppText
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import data.model.dto.StudentDto
import org.koin.compose.koinInject
import org.sam.multiplatform_base.app.theme.*

@Composable
fun StudentListView(isRegistered: Boolean, students: List<StudentDto>, onClick: (id: Long) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(students) { studentDto ->
            StudentCard(isRegistered, student = studentDto, onClick)
        }
    }
}

@Composable
fun StudentCard(isRegistered: Boolean, student: StudentDto, onClick: (id: Long) -> Unit) {
    val appText: AppText = koinInject()
    Card(
        modifier = Modifier
            .padding(vertical = dimen8())
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier
            .fillMaxHeight()) {
            Text(
                modifier = Modifier
                    .padding(dimen8())
                    .weight(1.0F)
                    .align(Alignment.CenterVertically),
                text = "${student.name} (${student.birth})"
            )
            Button(
                onClick = { onClick(student.id) }, colors = ButtonDefaults.textButtonColors(
                    containerColor = if (isRegistered) Color.Red else BgColor, contentColor = BaseColor
                ), modifier = Modifier
                    .height(dimen54())
                    .padding(dimen4())
                    .clip(AppShapes.small)
            ) {
                Text(
                    if (isRegistered) appText.removeStudent() else appText.registerCourse(),
                    style = AppTypography.displayLarge.copy(color = BaseColor)
                )
            }
        }
    }
}