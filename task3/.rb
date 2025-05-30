def is_ascending(n) # Проверяет, возрастают ли цифры числа
  s = n.to_s # Преобразует число в строку
  (1...s.length).each do |i| # Итерируется по цифрам (начиная со второй)
    return false if s[i].to_i <= s[i-1].to_i # Если текущая цифра меньше или равна предыдущей, возвращает false
  end
  true # Если все цифры возрастают, возвращает true
end

def reverse_number(n) # Переворачивает число
  n.to_s.reverse.to_i # Преобразует в строку, переворачивает, преобразует обратно в число
end

numbers = [4, 87, 129, 33, 45] # Массив чисел
print "Результат: " # Выводит "Результат: "
numbers.each do |num| # Итерируется по массиву чисел
  if is_ascending(num) # Если число возрастающее
    print "#{reverse_number(num)} " # Выводит перевернутое число с пробелом
  end
end
puts # Выводит перенос строки
