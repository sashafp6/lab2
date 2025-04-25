fn is_ascending(n: i32) -> bool { // Проверяет возрастание цифр числа
    let s = n.to_string(); // Число в строку
    for i in 1..s.len() { // Итерируемся по цифрам (кроме первой)
        if s.chars().nth(i).unwrap() <= s.chars().nth(i - 1).unwrap() { // Если цифра меньше или равна предыдущей
            return false; // Не возрастает
        }
    }
    return true; // Возрастает
}

fn reverse_number(n: i32) -> i32 { // Переворачивает число
    n.to_string().chars().rev().collect::<String>().parse::<i32>().unwrap() // Преобразуем в строку, переворачиваем, и парсим обратно в число
}

fn main() {
    let numbers = vec![4, 87, 129, 33, 45]; // Вектор чисел
    print!("Result3: "); // Вывод текста
    for num in numbers { // Итерируемся по числам
        if is_ascending(num) { // Если число возрастает
            print!("{} ", reverse_number(num)); // Выводим перевернутое число
        }
    }
    println!(); 
}
