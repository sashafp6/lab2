#include <iostream>
#include <string>
#include <climits>
#include <unordered_map>

using namespace std;

string minWindow(string s, string t) { // Функция для поиска наименьшей подстроки
    if (t.empty()) return "";

    unordered_map<char, int> need, window;

    for (char c : t) { // Проходим по каждому символу 'c' в строке t
        need[c]++;
    }

    int needCount = need.size();
    int formed = 0; // нужное количество символов
    int left = 0, right = 0;
    int minLen = INT_MAX;
    string minStr = "";

    while (right < s.length()) { // Двигаем правую границу по строке s
        char c = s[right];
        window[c]++;

        if (need.count(c) && window[c] == need[c]) { // Если символ нужен и мы нашли его нужное количество
            formed++;
        }

        while (formed == needCount) {
            int currentWindowLen = right - left + 1;
            if (currentWindowLen < minLen) {
                minLen = currentWindowLen;
                minStr = s.substr(left, currentWindowLen); // s.substr извлекает подстроку из s, начиная с индекса left, длиной length (которая равна right - left + 1).
            }

            char leftChar = s[left]; // Символ, на который указывает левая граница
            window[leftChar]--; // Уменьшаем количество символа в окне

            if (need.count(leftChar) && window[leftChar] < need[leftChar]) { // является ли этот символ одним из тех, что нам нужны, и если после уменьшения его количества в окне оно стало меньше, чем требуемое количество
                formed--; // Уменьшаем счетчик найденных
            }

            left++;
        }

        right++;
    }

    return minStr;
}

int main() {
    string s = "ADOBECODEBANC";
    string t = "ABC";
    string result = minWindow(s, t);
    cout << result << endl;
    return 0;
}}
