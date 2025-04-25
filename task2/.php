<?php

function normalize_email_php(string $email): string {
    $parts = explode('@', $email); // Разбиваем email на имя пользователя и домен по символу @
    if (count($parts) != 2) {
        return ""; // Если не 2 части (нет @), возвращаем пустую строку (некорректный формат)
    }
    $username = $parts[0]; // имя пользователя
    $domain = $parts[1]; // домен

    $normalized_username = ""; // Инициализируем строку для нормализованного имени пользователя
    for ($i = 0; $i < strlen($username); $i++) { // Перебираем символы в имени пользователя
        $char = $username[$i]; // // Перебираем символы в имени пользователя
        if ($char == '.') {
            continue;
        } else if ($char == '+' || $char == '*') {
            break;
        } else {
            $normalized_username .= $char; // Добавляем символ в нормализованное имя пользователя
        }
    }
    return $normalized_username . "@" . $domain; // Возвращаем нормализованный email
}

function count_unique_emails_php(array $emails): int {
    $unique_emails = []; // Инициализируем пустой массив для нормализованных email-адресов
    foreach ($emails as $email) { // Перебираем email-адреса в массиве
        $unique_emails[] = normalize_email_php($email); // Нормализуем email и добавляем в массив
    }
    return count(array_unique($unique_emails));// использую array для удаления дубликатов
}

$emails1 = ["mar.pha+science@corp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru"];
echo "Вывод 1 Output: " . count_unique_emails_php($emails1) . "\n";

$emails2 = ["mar.pha+science@co.rp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru"];
echo "Вывод 2 Output: " . count_unique_emails_php($emails2) . "\n";

?>
