import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmailCounterJava {

    public static String normalizeEmailJava(String email) {
        String[] parts = email.split("@");
        if (parts.length != 2) {
            return ""; // Invalid email format
        }
        String username = parts[0];
        String domain = parts[1];

        StringBuilder normalizedUsername = new StringBuilder(); // Строим нормализованное имя
        for (char c : username.toCharArray()) { // Перебираем символы имени
            if (c == '.') {
                continue;
            } else if (c == '+' || c == '*') {
                break;
            } else {
                normalizedUsername.append(c);
            }
        }
        return normalizedUsername.toString() + "@" + domain;
    }

    public static int countUniqueEmailsJava(List<String> emails) { // Функция подсчета уникальных email
        Set<String> uniqueEmails = new HashSet<>(); // Множество для уникальных email
        for (String email : emails) { // Перебираем список email
            uniqueEmails.add(normalizeEmailJava(email)); // Нормализуем и добавляем в множество
        }
        return uniqueEmails.size();
    }

    public static void main(String[] args) {
        List<String> emails1 = List.of("mar.pha+science@corp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru");
        System.out.println("Example 1 Output: " + countUniqueEmailsJava(emails1));

        List<String> emails2 = List.of("mar.pha+science@co.rp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru");
        System.out.println("Example 2 Output: " + countUniqueEmailsJava(emails2));
    }
}
