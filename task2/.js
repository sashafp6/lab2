function normalizeEmailJs(email) {
    const parts = email.split('@'); // Разбиваем email на имя и домен
    if (parts.length !== 2) { // Проверяем валидность (есть @?)
        return ""; // Некорректный формат
    }
    let username = parts[0]; // Имя пользователя
    const domain = parts[1]; // Домен

    let normalizedUsername = ""; // Для нормализованного имени
    for (let char of username) { // Перебираем символы имени
        if (char === '.') { // Пропускаем точки
            continue;
        } else if (char === '+' || char === '*') { // Прерываем после + или *
            break;
        } else {
            normalizedUsername += char; // Добавляем символы
        }
    }
    return normalizedUsername + "@" + domain; // Возвращаем нормализованный email
}

function countUniqueEmailsJs(emails) {
    const uniqueEmails = new Set(); // Set для уникальных email
    for (const email of emails) { // Перебираем все email
        uniqueEmails.add(normalizeEmailJs(email)); // Нормализуем и добавляем
    }
    return uniqueEmails.size; // Возвращаем кол-во уникальных
}

const emails1 = ["mar.pha+science@corp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru"];
console.log("Example 1 Output:", countUniqueEmailsJs(emails1));
const emails2 = ["mar.pha+science@co.rp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru"];
console.log("Example 2 Output:", countUniqueEmailsJs(emails2));
