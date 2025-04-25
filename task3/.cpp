#include <iostream>
using namespace std;

bool isAscending(int num) {
if (num < 10) return false;
int prev = num % 10;
num /= 10;
while (num > 0) {
int curr = num % 10;
if (curr >= prev) return false;
prev = curr;
num /= 10;
}
return true;
}

int reverseNumber(int num) {
int reversed = 0;
while (num > 0) {
reversed = reversed * 10 + num % 10;
num /= 10;
}
return reversed;
}

int main() {
int n, num;
cin >> n;

for (int i = 0; i < n; i++) {
cin >> num;
if (isAscending(num)) {
cout << reverseNumber(num) << " ";
}
}

return 0;
}
