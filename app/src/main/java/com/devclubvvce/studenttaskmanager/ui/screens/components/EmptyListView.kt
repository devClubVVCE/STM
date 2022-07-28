package com.devclubvvce.studenttaskmanager.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devclubvvce.studenttaskmanager.R
import com.devclubvvce.studenttaskmanager.ui.theme.Background
import com.devclubvvce.studenttaskmanager.ui.theme.ButtonBackground
import com.devclubvvce.studenttaskmanager.ui.theme.ibmPlexSans

@Composable
fun EmptyListView(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_tasks_background),
            contentDescription = stringResource(id = R.string.empty_list_image),
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            stringResource(id = R.string.get_started),
            style = TextStyle(
                fontFamily = ibmPlexSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                color = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(64.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .clip(RoundedCornerShape(8.dp))
                .background(ButtonBackground)
                .clickable {
                    onClick()
                }
                .padding(horizontal = 12.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_add_24),
                contentDescription = stringResource(
                    id = R.string.add_task_button
                ),
                tint = Color.Black,
            )
            Text(
                text = stringResource(id = R.string.add_first_task),
                style = TextStyle(
                    fontFamily = ibmPlexSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            )
        }
    }
}