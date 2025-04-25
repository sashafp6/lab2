using System;
using System.Collections.Generic;
using System.Linq;

public class EmailCounter
{
    public static string NormalizeEmail(string email)
    {
        int atPos = email.IndexOf('@'); // Находит индекс первого вхождения символа @ в строке email
        if (atPos == -1)
        {
            return ""; //  Если символ @ не найден, метод возвращает пустую строку, указывая, что формат электронной почты недействителен.
        }

        string username = email.Substring(0, atPos); // возвращает подстроку, начинающуюся с индекса 0 и длиной atPos.
        string domain = email.Substring(atPos + 1); // возвращает подстроку, начинающуюся с индекса atPos + 1 (то есть, после символа @) и продолжающуюся до конца строки.

        string normalizedUsername = "";
        foreach (char c in username) //перебирает каждый символ c в строке username
        {
            if (c == '.')
            {
                continue;
            }
            else if (c == '+' || c == '*')
            {
                break;
            }
            else
            {
                normalizedUsername += c;
            }
        }
        return normalizedUsername + "@" + domain;
    }

    public static int CountUniqueEmails(List<string> emails) //подсчитывает количество уникальных нормализованных адресов электронной почты в заданном списке.
    {
        HashSet<string> uniqueEmails = new HashSet<string>(); // хранит только уникальные строки
        foreach (string email in emails) // перебирает каждый адрес электронной почты email в списке emails.
        {
            uniqueEmails.Add(NormalizeEmail(email));
        }
        return uniqueEmails.Count;
    }

    public static void Main(string[] args)
    {
               List<string> emails1 = new List<string> { "mar.pha+science@corp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru" };
        Console.WriteLine("Example 1 Output: " + CountUniqueEmails(emails1));

        List<string> emails2 = new List<string> { "mar.pha+science@co.rp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru" };
        Console.WriteLine("Example 2 Output: " + CountUniqueEmails(emails2));
    }
}
