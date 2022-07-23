package com.vvcedevelopersclub.studenttaskmanager.ui.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vvcedevelopersclub.studenttaskmanager.R
import com.vvcedevelopersclub.studenttaskmanager.ui.screens.components.ButtonComponent
import com.vvcedevelopersclub.studenttaskmanager.ui.theme.Background
import com.vvcedevelopersclub.studenttaskmanager.ui.theme.PrimaryButtonBackground
import com.vvcedevelopersclub.studenttaskmanager.ui.theme.TaskListItemBackground
import com.vvcedevelopersclub.studenttaskmanager.ui.viewmodels.AddEditTaskViewModel
import kotlinx.coroutines.flow.collectLatest
import java.util.*

@Composable
fun AddEditTasksScreen(
    navController: NavController,
    viewModel: AddEditTaskViewModel = hiltViewModel()
) {
    val date = remember {
        mutableStateOf("")
    }

    val time = remember {
        mutableStateOf("")
    }

    val title = remember {
        mutableStateOf("")
    }

    val description = remember {
        mutableStateOf("")
    }

    val context = LocalContext.current


    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditTaskViewModel.UiEvent.ShowErrorMessage
                -> {
                    Toast.makeText(
                        context,
                        event.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is AddEditTaskViewModel.UiEvent.SaveTask -> {
                    navController.navigateUp()
                }

            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(horizontal = 16.dp),

        verticalArrangement = Arrangement.SpaceBetween
    ) {
        HeadingSection()
        TaskFormSection(context, date, time, title, description)
        SaveTaskButtonSection() {
            viewModel.addTask(title.value, description.value, time.value, date.value)
        }
    }
}

@Composable
fun HeadingSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.add_task_title),
            style = MaterialTheme.typography.h2
        )
        Text(
            text = stringResource(id = R.string.add_task_subtitle),
            style = MaterialTheme.typography.h4
        )

    }
}

@Composable
fun TaskFormSection(
    context: Context,
    date: MutableState<String>,
    time: MutableState<String>,
    title: MutableState<String>,
    description: MutableState<String>
) {

    val year: Int
    val month: Int
    val day: Int

    val hour: Int
    val minute: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DATE)
    hour = calendar.get(Calendar.HOUR_OF_DAY)
    minute = calendar.get(Calendar.MINUTE)

    calendar.time = Date()


    val datePickerDialog = DatePickerDialog(
        context,
        R.style.DialogTheme,
        { datePicker: DatePicker, mYear: Int, mMonth: Int, dayOfMonth: Int ->
            datePicker.minDate = calendar.timeInMillis
            val dayVal = if (dayOfMonth < 10) {
                "0$dayOfMonth"
            } else {
                dayOfMonth
            }
            val monthVal = if (mMonth + 1 < 10) {
                "0${mMonth + 1}"
            } else {
                mMonth + 1
            }

            date.value = "$dayVal/${monthVal}/$mYear"
        }, year, month, day
    )

    val timePickerDialog = TimePickerDialog(
        context, R.style.DialogTheme, { timePicker: TimePicker, mHour: Int, mMinute: Int ->
            val amPm: String = if (timePicker.hour < 12) "AM" else "PM"
            val hourVal = if (mHour < 10) {
                "0$mHour"
            } else if (mHour in 10..12) {
                "$mHour"
            } else {
                if (mHour % 12 < 10) {
                    "0${mHour % 12}"
                } else {
                    "${mHour % 12}"
                }
            }
            val minuteVal = if (mMinute < 10) {
                "0$mMinute"
            } else {
                "$mMinute"
            }
            time.value = "$hourVal:$minuteVal $amPm"
        }, hour, minute, false
    )



    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Label(stringResource(id = R.string.task_date_label))
            STMTextField(
                value = date.value, onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TaskListItemBackground)
                    .clickable {
                        datePickerDialog.show()
                    },
                placeholder = R.string.task_date_placeholder,
                enabled = false,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Label(stringResource(id = R.string.task_time_label))
            STMTextField(
                value = time.value, onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TaskListItemBackground)
                    .clickable {
                        timePickerDialog.show()
                    },
                placeholder = R.string.task_time_placeholder,
                enabled = false,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Label(stringResource(id = R.string.task_title_label))
            STMTextField(
                value = title.value,
                onValueChange = {
                    title.value = it
                },
                placeholder = R.string.task_title_placeholder,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Label(stringResource(id = R.string.task_description_label))
            STMTextField(
                value = description.value, onValueChange = {
                    description.value = it
                },
                placeholder = R.string.task_description_placeholder,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(TaskListItemBackground)
            )
        }
    }
}

@Composable
fun Label(label: String) {
    Text(
        label,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        style = MaterialTheme.typography.subtitle2
    )
}

@Composable
fun SaveTaskButtonSection(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        ButtonComponent(
            buttonBackground = PrimaryButtonBackground,
            label = stringResource(id = R.string.save_task),
            icon = Icons.Filled.Check,
            maxWidth = true,
            onClick = onClick
        )
    }
}

@Composable
fun STMTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .background(TaskListItemBackground),
    placeholder: Int,
    maxLines: Int = 1,
    enabled: Boolean = true
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = {
            Text(
                text = stringResource(id = placeholder),
                style = MaterialTheme.typography.body2.copy(color = Color.Black.copy(alpha = 0.5f))
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        maxLines = maxLines,
        enabled = enabled,
        textStyle = MaterialTheme.typography.body2
    )
}

