use std::collections::HashMap; 

fn min_window(s: String, t: String) -> String { // Функция для поиска минимального окна
    if t.is_empty() { return String::new(); } // Если t пустая, возвращаем пустую строку

    let need: HashMap<char, i32> = t.chars().fold(HashMap::new(), |mut m, c| { *m.entry(c).or_insert(0) += 1; m });
    // Создаем HashMap need, где храним количество каждого символа из строки t
    // .fold - итератор, применяет функцию к каждому элементу и накапливает результат
    // HashMap::new() - начальное значение аккумулятора
    // |mut m, c| { ... } - функция, применяемая к каждому символу c
    // *m.entry(c).or_insert(0) += 1; - увеличиваем счетчик для символа c в HashMap m

    let mut window: HashMap<char, i32> = HashMap::new(); // HashMap для хранения количества каждого символа в текущем окне
    let mut l = 0; // Левая граница окна
    let mut formed = 0; // Кол-во уникальных символов в окне, которые есть в need в достаточном количестве
    let need_count = need.len(); // Кол-во уникальных символов в t
    let mut min_len = std::usize::MAX;  // Минимальная длина найденного окна
    let mut min_str = String::new(); // Строка для хранения минимального окна
    let s_chars: Vec<char> = s.chars().collect(); // Преобразуем строку s в вектор символов

    for r in 0..s_chars.len() { // Двигаем правую границу окна по строке s
        let c = s_chars[r]; // Текущий символ справа
        *window.entry(c).or_insert(0) += 1; // Увеличиваем счетчик символа c в HashMap window

        if need.contains_key(&c) && window[&c] == need[&c] { formed += 1; }
        // Если c есть в need и кол-во c в окне window соответствует need, то увеличиваем счетчик сформированных formed

        while formed == need_count { // Пока все символы из need есть в окне window в достаточном количестве
            if r - l + 1 < min_len { // Если текущая длина окна меньше минимальной найденной длины
                min_len = r - l + 1; // Обновляем минимальную длину
                min_str = s_chars[l..=r].iter().collect(); // Обновляем минимальное окно
            }

            if need.contains_key(&s_chars[l]) {  // Если символ слева из need
                *window.entry(s_chars[l]).or_insert(0) -= 1; // Уменьшаем количество s_chars[l] в окне
                if window[&s_chars[l]] < need[&s_chars[l]] { formed -= 1; } //Если символа стало не хватать, уменьшаем счетчик
            }
            l += 1; // Сдвигаем левую границу окна вправо
        }
    }
    if min_len == std::usize::MAX { "".to_string() } else { min_str } //Если min_len осталась std::usize::MAX, то не нашли окна, возвращаем пустую строку, иначе - возвращаем минимальное окно.
}

fn main() {
    let s = "ADOBECODEBANC".to_string();
    let t = "ABC".to_string();
    println!("{}", min_window(s, t)); 
}
