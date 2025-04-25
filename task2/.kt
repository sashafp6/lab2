fun normalizeEmail (email: String): String { //принимает строку email (адрес электронной почты) в качестве входных данных и возвращает нормализованную строку String. 
    val parts = email.split('@', limit = 2) // Разделяет строку email на две части, используя символ ‘@’ в качестве разделителя. 
    if (parts.size != 2) {
        return "" // Проверяет, было ли разделение успешным. Если parts не содержит ровно две части значит адрес электронной почты имеет неверный формат и функция возвращает пустую строку.
    }
    var username = parts[0] // присваиает имя пользователя(изменяемая переменная)
    val domain = parts[1] // присваивает домен

    var normalizedUsername = ""
    for (char in username) { // Итерирует по каждому символу char в строке username
        if (char == '.') {
            continue
        } else if (char == '+' || char == '*') {
            break
        } else {
            normalizedUsername += char
        }
    }
    return normalizedUsername + "@" + domain // Возвращает нормализованный адрес электронной почты
}

fun countUniqueEmails (emails: List<String>): Int {
    val uniqueEmails = HashSet<String>()
    for (email in emails) { // Итерирует по каждому адресу электронной почты email в списке emails.
        uniqueEmails.add(normalizeEmail (email))
    }
    return uniqueEmails.size // Возвращает количество элементов в HashSet, что соответствует количеству уникальных, нормализованных адресов электронной почты.
}

fun main() {
    val emails1 = listOf("mar.pha+science@corp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru")
    println("1 Output: ${countUniqueEmails (emails1)}")

    val emails2 = listOf("mar.pha+science@co.rp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru")
    println("2 Output: ${countUniqueEmails (emails2)}")
}
