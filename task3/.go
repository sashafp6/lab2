package main

import (
	"fmt"       
	"strconv" 
)
func isAscending(n int) bool { 
	s := strconv.Itoa(n) // Преобразуем число в строку
	for i := 1; i < len(s); i++ { // Цикл по строке, начиная со второго символа
		if s[i] <= s[i-1] { 
			return false // Число не возрастающее
		}
	}
	return true // Число возрастающее
}
func reverseNumber(n int) int { 
	s := strconv.Itoa(n) // Преобразуем число в строку
	runes := []rune(s) // Преобразуем строку 
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 { // Переворачиваем 
		runes[i], runes[j] = runes[j], runes[i] // Меняем местами символы
	}
	reversedS := string(runes) // Преобразуем обратно в строку
	reversedN, err := strconv.Atoi(reversedS) // Преобразуем строку обратно в число
	if err != nil { // Если произошла ошибка при преобразовании
		return 0 
	}
	return reversedN 
}
func main() { 
	numbers := []int{4, 87, 129, 33, 45} 
	fmt.Print("Result4: ") 
	for _, num := range numbers { 
		if isAscending(num) { // Если число возрастающее
			fmt.Printf("%d ", reverseNumber(num)) 
		}
	}
	fmt.Println() 
}
