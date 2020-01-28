# Дипломный проект

[План работ](https://github.com/gruzdevni/diploma/blob/master/resources/Plan.md)

<details>
<summary>**Порядок действий для обеспечения возможности запуска автотестов**</summary>

### Все действия выполняются на Windows. Если у вас другая ОС, то шаги/команды могут отличаться.

1. В соответствии с планом работ на локальную машину должны быть установлены MySQL, PostgreSQL (далее система управления базой данных - СУБД), Node.js, DBeaver.
2. Для возможности запуска авто-тестов необходимо выполнить следующие действия:
    * Скачать и установить СУБД. Настроить DBeaver.
        + MySQL (http://www.mysql.ru/download/)
            - Во время первоначальной конфигурации установщик предложит создать пользователей.
            ![mysql-user-creation-during-install](https://github.com/gruzdevni/diploma/blob/master/resources/mysql-user-creation-during-install.PNG)
            Создайте пользователя app c паролем pass.
            <br>![mysql-user-properties](https://github.com/gruzdevni/diploma/blob/master/resources/mysql-user-properties.PNG)</br>
            - После установки СУБД зайти в командную строку.
            ![mysql-cmd-loaded](https://github.com/gruzdevni/diploma/blob/master/resources/mysql-cmd-loaded.PNG)
            С помощью команды `CREATE DATABASE app` создайте базу данных.
            ![mysql-appDB-creation](https://github.com/gruzdevni/diploma/blob/master/resources/mysql-appDB-creation.PNG)
            - Через DBeaver настроить подключение к БД. Пример настройки подключения к MySQL:
            ![DB-connection-settings](https://github.com/gruzdevni/diploma/blob/master/resources/DB-connection-settings.png)
            - В свойстве драйвера `allowPublicKeyRetrieval` поставить значение `TRUE`. В `serverTimezone` - `CET`.
        + PostgreSQL (https://www.postgresql.org/download/)
            - Создание пользователя и БД выполняется через дефолтный SQL Shell.
            ![postgres-cmd-loaded](https://github.com/gruzdevni/diploma/blob/master/resources/postgres-cmd-loaded.PNG)
            - С помощью команды `CREATE DATABASE app;` создайте базу данных.
            ![postgres-appDB-creation](https://github.com/gruzdevni/diploma/blob/master/resources/postgres-appDB-creation.PNG)
            - Создайте пользователя app c паролем pass командой `CREATE USER app WITH password 'pass';`.
            ![postgres-appuser-created](https://github.com/gruzdevni/diploma/blob/master/resources/postgres-appuser-created.PNG)
            - Командой `GRANT ALL ON DATABASE app TO app;` выдайте пользователю привилегии.
            ![postgres-appuser-grant](https://github.com/gruzdevni/diploma/blob/master/resources/postgres-appuser-grant.PNG)
            - Через DBeaver настроить подключение к БД. Пример настройки подключения к PostgreSQL:
            ![postgreSQL-connection-settings](https://github.com/gruzdevni/diploma/blob/master/resources/postgreSQL-connection-settings.PNG) 
    * Выполнить подключение к БД. Таблиц в БД нет, так как приложение еще не запущено.
    * Запустить сервис gate-simulator. В корне проекта имется папка gate-simulator. Из этой папки нужно выполнить команду `npm start`.
      Скриншот запущенного приложения:
      ![gate-simulator-launched](https://github.com/gruzdevni/diploma/blob/master/resources/gate-simulator-launched.png)
      
      ___Примечания___
      
      _База данных app создается единожды (для каждой СУБД)._
</details>

* Для запуска приложения под MySQL использовать команду
    `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar aqa-shop.jar`
    
* Для запуска под PostgreSQL использовать команду
    `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar aqa-shop.jar`
    
* Для запуска тестов для MySQL использовать команду
     `gradlew -Durl=jdbc:mysql://localhost:3306/app clean test allureReport`
     
* Для запуска тестов для PostgreSQL использовать команду
     `gradlew -Durl=jdbc:postgresql://localhost:5432/app clean test allureReport`