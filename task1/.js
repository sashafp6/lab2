function minWindow(s, t) {
  if (!s || !t || s.length < t.length) {
    return "";
  }
  const tCharCounts = new Map(); // Счетчик символов для строки T
  const windowCharCounts = new Map(); // Счетчик символов для текущего окна
  for (const char of t) {
    tCharCounts.set(char, (tCharCounts.get(char) || 0) + 1);
  }
  let left = 0;
  let right = 0;
  let minLen = Infinity;
  let minStart = 0;
  let matched = 0; // Количество символов из T, найденных в текущем окне
  while (right < s.length) {
    const char = s[right];
    if (tCharCounts.has(char)) {
      windowCharCounts.set(char, (windowCharCounts.get(char) || 0) + 1);
      if (windowCharCounts.get(char) === tCharCounts.get(char)) {
        matched++; // Увеличиваем счетчик, если количество символов совпало
      }
    }
    while (matched === tCharCounts.size) { // Когда все символы T найдены в окне
      if (right - left + 1 < minLen) {
        minLen = right - left + 1;
        minStart = left;
      }
      const leftChar = s[left];
      if (tCharCounts.has(leftChar)) {
        windowCharCounts.set(leftChar, windowCharCounts.get(leftChar) - 1);
        if (windowCharCounts.get(leftChar) < tCharCounts.get(leftChar)) {
          matched--; // Уменьшаем счетчик, так как символ больше не удовлетворяет условию
        }
      }
      left++;
    }
    right++;
  }
  return minLen === Infinity ? "" : s.substring(minStart, minStart + minLen);
}
const s = "ADOBECODEBANC";
const t = "ABC";
const result = minWindow(s, t);
console.log(result);
