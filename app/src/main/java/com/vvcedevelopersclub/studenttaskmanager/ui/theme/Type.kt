package com.vvcedevelopersclub.studenttaskmanager.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vvcedevelopersclub.studenttaskmanager.R

val ibmPlexSans = FontFamily(
    listOf(
        Font(R.font.ibm_plex_sans, FontWeight.Normal),
        Font(R.font.ibmplex_sans_semibold, FontWeight.SemiBold),
        Font(R.font.ibmplexsans_bold, FontWeight.Bold)
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = ibmPlexSans,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp
    ),
    body2 = TextStyle(
        fontFamily = ibmPlexSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)