function normalizeEmailTs(email: string): string {
    const parts = email.split('@'); // Разбиваем email на имя и домен
    if (parts.length !== 2) { // Проверяем валидность формата (наличие @)
        return ""; // Invalid email format
    }
    let username = parts[0]; // Имя пользователя
    const domain = parts[1]; // Домен

    let normalizedUsername = ""; // Строка для нормализованного имени
    for (let char of username) { // Перебираем символы имени пользователя
        if (char === '.') { 
            continue;
        } else if (char === '+' || char === '*') { 
            break;
        } else {
            normalizedUsername += char; // Добавляем остальные символы
        }
    }
    return normalizedUsername + "@" + domain; // Возвращаем нормализованный email
}

function countUniqueEmailsTs(emails: string[]): number {
    const uniqueEmails = new Set<string>(); // Множество для хранения уникальных email
    for (const email of emails) { // Перебираем email
        uniqueEmails.add(normalizeEmailTs(email)); // Нормализуем и добавляем в множество
    }
    return uniqueEmails.size; // Возвращаем кол-во уникальных
}

const emails1: string[] = ["mar.pha+science@corp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru"]; 
console.log("Example 1 Output:", countUniqueEmailsTs(emails1)); 

const emails2: string[] = ["mar.pha+science@co.rp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru"]; 
console.log("Example 2 Output:", countUniqueEmailsTs(emails2)); 
