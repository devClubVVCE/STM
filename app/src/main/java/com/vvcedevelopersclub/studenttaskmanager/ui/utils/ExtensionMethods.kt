package com.vvcedevelopersclub.studenttaskmanager.ui.utils

import java.text.SimpleDateFormat
import java.util.*

object ExtensionMethods {
    fun Long.toTime(): String {
        val date = Date(this)
        val format = SimpleDateFormat("hh:mm aa", Locale.US)
        return format.format(date)
    }
}