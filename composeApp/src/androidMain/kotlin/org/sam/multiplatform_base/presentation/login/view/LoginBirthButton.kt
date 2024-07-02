package org.sam.multiplatform_base.presentation.login.view

import AppText
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import org.koin.compose.koinInject
import org.sam.multiplatform_base.R
import org.sam.multiplatform_base.app.component.*
import org.sam.multiplatform_base.app.theme.AppShapes
import org.sam.multiplatform_base.app.theme.AppTypography
import org.sam.multiplatform_base.app.theme.BaseTextColor
import org.sam.multiplatform_base.app.theme.HintTextColor
import org.sam.multiplatform_base.app.theme.*
import viewModel.login.LoginState
import java.util.Calendar

@Composable
fun LoginBirthButton(
    uiState: LoginState,
    onYearMonthSelected: (year: Int, month: Int) -> Unit
) {
    val appText: AppText = koinInject()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = dimen20(),
                horizontal = dimen16()
            )
    ) {
        Text(
            text = appText.birthday(),
            style = AppTypography.bodyMedium
        )
        ExtraSmallSpacer()
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                showYearMonthPickerDialog(context, appText, null) { year, month ->
                    onYearMonthSelected(year, month)
                }
            },
            shape = AppShapes.medium,
            colors = ButtonDefaults.buttonColors(TransparentColor),
            border = LoginTextFieldBorder,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimen10()),
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    text = uiState.birth.ifEmpty { appText.birthdayPlaceHolder() },
                    style = if (uiState.birth.isEmpty()) AppTypography.displayLarge else diaryInputFieldText,
                    color = if (uiState.birth.isEmpty()) HintTextColor else BaseTextColor
                )
            }
        }
        ExtraSmallSpacer()
        Text(
            text = appText.birthdayDescription(),
            style = AppTypography.bodyMedium
        )
    }
}

fun showYearMonthPickerDialog(
    context: Context,
    appText: AppText,
    parentView: ViewGroup?,
    onYearMonthSelected: (year: Int, month: Int) -> Unit
) {

    val dialogView = LayoutInflater.from(context).inflate(R.layout.year_month_picker, parentView)

    val yearPicker: NumberPicker = dialogView.findViewById(R.id.yearPicker)
    val monthPicker: NumberPicker = dialogView.findViewById(R.id.monthPicker)
    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH)

    yearPicker.minValue = currentYear - 100
    yearPicker.maxValue = currentYear
    yearPicker.value = currentYear
    monthPicker.minValue = 1
    monthPicker.maxValue = 12
    monthPicker.value = currentMonth + 1

    AlertDialog.Builder(context).apply {
        setView(dialogView)
        setPositiveButton(appText.ok()) { _, _ ->
            onYearMonthSelected(
                yearPicker.value,
                monthPicker.value
            )
        }
        setNegativeButton(
            appText.cancel(),
            null
        )
    }.create().show()
}