3 задание
function isAscending(n: number): boolean {
    const s = n.toString(); // Число в строку
    for (let i = 1; i < s.length; i++) { // Цикл по цифрам
        if (s[i] <= s[i - 1]) { // Если не возрастает
            return false; // Не возрастающее
        }
    }
    return true; // Возрастающее
}
function reverseNumber(n: number): number {
    return parseInt(n.toString().split("").reverse().join(""), 10); // Строка, переворот, в число
}
const numbers: number[] = [4, 87, 129, 33, 45]; // Массив чисел
console.log("Result5: "); // Вывод текста
let output = ""; // Строка для результата
for (const num of numbers) { // Цикл по числам
    if (isAscending(num)) { // Если возрастающее
        output += reverseNumber(num) + " "; // Добавляем перевернутое
    }
}
console.log(output.trim()); // Вывод результата (с удалением пробелов в конце)
