package com.devclubvvce.studenttaskmanager.ui.screens

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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devclubvvce.studenttaskmanager.R
import com.devclubvvce.studenttaskmanager.ui.screens.components.ButtonComponent
import com.devclubvvce.studenttaskmanager.ui.theme.Background
import com.devclubvvce.studenttaskmanager.ui.theme.PrimaryButtonBackground
import com.devclubvvce.studenttaskmanager.ui.theme.TaskListItemBackground
import com.devclubvvce.studenttaskmanager.ui.utils.AddEditTaskEvent
import com.devclubvvce.studenttaskmanager.ui.utils.ExtensionMethods.parseDateString
import com.devclubvvce.studenttaskmanager.ui.utils.ExtensionMethods.parseTimeString
import com.devclubvvce.studenttaskmanager.ui.viewmodels.AddEditTaskViewModel
import kotlinx.coroutines.flow.collectLatest
import java.util.*

@Composable
fun AddEditTasksScreen(
    navController: NavController,
    taskId: Long,
    viewModel: AddEditTaskViewModel = hiltViewModel()
) {

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
        HeadingSection(taskId)
        TaskFormSection(context, viewModel)
        SaveTaskButtonSection {
            viewModel.onEvent(
                AddEditTaskEvent.SaveTask
            )
        }
    }
}

@Composable
fun HeadingSection(taskId: Long) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = if (taskId == -1L) stringResource(id = R.string.add_task_title) else stringResource(
                id = R.string.update_task_title
            ),
            style = MaterialTheme.typography.h2
        )
        Text(
            text = if (taskId == -1L) stringResource(id = R.string.add_task_subtitle) else stringResource(
                id = R.string.update_task_subtitle
            ),
            style = MaterialTheme.typography.h4
        )

    }
}

@Composable
fun TaskFormSection(
    context: Context,
    viewModel: AddEditTaskViewModel
) {

    val titleState = viewModel.taskTitle.value
    val descriptionState = viewModel.taskDescription.value
    val dateState = viewModel.taskDate.value
    val timeState = viewModel.taskTime.value


    val year: Int
    val month: Int
    val day: Int

    var hour: Int
    val minute: Int
    val calendar = Calendar.getInstance()
    if (dateState.text.isNotEmpty() && timeState.text.isNotEmpty()) {

        year = parseDateString(dateState.text)[2]
        month = parseDateString(dateState.text)[1]
        day = parseDateString(dateState.text)[0]
        hour = parseTimeString(timeState.text)[1].toInt()
        minute = parseTimeString(timeState.text)[2].toInt()
        if (parseTimeString(timeState.text)[0] == "PM") {
            hour += 12
        }

    } else {

        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DATE)
        hour = calendar.get(Calendar.HOUR_OF_DAY)
        minute = calendar.get(Calendar.MINUTE)
        calendar.time = Date()
    }


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

            viewModel.onEvent(AddEditTaskEvent.SelectDate("$dayVal/${monthVal}/$mYear"))
        }, year, if (dateState.text.isNotEmpty()) {
            month - 1
        } else {
            month
        }, day
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
            viewModel.onEvent(AddEditTaskEvent.SelectTime("$hourVal:$minuteVal $amPm"))
        }, hour, minute, false
    )


    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Label(stringResource(id = R.string.task_date_label))
            STMTextField2(
                text = dateState.text,
                hint = dateState.hint,
                onValueChange = {
                },
                singleLine = true,
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TaskListItemBackground)
                    .clickable {
                        datePickerDialog.show()
                    }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Label(stringResource(id = R.string.task_time_label))
            STMTextField2(
                text = timeState.text,
                hint = timeState.hint,
                onValueChange = {
                },
                singleLine = true,
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TaskListItemBackground)
                    .clickable {
                        timePickerDialog.show()
                    }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Label(stringResource(id = R.string.task_title_label))
            STMTextField2(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditTaskEvent.EnteredTitle(it))
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TaskListItemBackground)
                    .onFocusChanged {
                        viewModel.onEvent(AddEditTaskEvent.ChangedTitleFocus(it))
                    }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Label(stringResource(id = R.string.task_description_label))
            STMTextField2(
                text = descriptionState.text,
                hint = descriptionState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditTaskEvent.EnteredDescription(it))
                },
                singleLine = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TaskListItemBackground)
                    .onFocusChanged {
                        viewModel.onEvent(AddEditTaskEvent.ChangedDescriptionFocus(it))
                    }
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
fun STMTextField2(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    enabled: Boolean = true
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.body2.copy(color = Color.Black.copy(alpha = 0.5f))
            )
        },
        singleLine = singleLine,
        enabled = enabled,
        textStyle = MaterialTheme.typography.body2
    )

}

