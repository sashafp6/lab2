import java.util.*;

class Solution {
    public String minWindow(String s, String t) {
        if (t.isEmpty()) return "";

        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        // Заполняем карту need: ключ - символ из t, значение - частота
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int needCount = need.size();  // Количество уникальных символов в t
        int formed = 0;  // Количество символов, для которых выполнено need.get(c) == window.get(c)
        int left = 0, right = 0; // Указатели на начало и конец "окна"
        int minLen = Integer.MAX_VALUE; // Минимальная длина найденной подстроки
        String minStr = ""; // Наименьшая подстрока

        while (right < s.length()) {
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);

            // Если символ c из s есть в t и его частота в окне равна требуемой
            if (need.containsKey(c) && window.get(c).equals(need.get(c))) {
                formed++;
            }

            // Пока все необходимые символы найдены
            while (formed == needCount) {
                // Обновляем минимальную длину и подстроку
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStr = s.substring(left, right + 1);
                }

                char leftChar = s.charAt(left);
                int leftCharCount = window.get(leftChar); // Сохраняем значение для избежания повторных вызовов getOrDefault
                window.put(leftChar, leftCharCount - 1);

                // Если символ leftChar есть в t и его частота в окне стала меньше требуемой
                if (need.containsKey(leftChar) && leftCharCount <= need.get(leftChar)) {
                    formed--;
                }
                left++; // Сдвигаем левую границу окна
            }
            right++; // Сдвигаем правую границу окна
        }
        return minStr;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        Solution sol = new Solution();
        String result = sol.minWindow(s, t);
        System.out.println(result); 
    }
}
