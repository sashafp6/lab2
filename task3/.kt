fun isAscending(n: Int): Boolean { // Проверка на возрастание цифр
    val s = n.toString() // Число в строку
    for (i in 1 until s.length) { // Цикл по цифрам, начиная со второй
        if (s[i] <= s[i - 1]) { // Если не возрастает
            return false // Не возрастает
        }
    }
    return true // Возрастает
}

fun reverseNumber(n: Int): Int { // Переворот числа
    return n.toString().reversed().toInt() // Преобразуем в строку, переворачиваем, преобразуем обратно в число
}

fun main() {
    val numbers = listOf(4, 87, 129, 33, 45) // Список чисел
    print("Result: ") // Вывод текста
    for (num in numbers) { // Цикл по числам
        if (isAscending(num)) { // Если число возрастающее
            print("${reverseNumber(num)} ") // Вывод перевернутого числа
        }
    }
    println() // Перенос строки
}
