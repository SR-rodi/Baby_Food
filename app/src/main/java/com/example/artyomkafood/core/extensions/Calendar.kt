package com.example.artyomkafood.core.extensions

import java.util.*

fun Calendar.createDate(): Calendar {
    val newCalendar = Calendar.getInstance().apply {
        clear()
        set(Calendar.YEAR, this.get(Calendar.YEAR))
        set(Calendar.MONTH, this.get(Calendar.MONTH))
        set(Calendar.DAY_OF_MONTH, this.get(Calendar.DAY_OF_MONTH))
    }
    return newCalendar
}
