package main
import (
	"fmt"       
	"strings" 
)

func normalizeEmailGo(email string) string {
	parts := strings.Split(email, "@") 
	if len(parts) != 2 {              // Проверяем, что email имеет @
		return "" 
	}
	username := parts[0]   
	domain := parts[1]     

	normalizedUsername := "" 
	for _, char := range username {  // Перебираем символы имени пользователя
		if char == '.' {        
			continue
		} else if char == '+' || char == '*' { 
			break
		} else {
			normalizedUsername += string(char) // Добавляем символ к нормализованному имени
		}
	}
	return normalizedUsername + "@" + domain // Возвращаем нормализованный email
}

func countUniqueEmailsGo(emails []string) int {
	uniqueEmails := make(map[string]bool) 
	for _, email := range emails {         // Перебираем все email
		uniqueEmails[normalizeEmailGo(email)] = true // Нормализуем и добавляем в map
	}
	return len(uniqueEmails) 
}

func main() {
	emails1 := []string{"mar.pha+science@corp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru"} 
	fmt.Println("Example 1 Output:", countUniqueEmailsGo(emails1))                   

	emails2 := []string{"mar.pha+science@co.rp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru"} 
	fmt.Println("Example 2 Output:", countUniqueEmailsGo(emails2))                   
}
