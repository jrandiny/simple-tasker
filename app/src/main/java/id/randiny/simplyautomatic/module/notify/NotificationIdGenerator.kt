package id.randiny.simplyautomatic.module.notify

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object NotificationIdGenerator {
    fun generateId(identifier: Int): Int {
        val dateFormatter: DateFormat = SimpleDateFormat("hhmmss", Locale.getDefault())
        val today = Date()
        return dateFormatter.format(today).toInt() + identifier * 1000000
    }
}