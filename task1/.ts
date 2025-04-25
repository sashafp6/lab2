function minWindow(s: string, t: string): string {
  if (!s || !t || s.length < t.length) {
    return "";
  }

  const tCharCounts: Map<string, number> = new Map();
  for (const char of t) {
    tCharCounts.set(char, (tCharCounts.get(char) || 0) + 1);
  }

  const windowCharCounts: Map<string, number> = new Map();

  let left = 0;
  let right = 0;

  let minLen = Infinity;
  let minStart = 0;

  let matched = 0;

  while (right < s.length) {
    const char = s[right];

    if (tCharCounts.has(char)) {
      windowCharCounts.set(char, (windowCharCounts.get(char) || 0) + 1);

      if (windowCharCounts.get(char)! === tCharCounts.get(char)!) { // non-null assertion operator (!)
        matched++;
      }
    }

    while (matched === tCharCounts.size) {
      if (right - left + 1 < minLen) {
        minLen = right - left + 1;
        minStart = left;
      }

      const leftChar = s[left];

      if (tCharCounts.has(leftChar)) {
        windowCharCounts.set(leftChar, (windowCharCounts.get(leftChar) || 0) - 1);

        if (windowCharCounts.get(leftChar)! < tCharCounts.get(leftChar)!) {  // non-null assertion operator (!)
          matched--;
        }
      }

      left++;
    }

    right++;
  }

  if (minLen === Infinity) {
    return "";
  }

  return s.substring(minStart, minStart + minLen);
}

const s = "ADOBECODEBANC";
const t = "ABC";
const result = minWindow(s, t);
console.log(result);
