# Пример проекта тестов  

Используемые технологии:  
Java, Gradle, Allure, Junit5, Selenide, Rest-assured  

Проект параметризирован:  
1. для запуска многопоточного выполнения указать -Pthreads=2 (2 потока)  
2. Для выбора браузера указать -Dbrowser=chrome (chrome or firefox)  
3. Версия браузера -Dversion= (пример для chrome)  
4. Выбор запуска UI тесты или API тесты через задачу в gradle.build  
5. Запуск браузера без UI (-Dselenide.headless=true)  
6. Запись видео прохождения тестов  
        - установить через системные свойства параметр remote_driver_url  
        - установить через системные свойства параметр video_storage_url
7. для тренировки сделана системная переменная -Ddriver=qaguru or myserver через которую  
   можно загрузить адреса удаленного драйвера через разные файлы
8. добавлен телеграмм бот для отправки сообщений о прохождении тестов. Добавление в Jenkins
в post build "java -jar telegramBot.jar "-chatID" "token" "${JOB_BASE_NAME}" "allure-report/" ${BUILD_URL}

в папке main/resources должен быть файл config.properties с параметрами для логина на github:  

github.login=LOGIN  
github.password=PASSWORD  
github.token=API_TOKEN  
github.base_url=(for api test)

Примеры локального запуска:  
gradle web -Pthreads=2 -Dbrowser=chrome allure:serve  
gradle api allure:serve 

К тесту в отчете прикладываются последние скриншоты и логи выполнения.  

![пример отчета](/screenshots/report.png) 
![пример отчета](/screenshots/allure_ee.png) 
![пример отчета](/screenshots/jira_test.png) 
