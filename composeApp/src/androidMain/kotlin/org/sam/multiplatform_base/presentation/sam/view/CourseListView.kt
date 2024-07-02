package org.sam.multiplatform_base.presentation.sam.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.model.dto.CourseDto
import org.sam.multiplatform_base.app.theme.*

@Composable
fun CourseListView(courses: List<CourseDto>, onClickItem : (id: String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(courses) { course ->
            CourseCard(course = course) {
                onClickItem(it)
            }
        }
    }
}

@Composable
fun CourseCard(course: CourseDto, onClickItem : (id: String) -> Unit) {
    Card(modifier = Modifier.padding(dimen8()).fillMaxWidth()) {
        Column(modifier = Modifier
            .padding(dimen16())
            .clickable {
                onClickItem(course.id)
            }
            ) {
            Text(text = course.name, style = AppTypography.displayMedium)
            Text(text = "Instructor: ${course.instructor}")
            Text(text = "Topics:")
            course.topics.forEach { topic ->
                Text("- $topic")
            }
        }
    }
}