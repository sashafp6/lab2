using System;                      
using System.Collections.Generic; 
using System.Linq;


public class Program
{
    static bool IsAscending(int n)   // Проверяет, возрастают ли цифры числа
    {
        string s = n.ToString();  // Число в строку
        for (int i = 1; i < s.Length; i++) // Цикл по цифрам
        {
            if (s[i] <= s[i - 1])     // Если не возрастает
            {
                return false;       // Не возрастающее
            }
        }
        return true;               // Возрастающее
    }

    static int ReverseNumber(int n)  // Переворачивает число
    {
        string s = n.ToString();  // Число в строку
        return int.Parse(new string(s.Reverse().ToArray())); // Переворачиваем строку и обратно в число
    }

    public static void Main(string[] args) // Главная функция
    {
        List<int> numbers = new List<int> { 4, 87, 129, 33, 45 }; // Список чисел
        Console.Write("Result1: "); // Вывод текста
        foreach (int num in numbers)    // Цикл по числам
        {
            if (IsAscending(num))      // Если возрастающее
            {
                Console.Write(ReverseNumber(num) + " "); // Вывод перевернутого числа
            }
        }
        Console.WriteLine();          // Перенос строки
    }
}
