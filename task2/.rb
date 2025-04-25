require 'set' 

def normalize_email_ruby(email) # Функция нормализации email
  parts = email.split('@') 
  return "" if parts.length != 2 
  username = parts[0] 
  domain = parts[1] 

  normalized_username = "" 
  username.each_char do |char| # Перебираем имя
    if char == '.' 
      next
    elsif char == '+' || char == '*' 
      break
    else
      normalized_username += char 
    end
  end
  return normalized_username + "@" + domain 
end

def count_unique_emails_ruby(emails) # Функция подсчета уникальных
  unique_emails = Set.new # Множество для уникальных email
  emails.each { |email| unique_emails.add(normalize_email_ruby(email)) } # Добавляем в множество нормализованные email
  return unique_emails.length 
end

emails1 = ["..."] 
puts "Example 1 Output: #{count_unique_emails_ruby(emails1)}" 

emails2 = ["..."] 
puts "Example 2 Output: #{count_unique_emails_ruby(emails2)}"
