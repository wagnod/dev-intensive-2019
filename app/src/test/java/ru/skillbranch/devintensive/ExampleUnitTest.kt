package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils
import java.lang.Math.abs
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        val user = User("1", "John", "Cena")
    }

    @Test
    fun test_factory() {
        val user1 = User.makeUser("John Cena")
        val user2 = User.makeUser("John Wick")
        val user3 = User.makeUser("John Siverhand")

        println(user1)
        println(user2)
        println(user3)
    }

    @Test
    fun test_copy() {
        val user1 = User.makeUser("John Wick")
        val user2 = user1.copy(lastVisit = Date())
        val user3 = user1.copy(lastName = "Cena", lastVisit = Date().add(2, TimeUnits.HOUR))
        val user4 = user1.copy(lastVisit = Date().add(-2))


        println(
            """
            ${user1.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
            ${user4.lastVisit?.format()}
        """.trimIndent()
        )
    }

    @Test
    fun test_data_mapping() {
        val user = User.makeUser("Макеев Михаил")
        val newUser = user.copy(lastVisit = Date().add(-800, TimeUnits.DAY))

        println(newUser)

        val userView = newUser.toUserView()
        userView.printMe()
    }

    @Test
    fun test_parse_full_name() {
        println(Utils.parseFullName(null)) //null null
        println(Utils.parseFullName("")) //null null
        println(Utils.parseFullName(" ")) //null null
        println(Utils.parseFullName("John")) //John null
    }

    @Test
    fun test_to_initials() {
        println(Utils.toInitials("john" ,"doe")) //JD
        println(Utils.toInitials("John", null)) //J
        println(Utils.toInitials(null, null)) //null
        println(Utils.toInitials(" ", "")) //null
    }

    @Test
    fun test_transliteration(){
        println(Utils.transliteration("Женя Стереотипов")) //Zhenya Stereotipov
        println(Utils.transliteration("Amazing Петр","_")) //Amazing_Petr
    }

    @Test
    fun test_plural(){
        println(TimeUnits.SECOND.plural(1)) //1 секунду
        println(TimeUnits.MINUTE.plural(4)) //4 минуты
        println(TimeUnits.HOUR.plural(19)) //19 часов
        println(TimeUnits.DAY.plural(222)) //222 дня
    }

    @Test
    fun test_humanize(){
        println(Date().add(-2, TimeUnits.HOUR).humanizeDiff()) //2 часа назад
        println(Date().add(-5, TimeUnits.DAY).humanizeDiff()) //5 дней назад
        println(Date().add(2, TimeUnits.MINUTE).humanizeDiff()) //через 2 минуты
        println(Date().add(7, TimeUnits.DAY).humanizeDiff()) //через 7 дней
        println(Date().add(-400, TimeUnits.DAY).humanizeDiff()) //более года назад
        println(Date().add(400, TimeUnits.DAY).humanizeDiff()) //более чем через год
    }

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("Макеев Михаил")
        val txtMessage =
            BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", type = "text")
        val imgMessage =
            BaseMessage.makeMessage(user, Chat("0"), payload = "any image url", type = "image")

        println(txtMessage.formatMessage())
        println(imgMessage.formatMessage())
    }
}
