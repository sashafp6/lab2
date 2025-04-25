use std::collections::HashSet;

fn normalize_email_rust(email: &str) -> String {
    // Объявляет функцию `normalize_email_rust`, принимающую строковый слайс `email` и возвращающую строку `String`.
    if let Some(at_pos) = email.find('@') {
        // Использует `if let` для проверки наличия символа "@" в строке `email`.
        let username = &email[..at_pos];
        // Создает строковый слайс `username` , содержащий часть строки `email` до символа "@".
        let domain = &email[at_pos + 1..];
        // Создает строковый слайс `domain`, содержащий часть строки `email` после символа "@".
        let mut normalized_username = String::new();
        // Создает изменяемую строку `normalized_username` типа `String` и инициализирует ее как пустую строку.

        for c in username.chars() {
            // Перебирает символы в строке `username`.
            if c == '.' {
                continue;
            } else if c == '+' || c == '*' {
                break;
            } else {
                normalized_username.push(c);
                // `normalized_username.push(c)`:  Метод `push` добавляет символ в конец строки.
            }
        }
        return format!("{}@{}", normalized_username, domain); // Возвращает нормализованное имя пользователя, символ "@" и домен.
    }
    String::new() // Invalid email format
    // Если символ "@" не найден, функция возвращает пустую строку (некорректный формат).
}

fn count_unique_emails_rust(emails: &[&str]) -> usize {
    // Объявляет функцию `count_unique_emails_rust`, принимает `emails` и возвращает `usize`.

    let mut unique_emails: HashSet<String> = HashSet::new();
    // Создает изменяемый `HashSet` под названием `unique_emails`, который будет хранить уникальные нормализованные email-адреса.

    for email in emails { // Перебирает каждый email-адрес в слайсе `emails`.
        unique_emails.insert(normalize_email_rust(email));
        // Нормализует текущий email-адрес с помощью функции `normalize_email_rust` и добавляет нормализованную строку в `HashSet unique_emails`.
    }
    unique_emails.len()
    // Возвращает количество элементов в `HashSet unique_emails`, что соответствует количеству уникальных нормализованных email-адресов.
}

fn main() {
    let emails1 = ["mar.pha+science@corp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru"];
    println!("Вывод1 Output: {}", count_unique_emails_rust(&emails1));
    

    let emails2 = ["mar.pha+science@co.rp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru"];
    println!("Вывод2 Output: {}", count_unique_emails_rust(&emails2));
}
