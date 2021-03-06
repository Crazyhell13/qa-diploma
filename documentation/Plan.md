## План автоматизации тестирования покупки тура.

### **I. Перечень автоматизируемых сценариев.**
1) Оплата по дебетовой карте.
2) Выдача кредита по данным банковской карты.

**Карты, используемые в тестировании** (предоставлены в файле data.json):
4444 4444 4444 4441, status APPROVED
4444 4444 4444 4442, status DECLINED

### **1) Оплата по дебетовой карте.**
#### **Предусловие:**
1. Открыть в браузере страницу http://localhost:8080/.
2. Кликнуть по кнопке "Купить".

**Позитивные сценарии.** 🌞

👩‍💻 1.1 Оплата по дебетовой карте со статусом **APPROVED**: ✅

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет);
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV)
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - появилось всплывающее окно "Операция одобрена Банком"
    - в БД появилась запись со статусом APPROVED.

👩‍💻 1.2 Оплата по дебетовой карте со статусом **DECLINED**: ❌

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4442.
   2.Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет);
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV);
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - появилось всплывающее окно "Ошибка! Банк отказал в проведении операции"
    - в БД появилась запись со статусом DECLINED.

**Негативные сценарии.** 👺

👩‍💻 1.3 Все поля пустые.

Шаги:
1. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Владелец" появляется предупреждение "Поле обязательно для заполнения";
    - под полями "Номер карты", "Месяц", "Год", "CVC/CVV" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 1.4 Не заполнено поле "Номер карты".

Шаги:
1. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
2. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
3. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
4. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
5. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Номер карты" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 1.5 Не заполнено поле "Месяц".

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
3. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
4. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
5. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Месяц" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 1.6  Не заполнено поле "Год".

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12.
3. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
4. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
5. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Год" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 1.7 Не заполнено поле "Владелец".

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
5. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Владелец появляется предупреждение "Поле обязательно для заполнения";
    - в БД не создается новая запись.

👩‍💻 1.8 Не заполнено поле "CVC/CVV".

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Кликнуть по кнопке "Продолжить"

**_Ожидаемый результат:_** 
  - под полем "CVC/CVV" появляется предупреждение "Неверный формат", 
  - в БД не создается новая запись.

👩‍💻 2.1.1 Не валидный номер карты - одна цифра.

Шаги:
1. Ввести в поле "Номер карты" - некорректный номер (например, 4).
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Номер карты" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 2.1.2 Не валидный номер карты - 15 цифр.

Шаги:
1. Ввести в поле "Номер карты" - некорректный номер 4444 4444 4444 444.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Номер карты" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.
  
👩‍💻 2.1.3 Не валидный номер карты - номер другого банка.

Шаги:
1. Ввести в поле "Номер карты" - номер 1234 5678 0000 0000.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - появляется уведомление "Ошибка! Банк отказал в проведении операции.";
    - в БД появилась запись со статусом DECLINED.
    - 
- 👩‍💻 2.1.4 Не валидный номер карты - в поле номер одни нули.

Шаги:
1. Ввести в поле "Номер карты" - номер 0000 0000 0000 0000.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
  - под полем "Номер карты" появляется предупреждение "Неверный формат";
  - в БД не создается новая запись.

👩‍💻 2.2.1 Неверный формат числа в поле "Месяц".

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - число 00.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Месяц" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 2.2.2 Неверный формат числа в поле "Месяц " - число больше 12.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 13 до 99.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Месяц" появляется предупреждение "Неверно указан срок действия карты";
    -  в БД не создается новая запись.

👩‍💻 2.2.3 Неверный формат числа в поле "Месяц " - одна цифра.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любая цифра от 0 до 9.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Месяц" появляется предупреждение "Неверно указан срок действия карты";
    - в БД не создается новая запись.

👩‍💻 2.2.4 Неверный формат числа в поле "Месяц " - срок действия истек.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - предыдущий месяц.
3. Ввести в поле "Год" - текущий год.
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Месяц" появляется предупреждение "Истёк срок действия карты";
    - в БД не создается новая запись.

👩‍💻 2.3.1 Неверный формат числа в поле "Год" - одна цифра.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - любая цифра от 0 до 9.
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Год" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 2.3.2 Неверный формат числа в поле "Год" - нули в поле год.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12.
3. Ввести в поле "Год" - число 00.
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Год" появляется предупреждение "Истёк срок действия карты";
    - в БД не создается новая запись.

👩‍💻 2.3.3 Неверный формат числа в поле "Год".

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12.
3. Ввести в поле "Год" - текущий год + 6 лет.
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Год" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 2.4.1 Неверный формат в поле "Владелец" - кириллица.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - имя на кириллице (например: Иван Петров).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Владелец" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 2.4.2 Неверный формат в поле "Владелец" - цифры.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - имя с цифрами (например, 123456).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Владелец" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 2.4.3 Неверный формат числа в поле "Владелец" - спецсимволы.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" -  +_.,\/~'@"-=?&%^()$#№;*:!.
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Владелец" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 2.4.4 Неверный формат числа в поле "Владелец" - иероглифы.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - ввести 水闻起来像玫瑰.
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Владелец" появляется предупреждение "Неверный формат"; 
    - в БД не создается новая запись.

👩‍💻 2.4.5 Неверный формат числа в поле "Владелец" - слишком короткое.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - ввести букву Y.
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Владелец" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 2.4.6 Неверный формат числа в поле "Владелец" - слишком длинное.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - ввести букву Y 30 раз.
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Владелец" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 2.4.7 Неверный формат числа в поле "Владелец" - пробел.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - несколько раз нажать пробел.
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
  - под полем "Владелец" появляется предупреждение "Поле обязательно для заполнения";
  - в БД не создается новая запись.

👩‍💻 2.5.1 Неверный формат числа в поле "CVC/CVV" - две цифры.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - номер из двух цифр (например, 12)
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "CVC/CVV" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 2.5.2 Неверный формат числа в поле "CVC/CVV" - три нуля.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - три нуля.
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
  - появилось всплывающее окно "Операция одобрена Банком"
  - в БД появилась запись со статусом APPROVED.

### **2) Выдача кредита по данным банковской карты.**💳

#### **Предусловие:**
1. Открыть в браузере страницу http://localhost:8080/.
2. Кликнуть по кнопке "Купить в кредит".

**Позитивные сценарии.** 🌞

👩🏻‍💻 3.1 Выдача кредита по карте со статусом **APPROVED**: ✅

Шаги:

1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет);
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV)
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
- появилось всплывающее окно "Операция одобрена Банком";
- в БД появилась запись со статусом APPROVED.

👩🏻‍💻 3.2 Выдача кредита по карте со статусом **DECLINED**: ❌

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4442.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет);
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV)
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
- появилось всплывающее окно "Ошибка! Банк отказал в проведении операции";
-  в БД появилась запись со статусом DECLINED.

**Негативные сценарии.** 👺

👩‍💻 3.3 Все поля пустые.

Шаги:
1. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Владелец" появляется предупреждение "Поле обязательно для заполнения";
    - под полями "Номер карты", "Месяц", "Год", "CVC/CVV" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 3.4 Не заполнено поле "Номер карты".

Шаги:
1. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
2. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
3. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
4. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
5. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Номер карты" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 3.5 Не заполнено поле "Месяц".

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
3. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
4. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
5. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Месяц" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 3.6  Не заполнено поле "Год".

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12.
3. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
4. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
5. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Год" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 3.7 Не заполнено поле "Владелец".

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
5. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Владелец появляется предупреждение "Поле обязательно для заполнения";
    - в БД не создается новая запись.

👩‍💻 3.8 Не заполнено поле "CVC/CVV".

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Кликнуть по кнопке "Продолжить"

**_Ожидаемый результат:_** 
  - под полем "CVC/CVV" появляется предупреждение "Неверный формат", 
  - в БД не создается новая запись.

👩‍💻 4.1.1 Не валидный номер карты - одна цифра.

Шаги:
1. Ввести в поле "Номер карты" - некорректный номер (например, 4).
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - валидное имя латинскими буквами (например: Kirill).
5. Ввести в поле "CVC/CVV" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Номер карты" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 4.1.2 Не валидный номер карты - 15 цифр.

Шаги:
1. Ввести в поле "Номер карты" - некорректный номер 4444 4444 4444 444.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - валидное имя латинскими буквами (например: Kirill).
5. Ввести в поле "CVC/CVV" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Номер карты" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 4.1.3 Не валидный номер карты - номер другого банка.

Шаги:
1. Ввести в поле "Номер карты" - номер 1234 5678 0000 0000.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - валидное имя латинскими буквами (например: Kirill).
5. Ввести в поле "CVC/CVV" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - появляется уведомление "Ошибка! Банк отказал в проведении операции.";
    - в БД данных появляется запись со статусом "DECLINED".

👩‍💻 4.2.1 Неверный формат числа в поле "Месяц".

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - число 00.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Месяц" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 4.2.2 Неверный формат числа в поле "Месяц " - число больше 12.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 13 до 99.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Месяц" появляется предупреждение "Неверно указан срок действия карты";
    - в БД не создается новая запись.

👩‍💻 4.2.3 Неверный формат числа в поле "Месяц " - одна цифра.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любая цифра от 0 до 9.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Месяц" появляется предупреждение "Неверно указан срок действия карты";
    - в БД не создается новая запись.

👩‍💻 4.2.4 Неверный формат числа в поле "Месяц " - срок действия истек.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - предыдущий месяц.
3. Ввести в поле "Год" - текущий год.
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Месяц" появляется предупреждение "Истёк срок действия карты";
    - в БД не создается новая запись.

👩‍💻 4.3.1 Неверный формат числа в поле "Год" - одна цифра.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - любая цифра от 0 до 9.
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Год" появляется предупреждение "Неверный формат";
    -  в БД не создается новая запись.

👩‍💻 4.3.2 Неверный формат числа в поле "Год" - истёк срок действия.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12.
3. Ввести в поле "Год" - число 00.
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Год" появляется предупреждение "Истёк срок действия карты";
    - в БД не создается новая запись.

👩‍💻 4.3.3 Неверный формат числа в поле "Год".

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12.
3. Ввести в поле "Год" - текущий год + 6 лет.
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Год" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 4.4.1 Неверный формат в поле "Владелец" - кириллица.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - имя на кириллице (например: Иван Петров).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Владелец" появляется предупреждение "Неверный формат";
    -  в БД не создается новая запись.

👩‍💻 4.4.2 Неверный формат в поле "Владелец" - цифры.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - имя с цифрами (например, PETROV67).
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Владелец" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 4.4.3 Неверный формат числа в поле "Владелец" - спецсимволы.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" -  +_.,\/~'@"-=?&%^()$#№;*:!.
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Владелец" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 4.4.4 Неверный формат числа в поле "Владелец" - иероглифы.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - ввести 水闻起来像玫瑰.
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Владелец" появляется предупреждение "Неверный формат"; 
    - в БД не создается новая запись.

👩‍💻 4.4.5 Неверный формат числа в поле "Владелец" - слишком короткое.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - ввести букву Y.
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Владелец" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 4.4.6 Неверный формат числа в поле "Владелец" - слишком длинное.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - ввести букву Y 70 раз.
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "Владелец" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 4.4.7 Неверный формат числа в поле "Владелец" - пробел.

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - несколько раз нажать пробел.
5. Ввести в поле "CVC/CVV" - номер из трех цифр (например: 123).
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
  - под полем "Владелец" появляется предупреждение "Поле обязательно для заполнения";
  - в БД не создается новая запись.

👩‍💻 4.5.1 Неверный формат числа в поле "CVC/CVV".

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - номер из двух цифр (например, 12)
6. Кликнуть по кнопке "Продолжить"

- **_Ожидаемый результат:_**
    - под полем "CVC/CVV" появляется предупреждение "Неверный формат";
    - в БД не создается новая запись.

👩‍💻 2.5.2 Неверный формат числа в поле "CVC/CVV".

Шаги:
1. Ввести в поле "Номер карты" - 4444 4444 4444 4441.
2. Ввести в поле "Месяц" - любое число от 01 до 12, таким образом, чтобы вместе с годом дата окончания действия карты была больше или равна текущей дате.
3. Ввести в поле "Год" - последние две цифры года ( в диапазоне от текущего до плюс пяти лет).
4. Ввести в поле "Владелец" - имя латиницей, заглавными буквами через пробел (например: IVAN PETROV).
5. Ввести в поле "CVC/CVV" - три нуля.
6. Кликнуть по кнопке "Продолжить"
7. 
- **_Ожидаемый результат:_**
  - появилось всплывающее окно "Операция одобрена Банком"
  - в БД появилась запись со статусом APPROVED.

### **II. Перечень используемых инструментов с обоснованием выбора.**

- **IntelliJ IDEA 2022.1 (Ultimate Edition)** - среда разработки, мощная и удобная IDE для java-разработки с поддержкой всех последних технологий и фреймворков.
- **Java 11** язык для написания авто-тестов, имеет набор готового ПО для разработки и запуска приложений.
- **Gradle** - сборка проекта, управление подключенными зависимостями, а так же для генерации отчётов о тестировании.
- **JUnit 5** - написание и запуск тестов. Не требует контроля пользователя во время исполнения тестов, может запускать одновременно несколько тестов, сообщает обо всех ошибках в ходе тестирования, предоставляет готовый набор методов для сравнения ожидаемого и фактического результатов.
- **Docker** - для развертывания виртуальных контейнеров баз данных, а также эмулятора сервисов банка на NodeJS
- **Selenide** - фреймворк для тестирования веб-приложений на основе Selenium WebDriver, осуществляет управление браузером, создаёт автоматические скриншоты.
- **Lombok** - плагин, предоставляет аннотации, позволяющие сократить время написания кода и сделать его компактнее.
- **Faker** для генерации тестовых данных.
- **Rest Assured** - java-библиотека для тестирования REST API, позволяет автоматизировать тестирование get и post запросов.
- **Allure** - фреймворк для создания отчетов о тестировании, для наглядного отображения прохождения тестов и ошибок.
- Appveyor - система CI CD.
- **Git** и **GitHub** для хранения кода. Git достаточно прост и удобен для управления исходным кодом, очень распространенная система контроля версий, поэтому достаточно хорошо взаимодействует с различными ОС. GitHub специализированный веб-сервис с удобным интерфейсом, интегрирован с Git.

### **III. Перечень и описание возможных рисков при автоматизации.**

- отсутствие документации к веб-сервису;
- стоимость инструментов для автоматизации тестирования;
- длительность написания авто-тестов;
- отсутствие специальных селекторов для тестирования;
- возможные ошибки на стороне сервера;
- требуется постоянная поддержка авто-тестов;

### **IV.** Перечень необходимых специалистов для автоматизации.

- Специалист по автоматизации тестирования - 1.

### **V. Интервальная оценка с учётом рисков (в часах).**

- подготовка окружения, инфраструктуры, развертывание БД - 6 часов;
- написание авто-тестов, тестирование - 48 часов;
- формирование и анализ отчетов - 8 часов.

### **V. План сдачи работ (когда будут авто-тесты, результаты их прогона и отчёт по автоматизации).**

- 27.04-29.04 - планирование автоматизации тестирования.
- 30.04-15.05 - написание авто-тестов.
- 16.05-18.05 - прогон авто-тестов и написание баг-репортов.
- 19.05 - подготовка отчётных документов по итогам автоматизации.