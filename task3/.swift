func reverseAscending(numbers: [Int]) -> [Int] {
    var result: [Int] = []
    for num in numbers {
        let strNum = String(num)
        if strNum == String(strNum.sorted()) { // Проверяем, возрастают ли цифры 
            result.append(Int(String(strNum.reversed())) ?? 0)
        }
    }
    return result
}
let numbers = [4, 87, 129, 33, 45]
print(reverseAscending(numbers: numbers))
