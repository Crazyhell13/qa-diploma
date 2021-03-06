## Отчёт о проведённом тестировании
### Краткое описание
Была протестирована работа комплексного сервиса, взаимодействующего с двумя СУБД и API Банка согласно составленному [тест-плану](https://github.com/Crazyhell13/qa-diploma/blob/1432f21ad8162731475de2e82e0ed7dd15c6ecc4/documentation/Plan.md).

Тесты запускаются как для БД MySQL, так и для PostgreSQL. Реализованные тест-кейсы проходят одинаково, независимо от базы данных.

### Количество тест-кейсов
Всего 60 тест-кейсов:
- 30 для оплаты по дебетовой карте;
- 30 для оплаты по кредитной карте.

### Из которых:
- Успешных:33   
- Не успешных:28 

![image](https://user-images.githubusercontent.com/89997099/173885520-af71888c-7b54-45ad-8ba9-c49fe03244fc.png)

### В Github заведены следующие баг-репорты:
1) [Неверное информационное сообщение при оплате картой со статусом "DECLINED"](https://github.com/Crazyhell13/qa-diploma/issues/1);
2) [Отсутствует сообщение об ошибке при вводе одних нулей в поле "Номер карты"](https://github.com/Crazyhell13/qa-diploma/issues/2);
3) [Отсутствует валидация поля "Владелец"](https://github.com/Crazyhell13/qa-diploma/issues/3);
4) [Сообщение об ошибке под полем "Владелец" при незаполненном поле "CVC/CVV"](https://github.com/Crazyhell13/qa-diploma/issues/4);
5) [Неверное сообщение об ошибке при истекшем сроке валидности банковской карты](https://github.com/Crazyhell13/qa-diploma/issues/5);
6) [В поле "Месяц" можно ввести "00"](https://github.com/Crazyhell13/qa-diploma/issues/6);
7) [Данные по оплате в кредит ошибочно записываются в столбец payment_id в базе данных](https://github.com/Crazyhell13/qa-diploma/issues/7);
8) [Неверное информационное сообщение при покупке в кредит картой со статусом "DECLINED"](https://github.com/Crazyhell13/qa-diploma/issues/8);
9) [Сообщения об ошибках под полями "Номер карты", "CVC/CVV" не исчезают после их исправления ](https://github.com/Crazyhell13/qa-diploma/issues/9);
10) [Неправильное название заголовка страницы](https://github.com/Crazyhell13/qa-diploma/issues/10);
11) [Неверное двойное уведомление при оплате картой, не входящей в тестовый набор](https://github.com/Crazyhell13/qa-diploma/issues/11);
12) [Опечатка в названии города](https://github.com/Crazyhell13/qa-diploma/issues/12);


### Общие рекомендации

В тестируемой системы выявлены значительные ошибки, влияющие как на бизнес логику, так и на пользовательский опыт.

- Реализовать валидацию поля "Владелец" согласно шаблону "NAME SURNAME"
- Ввести подсказку "Укажите имя как на карте"
- Исправить баг с покупкой по отклоненной карте
- Исправить орфографические ошибки и заголовок страницы.

