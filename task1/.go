package main

import (
	"fmt"
	"math"
)
func minWindow(s, t string) string {
	if t == "" {  // Если t пустая
		return ""
	}
	need := make(map[rune]int)  
	for _, char := range t {  // Перебираем t
		need[char]++  // Считаем, сколько каждого символа нужно
	}
	formed, l, minLen := 0, 0, math.MaxInt32  
	needCount := len(need)  
	window := make(map[rune]int)  // window: символ -> сколько в текущем окне
	result := ""  

	for r, char := range s {  
		window[char]++  // Добавляем/увеличиваем счетчик в окне
		if _, ok := need[char]; ok && window[char] == need[char] {  // Если символ нужен и его уже достаточно
			formed++  // Увеличиваем счетчик formed
		}
		for formed == needCount {  // Пока все нужные символы есть
			if r-l+1 < minLen {  // Если текущее окно меньше минимального на данный момент
				minLen = r - l + 1  // Запоминаем новую минимальную длину
				result = s[l : r+1]  
			}
			leftChar := rune(s[l])  
			if _, ok := need[leftChar]; ok {  // Если символ, который сейчас выкинем из окна, нужен
				window[leftChar]--  // Убираем его из окна
				if window[leftChar] < need[leftChar] {  // И если его стало не хватать
					formed--  // То уменьшаем счетчик formed
				}
			}
			l++  // Сдвигаем окно слева
		}
	}
	if minLen == math.MaxInt32 {  // Если так и не нашли хорошего окна
		return "" // То возвращаем пустую строку
	}
	return result  // Иначе - возвращаем результат
}

func main() {  
	s := "ADOBECODEBANC" 
	t := "ABC" 
	fmt.Println(minWindow(s, t))

}
