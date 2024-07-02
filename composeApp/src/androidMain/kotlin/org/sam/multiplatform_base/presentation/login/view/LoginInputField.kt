package org.sam.multiplatform_base.presentation.login.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import org.sam.multiplatform_base.app.component.ExtraSmallSpacer
import org.sam.multiplatform_base.app.theme.LoginTextFieldBorder
import org.sam.multiplatform_base.app.theme.AppTypography
import org.sam.multiplatform_base.app.theme.*
import org.sam.multiplatform_base.app.theme.diaryInputFieldText
import org.sam.multiplatform_base.app.theme.dimen16

@Composable
fun LoginInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    helperText: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimen16())
    ) {
        Text(
            text = label,
            style = AppTypography.bodyMedium
        )
        ExtraSmallSpacer()
        TextField(
            value = value,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Ascii,
                capitalization = KeyboardCapitalization.Characters
            ),
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .background(TransparentColor)
                .border(
                    LoginTextFieldBorder,
                    shape = AppShapes.medium
                ),
            singleLine = true,
            textStyle = diaryInputFieldText.copy(color = BaseTextColor),
            placeholder = {
                Text(
                    text = placeholder,
                    style = diaryInputFieldText,
                    color = HintTextColor
                )
            },
            colors = TextFieldDefaults.colors()
        )
        ExtraSmallSpacer()
        Text(
            text = helperText,
            style = AppTypography.bodyMedium
        )
    }
}