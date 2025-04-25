fun minWindowSubstring(s: String, t: String): String { // Ищет минимальное окно в строке s, содержащее все символы строки t
    if (t.isEmpty()) return "" // Если t пустая строка, возвращает пустую строку

    val need = mutableMapOf<Char, Int>() // символ -> необходимое количество в t
    val window = mutableMapOf<Char, Int>() // символ -> количество в текущем окне

    for (char in t) { // Заполняем карту need
        need[char] = need.getOrDefault(char, 0) + 1 // Увеличиваем количество нужного символа на 1
    }

    var needCount = need.size // Количество уникальных символов в t
    var formed = 0 // Количество уникальных символов в t, содержащихся в окне в нужном количестве

    var left = 0 // Левая граница 
    var right = 0 // Правая граница 

    var minLen = Int.MAX_VALUE // Минимальная длина найденного окна (инициализируем максимальным значением)
    var minStr = "" // Минимальное окно (изначально пустое)

    while (right < s.length) { // Двигаем правую границу окна по строке s
        val char = s[right] // Текущий символ справа
        window[char] = window.getOrDefault(char, 0) + 1 // Увеличиваем количество символа в окне на 1

        if (need.containsKey(char) && window[char] == need[char]) { // Если символ нужен, и его количество в окне соответствует нужному
            formed++ // Увеличиваем счетчик сформированных символов
        }

        while (formed == needCount) { // Пока все нужные символы есть в окне в нужном количестве
            if (right - left + 1 < minLen) { // Если текущая длина окна меньше минимальной найденной
                minLen = right - left + 1 // Обновляем минимальную длину
                minStr = s.substring(left, right + 1) // Обновляем минимальное окно
            }

            val leftChar = s[left] // Символ слева
            window[leftChar] = window.getOrDefault(leftChar, 0) - 1 // Уменьшаем количество символа в окне на 1

            if (need.containsKey(leftChar) && window[leftChar]!! < need[leftChar]!!) { // Если символ нужен, и его количество стало меньше нужного
                formed-- // Уменьшаем счетчик сформированных символов
            }
            left++ // Сдвигаем левую границу окна
        }
        right++ // Сдвигаем правую границу окна
    }
    return minStr // Возвращает минимальное окно
}

fun main() { // Пример использования
    val s = "ADOBECODEBANC"
    val t = "ABC"
    val result = minWindowSubstring(s, t)
    println(result) 
}
