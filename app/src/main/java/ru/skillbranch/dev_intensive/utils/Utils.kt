package ru.skillbranch.dev_intensive.utils

object Utils {
    private val map = mapOf(
        'а' to "a", 'б' to "b", 'в' to "v", 'г' to "g", 'д' to "d", 'е' to "e",
        'ё' to "e", 'ж' to "zh", 'з' to "z", 'и' to "i", 'й' to "i", 'к' to "k", 'л' to "l",
        'м' to "m", 'н' to "n", 'о' to "o", 'п' to "p", 'р' to "r", 'с' to "s", 'т' to "t",
        'у' to "u", 'ф' to "f", 'х' to "h", 'ц' to "c", 'ч' to "ch", 'ш' to "sh", 'щ' to "sh'",
        'ъ' to "", 'ы' to "i", 'ь' to "", 'э' to "e", 'ю' to "yu", 'я' to "ya"
    )

    //FIX IT
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return if (firstName == null || firstName == "") {
            Pair(null, null)
        } else {
            Pair(firstName, lastName)
        }
    }

    fun transliteration(payload: String, divider: String = " "): String? {
        var result: String = ""
        for (char in payload.toLowerCase().toCharArray()) {
            result += if (map.containsKey(char)) {
                map.get(char)
            } else {
                divider
            }
        }
        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String {
        return firstName?.get(0).toString() + lastName?.get(0)
    }
}