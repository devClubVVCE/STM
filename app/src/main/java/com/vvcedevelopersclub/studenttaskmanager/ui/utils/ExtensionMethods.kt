package com.vvcedevelopersclub.studenttaskmanager.ui.utils

object ExtensionMethods {

    fun parseDateString(dateString: String): List<Int> {
        return dateString.split("/").map {
            it.toInt()
        }
    }

    fun parseTimeString(timeString: String): List<String> {
        val split1 = timeString.split(" ")
        val amPm = split1[1]
        val split2 = split1[0].split(":")
        return listOf(amPm, split2[0], split2[1])
    }
}