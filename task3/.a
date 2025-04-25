
section .data
   
    SYS_WRITE equ 4         
    SYS_EXIT  equ 1         
    STDOUT_FD equ 1         

    numbers:      dd  87, 129, 33, 45   ; Массив 32-битных чисел
    num_count:    dd 5                  ; Количество элементов 
    result_str:   db "Результат: ", 0
    result_str_len: equ $ - result_str - 1
    space_str:    db " ", 0
    space_str_len:  equ $ - space_str - 1
    newline_str:  db 10, 0
    newline_str_len:equ $ - newline_str - 1

section .bss
    
    num_buffer:     resb 16

section .text
    global _start ; Точка входа

_print:
    pusha               ; Сохраняем все основные регистры

    mov eax, SYS_WRITE  ; syscall number (write)
    mov ebx, STDOUT_FD  ; fd (stdout)
    ; ecx = buf addr (уже в ecx)
    ; edx = count (уже в edx)
    int 0x80            ; Вызов ядра

    popa                ; Восстанавливаем регистры
    ret


_itoa:
    push ebx            ; Сохраняем ebx, esi
    push esi
    push edi            ; Сохраняем edi (используется для адреса конца)

    lea edi, [num_buffer + 15] ; edi = Указатель на конец буфера (индекс 15)
    mov byte [edi], 0   ; Нуль-терминатор строки
    mov esi, edi        ; esi = текущий указатель (начинаем с конца)

    mov ebx, 10         ; Делитель = 10
    xor ecx, ecx        ; Счетчик длины строки (временно в ecx)

    ; Обработка случая нуля отдельно
    test eax, eax       ; Проверяем eax на ноль
    jnz .itoa_loop_start
    dec esi
    mov byte [esi], '0' ; Записываем '0'
    inc ecx             ; Длина = 1
    jmp .itoa_done      ; Переходим к завершению

.itoa_loop_start:
    cmp eax, 0          ; Проверяем перед циклом, чтобы не выполнить лишнюю итерацию для 0
    je .itoa_prepare_ret

.itoa_loop:
    xor edx, edx        ; Очищаем edx перед делением (div делит EDX:EAX)
    div ebx             ; Делим EDX:EAX на ebx (10). Частное в EAX, остаток в EDX

    
    add dl, '0'         ; Добавляем ASCII код '0' (работаем с dl)
    dec esi             ; Двигаем указатель буфера назад
    mov byte [esi], dl  ; Сохраняем символ в буфере

    inc ecx             ; Увеличиваем длину строки

    test eax, eax       ; Если частное (eax) не ноль, продолжаем
    jnz .itoa_loop

.itoa_prepare_ret:
    

.itoa_done:
    mov edx, ecx        ; Возвращаем длину строки в edx
    mov ecx, esi        ; Возвращаем адрес начала строки в ecx

    pop edi             ; Восстанавливаем edi, esi, ebx
    pop esi
    pop ebx
    ret


_reverseString:
    pusha

    cmp edx, 1          ; Если длина 0 или 1, ничего не делаем
    jle .reverse_done

    mov esi, ecx        ; esi = указатель на начало (start_ptr)
    lea edi, [ecx + edx - 1] ; edi = указатель на конец (end_ptr)

.reverse_loop:
    cmp esi, edi
    jge .reverse_done   ; Если start_ptr >= end_ptr, закончили

    mov al, byte [esi]  ; Загружаем байт с начала
    mov bl, byte [edi]  ; Загружаем байт с конца
    mov byte [esi], bl  ; Сохраняем конечный байт в начале
    mov byte [edi], al  ; Сохраняем начальный байт в конце

    inc esi             ; Двигаем начальный указатель вперед
    dec edi             ; Двигаем конечный указатель назад
    jmp .reverse_loop

.reverse_done:
    popa
    ret

_atoi:
    push ebx
    push ecx
    push edx
    push esi

    mov esi, ecx        ; esi = указатель на текущий символ
    lea ecx, [esi + edx]; ecx = указатель на символ после строки
    xor eax, eax        ; eax = результат (начинаем с 0)
    mov ebx, 10         ; ebx = множитель

.atoi_loop:
    cmp esi, ecx
    jge .atoi_done      ; Если дошли до конца строки, выходим

    movzx edx, byte [esi] ; Загружаем текущий символ в dl (edx обнуляется)
    inc esi             ; Переходим к следующему символу

    ; Проверяем, является ли символ цифрой
    cmp dl, '0'
    jl .atoi_skip_char  ; Пропускаем не-цифры в начале 
    cmp dl, '9'
    jg .atoi_done       ; Если встретили не-цифру после цифр - конец числа

    ; Это цифра
    sub dl, '0'         ; Преобразуем символ в числовое значение

   
    imul eax, ebx       ; Умножаем текущий результат (eax) на 10 (ebx)
                        ; eax = eax * ebx

    add eax, edx        ; Добавляем новую цифру (edx содержит 0 в старших байтах)
    

.atoi_skip_char:        ; Метка для пропуска не-цифровых символов в начале
    jmp .atoi_loop

.atoi_done:
    pop esi
    pop edx
    pop ecx
    pop ebx
    ret

_isAscending:
    push ebx
    push ecx
    push edx
    push esi
    push edi

    ; 1. Преобразуем число в строку
    call _itoa          ; eax=число -> ecx=адрес строки, edx=длина

    mov esi, ecx        ; esi = адрес начала строки
    mov edi, edx        ; edi = длина строки

    cmp edi, 1
    jle .isAsc_true     ; Если длина <= 1, true

    ; Начинаем проверку со второго символа
    inc esi             ; esi указывает на второй символ
    dec edi             ; Уменьшаем счетчик оставшихся символов для проверки (теперь это число сравнений)

    mov eax, 1          ; Предполагаем, что результат true (1)

.isAsc_loop:
    ; edi содержит количество оставшихся сравнений
    test edi, edi
    jz .isAsc_done      ; Если 0, все сравнения прошли успешно

    mov cl, byte [esi]      ; Текущая цифра (символ)
    mov bl, byte [esi - 1]  ; Предыдущая цифра (символ)

    cmp cl, bl          ; Сравниваем текущую с предыдущей
    jle .isAsc_false    ; Если текущая <= предыдущей, условие нарушено

    inc esi             ; Переходим к следующему символу
    dec edi             ; Уменьшаем счетчик сравнений
    jmp .isAsc_loop

.isAsc_false:
    mov eax, 0          ; Результат false (0)
    jmp .isAsc_done

.isAsc_true:
    mov eax, 1          ; Результат true (1)

.isAsc_done:
    pop edi
    pop esi
    pop edx
    pop ecx
    pop ebx
    ret

; Функция: _reverseNumber
; Разворачивает цифры числа (123 -> 321)
; Аргументы: eax = число
; Возвращает: eax = развернутое число
_reverseNumber:
    push ebx
    push ecx
    push edx

    ; 1. Преобразуем число в строку
    call _itoa          ; eax=число -> ecx=адрес строки, edx=длина

    ; 2. Разворачиваем строку (аргументы уже в ecx, edx)
    call _reverseString

    ; 3. Преобразуем развернутую строку обратно в число (аргументы в ecx, edx)
    call _atoi          ; -> результат в eax

    pop edx
    pop ecx
    pop ebx
    ret

; --- Основная программа ---
_start:
    ; Сохраняем регистры, которые будем использовать и которые должны сохраняться
    push esi
    push edi
    push ebx

    ; Печатаем префикс "Результат: "
    mov ecx, result_str
    mov edx, result_str_len
    call _print

    ; Инициализация цикла
    mov esi, numbers    ; esi = адрес начала массива numbers
    mov edi, [num_count]; edi = количество чисел

.main_loop:
    test edi, edi       ; Проверяем счетчик
    jz .main_loop_end   ; Если 0, выходим из цикла

    mov eax, [esi]      ; eax = текущее число
    mov ebx, eax        ; Сохраняем текущее число в ebx

    call _isAscending   ; Проверяем число в eax. Результат в eax (1 или 0)

    test eax, eax       ; Проверяем результат _isAscending
    jz .main_skip_print ; Если 0 (не возрастающее), пропускаем печать

    ; --- Число с возрастающими цифрами ---
    mov eax, ebx        ; Восстанавливаем число для разворота
    call _reverseNumber ; Разворачиваем число в eax. Результат в eax.

    ; Преобразуем развернутое число в строку
    call _itoa          ; eax=развернутое число -> ecx=адрес, edx=длина

    ; Печатаем развернутое число
    call _print         ; Аргументы уже в ecx, edx

    ; Печатаем пробел
    mov ecx, space_str
    mov edx, space_str_len
    call _print

.main_skip_print:
    add esi, 4          ; Передвигаем указатель на следующее dd (4 байта)
    dec edi             ; Уменьшаем счетчик
    jmp .main_loop

.main_loop_end:
    ; Печатаем символ новой строки
    mov ecx, newline_str
    mov edx, newline_str_len
    call _print

    ; Завершение программы
    mov eax, SYS_EXIT   ; Номер системного вызова exit (1)
    xor ebx, ebx        ; Код возврата 0
    int 0x80

    ; Восстанавливаем регистры (хотя после exit это не нужно)
    pop ebx
    pop edi
    pop esi
    ; ret ; Не нужно после exit
