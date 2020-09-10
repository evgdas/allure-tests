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
7. для тренировки сделана системная переменная -Ddriver=local or remote через которую  
   можно загрузить адреса удаленного драйвера через файл

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
