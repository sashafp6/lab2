function isAscending(n) { // Проверяет, возрастают ли цифры числа
    const s = String(n); // Преобразует число в строку
    for (let i = 1; i < s.length; i++) { // Перебирает цифры строки
        if (s[i] <= s[i - 1]) { // Если текущая цифра не больше предыдущей
            return false; // Не возрастающее
        }
    }
    return true; // Возрастающее
}

function reverseNumber(n) { // Переворачивает число
    return parseInt(String(n).split("").reverse().join(""), 10); // Преобразует в строку, переворачивает, преобразует обратно в число
}

const numbers = [4, 87, 129, 33, 45]; // Массив чисел
console.log("Результат: "); // Выводит "Результат: "
let output = ""; // Строка для хранения результата
for (const num of numbers) { // Перебирает массив чисел
    if (isAscending(num)) { // Если число возрастающее
        output += reverseNumber(num) + " "; // Добавляет перевернутое число и пробел к строке
    }
}
console.log(output.trim()); // Выводит строку с результатом, удаляя лишние пробелы в конце
