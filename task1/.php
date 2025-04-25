<?php

function minWindow(string $s, string $t): string {  // Функция для поиска минимального окна
    if ($t === "") return "";  // Если строка t пуста, возвращаем пустую строку

    $need = array_count_values(str_split($t));  // Используем array_count_values для чтения символов в t
    $window = [];  // массив для хранения символов в окне
    $needCount = count($need);  // Количество уникальных символов в t
    $formed = 0;  // Количество символов, которые мы нашли в окне
    $left = 0;  // Левая граница окна
    $minLen = INF;  // Минимальная длина окна
    $minStr = "";  // Минимальное окно

    for ($right = 0; $right < strlen($s); $right++) {  // Двигаемся по строке S правой границей
        $char = $s[$right];  // Текущий символ
        $window[$char] = ($window[$char] ?? 0) + 1;  // Увеличиваем количество символов в окне

        if (array_key_exists($char, $need) && $window[$char] == $need[$char]) { // Проверка, что символ из t и в правильном количестве
            $formed++; // Увеличиваем счетчик
        }

        while ($formed >= $needCount) { // Пока не достигли правой границы, все необходимые символы есть
            if ($right - $left + 1 < $minLen) {  // Обновляем, если это окно меньше
                $minLen = $right - $left + 1;  
                $minStr = substr($s, $left, $right - $left + 1); 
            }

            $leftChar = $s[$left];  // Символ с левой границы окна
            if(array_key_exists($leftChar,$need)) {  // Проверка, что этот символ из t
                $window[$leftChar]--; // Удаляем символ из окна 
                 if (array_key_exists($leftChar, $need) && $window[$leftChar] < $need[$leftChar]) { // Проверка, что символ из t и кол-во недостаточно
                    $formed--; // Уменьшаем счетчик
                }
            }

            $left++;  
        }
    }
    return $minStr;  // Возвращаем искомое окно
}

$s = "ADOBECODEBANC";  
$t = "ABC";  

echo minWindow($s, $t) . PHP_EOL;  

?>
