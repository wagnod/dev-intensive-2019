package ru.skillbranch.dev_intensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.math.abs

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
    val res: String
    val curDate = Date()
    val diffInMs = curDate.time - date.time
    val hh = TimeUnit.MILLISECONDS.toHours(diffInMs)
    val mm = TimeUnit.MILLISECONDS.toMinutes(diffInMs)
    val ss = TimeUnit.MILLISECONDS.toSeconds(diffInMs)
    val dd = TimeUnit.MILLISECONDS.toDays(diffInMs)

    res = when (ss) {
        in 0..1 -> "только что"
        in 1..45 -> "несколько секунд назад"
        in 45..75 -> "минуту назад"
        //in 75..2700 -> "$mm + ${plural(TimeUnits.MINUTE, mm.toString())} назад"
        in 75..2700 -> "${TimeUnits.SECOND.plural(1)} назад"
        in 2700..4500 -> "час назад"
        in 4500..79200 -> "${TimeUnits.HOUR.plural(hh)} назад"
        in 79200..93600 -> "день назад"
        in 93600..31104000 -> "${TimeUnits.DAY.plural(dd)} назад"
        in 31104000..Long.MAX_VALUE -> "более года назад"
        else -> when (ss) {
            in -1..0 -> "только что"
            in -45..-1 -> "через несколько секунд"
            in -75..-45 -> "через минуту"
            in -2700..-75 -> "через ${TimeUnits.MINUTE.plural(abs(mm + 1))}"
            in -4500..-2700 -> "через час"
            in -79200..-4500 -> "через ${TimeUnits.HOUR.plural(abs(hh + 1))}"
            in -93600..-79200 -> "через день"
            in -31104000..-93600 -> "через ${TimeUnits.DAY.plural(abs(dd) + 1)}"
            else -> "более чем через год"
        }
    }
    return res
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;
}

fun TimeUnits.plural(num : Long): String {
    // 1 секунда, 2-4 секунды, 5-10 секунд
    val two: ArrayList<Char> = arrayListOf('2', '3', '4')
    val zero: ArrayList<Char> = arrayListOf('0', '5', '6', '7', '8', '9')
    val res: String
    val time = num.toString()
    when (this) {
        TimeUnits.SECOND -> res = when {
            two.contains(time[time.length - 1]) -> "$num секунды"
            zero.contains(time[time.length - 1]) -> "$num секунд"
            else -> "$num секунду"
        }
        TimeUnits.MINUTE -> res = when {
            two.contains(time[time.length - 1]) -> "$num минуты"
            zero.contains(time[time.length - 1]) -> "$num минут"
            else -> "$num минуту"
        }
        TimeUnits.HOUR -> res = when {
            two.contains(time[time.length - 1]) -> "$num часа"
            zero.contains(time[time.length - 1]) -> "$num часов"
            else -> "$num час"
        }
        else -> res = when {
            two.contains(time[time.length - 1]) -> "$num дня"
            zero.contains(time[time.length - 1]) -> "$num дней"
            else -> "$num день"
        }
    }
    return res
}