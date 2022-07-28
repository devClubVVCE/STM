package com.devclubvvce.studenttaskmanager.ui.screens.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.devclubvvce.studenttaskmanager.ui.screens.drawArc

@Composable
fun PieChartSection(points: List<Float>, colors: List<Color>) {
    val total = points.sum()
    val proportions = points.map {
        it * 100 / total
    }
    val sweepAnglePercentage = proportions.map {
        360 * it / 100
    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)) {
        Canvas(modifier = Modifier.fillMaxWidth()) {

            var startAngle = 270f
            sweepAnglePercentage.forEachIndexed { index, sweepAngle ->
                drawArc(color = colors[index], startAngle = startAngle, sweepAngle = sweepAngle)
                startAngle += sweepAngle
            }
        }
    }

}