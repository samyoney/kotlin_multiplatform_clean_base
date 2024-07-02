package org.sam.multiplatform_base.presentation.login.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import org.sam.multiplatform_base.app.theme.AppShapes
import org.sam.multiplatform_base.app.theme.*
import org.sam.multiplatform_base.app.theme.dimen38
import org.sam.multiplatform_base.app.theme.dimen54

@Composable
fun LoginButton(title: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.textButtonColors(
            containerColor = BgColor,
            contentColor = BaseColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(dimen54())
            .padding(horizontal = dimen38())
            .clip(AppShapes.small)
    ) {
        Text(
            title,
            style = AppTypography.titleLarge
        )
    }
}