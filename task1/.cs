using System;
using System.Collections.Generic;
using System.Linq;  

public class Solution {
    public string MinWindow(string s, string t) {  // Функция поиска минимального окна
        if (string.IsNullOrEmpty(t)) return "";  // Если `t` пуста, возвращаем пустую строку

        Dictionary<char, int> need = t.GroupBy(c => c).ToDictionary(g => g.Key, g => g.Count()); // Создаем словарь `need` из строки `t`
        Dictionary<char, int> window = new Dictionary<char, int>();  // Словарь для подсчета символов в окне

        int needCount = need.Count;  // Кол-во уникальных символов в `t`
        int formed = 0;  // Кол-во найденных символов
        int left = 0,  // Левая граница окна
            minLen = int.MaxValue, // Минимальная длина окна
            minStart = 0; // Начало минимального окна

        for (int right = 0; right < s.Length; right++) {  // Двигаем правую границу
            char c = s[right];  // Текущий символ
            window[c] = window.ContainsKey(c) ? window[c] + 1 : 1;  // Увеличиваем кол-во символа в окне

            if (need.ContainsKey(c) && window[c] == need[c]) formed++; // Если символ нужен и его кол-во соответствует, увеличиваем `formed`

            while (formed == needCount) {  // Пока все нужные символы есть
                if (right - left + 1 < minLen) {  // Если текущая длина окна меньше минимальной
                    minLen = right - left + 1;  // Обновляем минимальную длину
                    minStart = left;  
                }
                char leftChar = s[left];  // Символ на левой границе
                if (need.ContainsKey(leftChar)) {  // Если символ есть в `need`
                    window[leftChar]--; // Уменьшаем кол-во
                    if (window[leftChar] < need[leftChar]) formed--;  // Если кол-ва стало не хватать, уменьшаем `formed`
                }
                left++;  // Сдвигаем левую границу
            }
        }
        return minLen == int.MaxValue ? "" : s.Substring(minStart, minLen);  // Возвращаем минимальное окно или пустую строку, если не найдено
    }

    public static void Main(string[] args) {  
        string s = "ADOBECODEBANC";  
        string t = "ABC";  
        Solution sol = new Solution();  // Создаем объект класса
        Console.WriteLine(sol.MinWindow(s, t));  
    }
}
