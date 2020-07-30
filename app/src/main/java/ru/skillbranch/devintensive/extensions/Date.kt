package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val time = this.time
    val seconds = ((time - date.time)/1000).toInt()
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24

    val MIN_VALUE: Int = Int.MIN_VALUE
    var result = ""
    result += when(seconds) {
        0 -> "только что"
        in -2147483648..-3110400 -> "более года назад"
        in -3110400..-93600 -> "${TimeUnits.DAY.plural(abs(days))} назад"
        in -93600..-79200 -> "день назад"
        in -79200..-4500 -> "${TimeUnits.HOUR.plural(abs(hours))} назад"
        in -4500..-2700 -> "час назад"
        in -2700..-75 -> "${TimeUnits.MINUTE.plural(abs(minutes))} назад"
        in -75..-45 -> "минуту назад"
        in -45..-1 -> "несколько секунд назад"
        in 1..45 -> "через несколько секунд"
        in 45..75 -> "через минуту"
        in 75..2700 -> "через ${TimeUnits.MINUTE.plural(minutes)}"
        in 2700..4500 -> "через час"
        in 4500..79200 -> "через ${TimeUnits.HOUR.plural(hours)}"
        in 79200..93600 -> "через день"
        in 93600..3110400 -> "через ${TimeUnits.DAY.plural(days)}"
        else -> "более чем через год"
    }
    return result

    /*0с - 1с "только что"
1с - 45с "несколько секунд назад"
45с - 75с "минуту назад"
75с - 45мин "N минут назад"
45мин - 75мин "час назад"
75мин 22ч "N часов назад"
22ч - 26ч "день назад"
26ч - 360д "N дней назад"
>360д "более года назад"*/
}

enum class TimeUnits {
    SECOND {
        override fun plural(value: Int): String {
            var time = value.toString()
            time += when (time[time.length - 1]) {
                '1' -> " секунду"
                '2' -> " секунды"
                '3' -> " секунды"
                '4' -> " секунды"
                '5' -> " секунд"
                '6' -> " секунд"
                '7' -> " секунд"
                '8' -> " секунд"
                '9' -> " секунд"
                '0' -> " секунд"
                else -> ""
            }
            return time
        }
    },
    MINUTE {
        override fun plural(value: Int): String {
            var time = value.toString()
            time += when (time[time.length - 1]) {
                '1' -> " минуту"
                '2' -> " минуты"
                '3' -> " минуты"
                '4' -> " минуты"
                '5' -> " минут"
                '6' -> " минут"
                '7' -> " минут"
                '8' -> " минут"
                '9' -> " минут"
                '0' -> " минут"
                else -> ""
            }
            return time
        }
    },
    HOUR {
        override fun plural(value: Int): String {
            var time = value.toString()
            time += when (time[time.length - 1]) {
                '1' -> " час"
                '2' -> " часа"
                '3' -> " часа"
                '4' -> " часа"
                '5' -> " часов"
                '6' -> " часов"
                '7' -> " часов"
                '8' -> " часов"
                '9' -> " часов"
                '0' -> " часов"
                else -> ""
            }
            return time
        }
    },
    DAY {
        override fun plural(value: Int): String {
            var time = value.toString()
            time += when (time[time.length - 1]) {
                '1' -> " день"
                '2' -> " дня"
                '3' -> " дня"
                '4' -> " дня"
                '5' -> " дней"
                '6' -> " дней"
                '7' -> " дней"
                '8' -> " дней"
                '9' -> " дней"
                '0' -> " дней"
                else -> ""
            }
            return time
        }
    };

    abstract fun plural(value: Int): String
}