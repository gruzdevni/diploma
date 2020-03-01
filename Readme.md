# Дипломный проект

[План работ](https://github.com/gruzdevni/diploma/blob/master/resources/Plan.md)

Предварительные шаги:
* Склонируйте репозиторий с дипломным проектом:

`git clone https://github.com/gruzdevni/diploma`

* Для создания сервера БД и запуска базы на Postgres выполните:

`$ docker run --name aqa-post -p 5432:5432 -e POSTGRES_USER=app -e POSTGRES_PASSWORD=pass -e POSTGRES_DB=app -d postgres`

`$ docker start aqa-post`

* Для создания сервера БД и запуска базы на MySQL выполните:

`$ docker run --name aqa-mysql -p 3306:3306 -e MYSQL_USER=app -e MYSQL_PASSWORD=pass -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=app -d mysql`

`$ docker start aqa-mysql`

* Для запуска приложения на Node.js выполните:

`$ docker run --name gate -p 9999:9999 -d nikolaygr/gate-sim`

<a name="AUT_launch"></a>После выполнения вышеуказанных шагов можно запускать AUT:

* Для запуска приложения под MySQL использовать команду

    `java -Dspring.datasource.url=jdbc:mysql://192.168.99.100:3306/app -jar aqa-shop.jar`
    
* Для запуска под PostgreSQL использовать команду

    `java -Dspring.datasource.url=jdbc:postgresql://192.168.99.100:5432/app -jar aqa-shop.jar`
    
* Для запуска тестов для MySQL использовать команду

     `gradlew -Durl=jdbc:mysql://192.168.99.100:3306/app clean test allureReport`
     
* Для запуска тестов для PostgreSQL использовать команду

     `gradlew -Durl=jdbc:postgresql://192.168.99.100:5432/app clean test allureReport`
     
* Для получения отчета Allure:
`gradlew allureServe`    

<details>
<summary>**Если у вас нет возможности использовать Docker, следуйте данной инструкции**</summary>

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
    * Запустить сервис gate-simulator. В корне проекта имется папка gate. Из этой папки нужно выполнить команду `npm start`.
      Скриншот запущенного приложения:
      ![gate-simulator-launched](https://github.com/gruzdevni/diploma/blob/master/resources/gate-simulator-launched.png)
3. После этих шагов следовать командам выше по [ссылке](#AUT_launch).    
      
      ___Примечания___
      
      _База данных app создается единожды (для каждой СУБД)._
</details>