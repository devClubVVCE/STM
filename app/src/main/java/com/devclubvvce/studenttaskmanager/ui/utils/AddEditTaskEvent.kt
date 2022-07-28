package com.devclubvvce.studenttaskmanager.ui.utils

import androidx.compose.ui.focus.FocusState

sealed class AddEditTaskEvent {

    data class EnteredTitle(val value: String) : AddEditTaskEvent()
    data class ChangedTitleFocus(val focusState: FocusState) : AddEditTaskEvent()
    data class EnteredDescription(val value: String) : AddEditTaskEvent()
    data class ChangedDescriptionFocus(val focusState: FocusState) : AddEditTaskEvent()
    data class SelectDate(val value: String) : AddEditTaskEvent()
    data class SelectTime(val value: String) : AddEditTaskEvent()
    object SaveTask : AddEditTaskEvent()
}
