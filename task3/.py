def reverse_ascending(nums):
  res = [] # Массив для хранения результатов
  for n in nums: 
    s = str(n) # Преобразуем число в строку
    if s == "".join(sorted(s)): # Проверяем, возрастают ли цифры 
      res.append(int(s[::-1]))  # Переворачиваем, преобразуем в Int и добавляем в результат
  return res 

nums = [4, 87, 129, 33, 45] 
print(reverse_ascending(nums))  
