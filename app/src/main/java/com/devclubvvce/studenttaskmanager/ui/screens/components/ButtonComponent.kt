package com.devclubvvce.studenttaskmanager.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonComponent(
    buttonBackground: Color,
    label: String,
    icon: ImageVector = Icons.Filled.Add,
    maxWidth: Boolean = false,
    onClick: () -> Unit,
) {
    Row(
        modifier = if (maxWidth) {
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(buttonBackground)
                .clickable {
                    onClick()
                }
                .padding(vertical = 8.dp)

        } else {
            Modifier
                .width(140.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(buttonBackground)
                .clickable {
                    onClick()
                }
                .padding(vertical = 8.dp)

        },
        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = label)
        Text(
            label, style = MaterialTheme.typography.body2.copy(
                fontSize = 20.sp
            )
        )
    }
}
