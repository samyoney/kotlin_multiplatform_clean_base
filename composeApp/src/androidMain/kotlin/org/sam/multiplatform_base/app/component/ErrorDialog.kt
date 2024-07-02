package org.sam.multiplatform_base.app.component

import AppText
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import framework.extension.isNotNullOrBlank
import org.koin.compose.koinInject
import org.sam.multiplatform_base.app.theme.AppTypography

@Composable
fun ErrorDialog(
    title: String = String(),
    content: String = String(),
    dismiss: () -> Unit
) {
    val appText: AppText = koinInject()

    AlertDialog(
        modifier = Modifier.wrapContentSize(),
        onDismissRequest = {
            dismiss()
        },
        title = if (title.isNotNullOrBlank()) {
            {
                Text(text = title)
            }
        } else null,
        text =
        if (content.isNotNullOrBlank()) {
            {
                Text(text = content)
            }
        } else null,
        confirmButton = {
            TextButton(
                onClick = {
                    dismiss()
                }
            ) {
                Text(style = AppTypography.bodyMedium, text = appText.ok())
            }
        }
    )
}


@Composable
fun AlertDialog(
    title: String = String(),
    content: String = String(),
    confirmLabel: String = String(),
    dismissLabel: String = String(),
    confirm: () -> Unit,
    dismiss: () -> Unit
) {
    AlertDialog(
        modifier = Modifier.wrapContentSize(),
        onDismissRequest = {
            dismiss()
        },
        title = if (title.isNotNullOrBlank()) {
            {
                Text(text = title)
            }
        } else null,
        text =
        if (content.isNotNullOrBlank()) {
            {
                Text(text = content)
            }
        } else null,
        dismissButton = {
            TextButton(
                onClick = {
                    dismiss()
                }
            ) {
                Text(style = AppTypography.bodyMedium, text = dismissLabel)
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    confirm()
                }
            ) {
                Text(style = AppTypography.bodyMedium, text = confirmLabel)
            }
        }
    )
}
