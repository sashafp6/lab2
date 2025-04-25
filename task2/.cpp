#include <iostream>
#include <string>
#include <vector>
#include <unordered_set>


using namespace std;

string normalize_email(const string& email) { 
    size_t at_pos = email.find('@'); 
    if (at_pos == string::npos) { 
        return ""; 
    }

    string username = email.substr(0, at_pos); 
    string domain = email.substr(at_pos + 1); 

    string normalized_username = ""; 
    for (char c : username) { // Итерируется по каждому символу c в строке username
        if (c == '.') {
            continue;
        } else if (c == '+' || c == '*') {
            break; //  удалит все символы после + или *
        } else {
            normalized_username += c; 
        }
    }
    return normalized_username + "@" + domain; 
}

int count_unique_emails_cpp(const vector<string>& emails) { 
    unordered_set<string> unique_emails; // добавляет нормализованную строку email в набор.
    for (const string& email : emails) { // Итерируется по каждому адресу электронной почты в векторе emails.
        unique_emails.insert(normalize_email(email)); // для нормализации текущего адреса электронной почты и затем добавляет нормализованную строку
    }
    return unique_emails.size();
}

int main() {
    vector<string> emails1 = {"mar.pha+science@corp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru"};
    cout << "1 Output: " << count_unique_emails_cpp(emails1) << endl;

    vector<string> emails2 = {"mar.pha+science@co.rp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru"};
    cout << "2 Output: " << count_unique_emails_cpp(emails2) << endl;

    return 0;
}
