def normalize_email_py(email):
    parts = email.split('@')
    if len(parts) != 2:
        return ""  # Invalid email format
    username = parts[0]
    domain = parts[1]

    normalized_username = ""
    for char in username:
        if char == '.':
            continue
        elif char == '+' or char == '*':
            break
        else:
            normalized_username += char
    return normalized_username + "@" + domain

def count_unique_emails_py(emails):
    unique_emails = set()
    for email in emails:
        unique_emails.add(normalize_email_py(email))
    return len(unique_emails)

emails1 = ["mar.pha+science@corp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru"]
print("Example 1 Output:", count_unique_emails_py(emails1))

emails2 = ["mar.pha+science@co.rp.nstu.ru", "marpha+scie.nce@corp.nstu.ru", "marph.a+s.c.i.e.n.c.e+@corp.nstu.ru"]
print("Example 2 Output:", count_unique_emails_py(emails2))

