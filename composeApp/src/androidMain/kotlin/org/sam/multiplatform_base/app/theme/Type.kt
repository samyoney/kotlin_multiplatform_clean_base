package org.sam.multiplatform_base.app.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
val AppTypography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.W300,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp,
        color = BaseTextColor
    ),
    displayMedium = TextStyle(
        fontWeight = FontWeight.W300,
        fontSize = 9.sp,
        letterSpacing = 0.1.sp,
        color = SubTitleColor
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W600,

        fontSize = 16.sp,
        color = TitleBlackColor
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        letterSpacing = 0.25.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W600,
        fontSize = 22.sp,
        color = MainColor
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W300,
        fontSize = 12.sp,
        color = BaseTextColor
    )
)

val diaryInputFieldText = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W300,
    fontSize = 18.sp
)

val selectedButtonText = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W600,
    fontSize = 14.sp,
    color = BgColor
)

val unSelectedButtonText = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W600,
    fontSize = 14.sp,
    color = BaseTextColor
)

val unSelectedDiaryButtonText = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W600,
    fontSize = 14.sp,
    color = BaseTextColor.copy(alpha = 0.5F)
)

val painScoreLargeText = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W600,
    fontSize = 20.sp,
    color = BgColor
)

val resultLargeText = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W600,
    fontSize = 16.sp,
    color = BgColor
)

val resultMediumText = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W600,
    fontSize = 14.sp,
    color = BgColor
)

val resultSmallText = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W600,
    fontSize = 13.sp,
    color = BgColor
)