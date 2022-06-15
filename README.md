## Дипломная работа профессии Тестировщик

Дипломный проект представляет собой автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

#### Начало работы:
1) Открыть интегрированную среду разработки программного обеспечения - IntelliJ IDEA Ultimate;
2) Склонировать [репозиторий](https://github.com/Crazyhell13/qa-diploma.git) c помощью команды: ```git clone```;
3) Запустить Docker Desktop.
   
#### Prerequisites
На ПК должны быть установлены:
- Интегрированная среда разработки программного обеспечения - IntelliJ IDEA Ultimate;
- [Docker Desktop](https://www.docker.com/products/docker-desktop/);
- Веб браузер;
- Git.

#### Установка и запуск авто-тестов:
1) Запустить контейнеры СУБД MySQl, PostgreSQL и Node.js командой в терминале:
```docker-compose up -d ```
2) Запустить SUT 
+ на СУБД MySQl командой:
```java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar```
+ на СУБД PostgreSQL командой:
```java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar```
3) В новом терминале запустить авто-тесты 
+ для MySQL:
```./gradlew clean test -Durl=jdbc:mysql://localhost:3306/app```
+ для PostgreSQL:
```./gradlew clean test -Durl=jdbc:postgresql://localhost:5432/app```
4) Сгенерировать отчет:
+ ```./gradlew allureReport```
5) Остановить и удалить все контейнеры можно с помощью команды:
```docker-compose down```
6) При необходимости отключения SUT, находясь в терминале IntelliJ IDEA, нажать клавиши ```CTRL+C```
#### Документация:

1) [План автоматизации](https://github.com/Crazyhell13/qa-diploma/blob/1432f21ad8162731475de2e82e0ed7dd15c6ecc4/documentation/Plan.md)
2) [Отчет о тестировании]()
3) [Отчет по итогам автоматизации]()
