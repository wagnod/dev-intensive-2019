package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")


        var firstName = parts?.getOrNull(0)
        if (firstName == "") firstName = null
        var lastName = parts?.getOrNull(1)
        if (lastName == "") lastName = null

        return Pair(firstName, lastName)
    }

    fun transliteration(payloader: String, divider: String = " "): String {
        val dictionary = mapOf(
            "а" to "a", "А" to "A",
            "б" to "b", "Б" to "B",
            "в" to "v", "В" to "V",
            "г" to "g", "Г" to "G",
            "д" to "d", "Д" to "D",
            "е" to "e", "Е" to "E",
            "ё" to "e", "Ё" to "E",
            "ж" to "zh", "Ж" to "Zh",
            "з" to "z", "З" to "Z",
            "и" to "i", "И" to "I",
            "й" to "i", "Й" to "I",
            "к" to "k", "К" to "K",
            "л" to "l", "Л" to "L",
            "м" to "m", "М" to "M",
            "н" to "n", "Н" to "N",
            "о" to "o", "О" to "O",
            "п" to "p", "П" to "P",
            "р" to "r", "Р" to "R",
            "с" to "s", "С" to "S",
            "т" to "t", "Т" to "T",
            "у" to "u", "У" to "U",
            "ф" to "f", "Ф" to "F",
            "х" to "h", "Х" to "H",
            "ц" to "c", "Ц" to "C",
            "ч" to "ch", "Ч" to "Ch",
            "ш" to "sh", "Ш" to "Sh",
            "ъ" to "", "Ъ" to "",
            "щ" to "sh'", "Щ" to "Sh'",
            "ь" to "", "Ь" to "",
            "ы" to "i", "Ы" to "I",
            "э" to "e", "Э" to "E",
            "ю" to "yu", "Ю" to "Yu",
            "я" to "ya", "Я" to "Ya"
        )
        val (firstName, lastName) = parseFullName(payloader)

        var reFName = ""
        var reLName = ""
        if (firstName != null) {
            for (char in firstName) {
                reFName += dictionary.getOrElse(char.toString()){char.toString()}
            }
        }

        if (lastName != null) {
            for (char in lastName) {
                reLName += dictionary.getOrElse(char.toString()){char.toString()}
            }
        }

        return "$reFName$divider$reLName"
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var firstLitera: String = firstName?.getOrNull(0)?.toUpperCase().toString()
        var lastLitera: String = lastName?.getOrNull(0)?.toUpperCase().toString()

        if (firstLitera == "null") firstLitera = " "
        if (lastLitera == "null") lastLitera = " "
        return if (firstLitera == " " && lastLitera == " ") null else "$firstLitera$lastLitera"

    }
}