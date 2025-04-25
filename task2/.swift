import Foundation 

func normalizeEmailSwift(_ email: String) -> String { // Функция нормализации email
    guard let atPos = email.firstIndex(of: "@") else { // Находим индекс символа @
        return "" // Если @ нет, возвращаем пустую строку
    }
    let username = String(email[..<atPos]) // Имя пользователя (до @)
    let domain = String(email[atPos...].dropFirst()) // Домен (после @)

    var normalizedUsername = "" // Строка для нормализованного имени
    for char in username { // Перебираем символы имени
        if char == "." { 
            continue
        } else if char == "+" || char == "*" { 
            break
        } else {
            normalizedUsername.append(char) // Добавляем остальные символы
        }
    }
    return normalizedUsername + "@" + domain // Возвращаем нормализованный email
}

func countUniqueEmailsSwift(_ emails: [String]) -> Int { // Функция подсчета уникальных email
    var uniqueEmails = Set<String>() // Множество для уникальных email
    for email in emails { // Перебираем список email
        uniqueEmails.insert(normalizeEmailSwift(email)) // Нормализуем и добавляем в множество
    }
    return uniqueEmails.count // Возвращаем количество уникальных email
}

let emails1 = ["mar.pha+science@corp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru"] // Пример 1
print("Example 1 Output: \(countUniqueEmailsSwift(emails1))") 

let emails2 = ["mar.pha+science@co.rp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru"] // Пример 2
print("Example 2 Output: \(countUniqueEmailsSwift(emails2))")
