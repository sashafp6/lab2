func minWindow(s: String, t: String) -> String {
    guard !s.isEmpty && !t.isEmpty && s.count >= t.count else { return "" }

    var tCharCounts: [Character: Int] = [:] // Счетчик символов для строки T
    var windowCharCounts: [Character: Int] = [:] // Счетчик символов для текущего окна

    for char in t {
        tCharCounts[char, default: 0] += 1
    }

    var left = 0
    var right = 0
    var minLen = Int.max
    var minStart = 0
    var matched = 0 // Количество символов из T, найденных в текущем окне

    let sArray = Array(s) // Преобразуем строку S в массив символов для удобства доступа

    while right < sArray.count {
        let char = sArray[right]

        if tCharCounts[char] != nil {
            windowCharCounts[char, default: 0] += 1
            if windowCharCounts[char] == tCharCounts[char] {
                matched += 1 // Увеличиваем счетчик, если количество символов совпало
            }
        }

        while matched == tCharCounts.count { // Когда все символы T найдены в окне
            if right - left + 1 < minLen {
                minLen = right - left + 1
                minStart = left
            }

            let leftChar = sArray[left]
            if tCharCounts[leftChar] != nil {
                windowCharCounts[leftChar, default: 0] -= 1
                if windowCharCounts[leftChar, default: 0] < tCharCounts[leftChar, default: 0] {
                    matched -= 1 // Уменьшаем счетчик, так как символ больше не удовлетворяет условию
                }
            }
            left += 1
        }
        right += 1
    }

    return minLen == Int.max ? "" : String(sArray[minStart..<minStart+minLen])
}

let s = "ADOBECODEBANC"
let t = "ABC"
let result = minWindow(s: s, t: t)
print(result) 
