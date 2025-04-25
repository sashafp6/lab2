def min_window(s, t):
    if not s or not t:
        return ""
    t_counts = {}
    for char in t:
        t_counts[char] = t_counts.get(char, 0) + 1

    required = len(t_counts)  # Количество уникальных символов в t
    formed = 0 # Количество уникальных символов, удовлетворяющих условию кратности

    window_counts = {}
    left = 0
    right = 0
    min_length = float('inf')
    min_start = 0

    while right < len(s):
        char = s[right]
        window_counts[char] = window_counts.get(char, 0) + 1

        if char in t_counts and window_counts[char] == t_counts[char]:
            formed += 1

        while formed == required:
            if right - left + 1 < min_length:
                min_length = right - left + 1
                min_start = left

            char = s[left]
            window_counts[char] -= 1

            if char in t_counts and window_counts[char] < t_counts[char]:
                formed -= 1

            left += 1

        right += 1

    if min_length == float('inf'):
        return ""
    else:
        return s[min_start : min_start + min_length]

s = "ADOBECODEBANC"
t = "ABC"
result = min_window(s, t)
print(result) 
