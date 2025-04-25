def min_window_substring(s, t)
  return "" if t.empty?

  need = Hash.new(0)
  window = Hash.new(0)

  t.each_char { |char| need[char] += 1 }
  need_count = need.length
  formed = 0

  left = 0
  right = 0
  min_len = Float::INFINITY
  min_str = ""

  while right < s.length
    char = s[right]
    window[char] += 1
    if need.key?(char) && window[char] == need[char]
      formed += 1
    end

    while formed == need_count
      if right - left + 1 < min_len
        min_len = right - left + 1
        min_str = s[left..right]
      end

      left_char = s[left]
      window[left_char] -= 1
      if need.key?(left_char) && window[left_char] < need[left_char]
        formed -= 1
      end
      left += 1
    end
    right += 1
  end
  min_str
end

s = "ADOBECODEBANC"
t = "ABC"
result = min_window_substring(s, t)
puts result
