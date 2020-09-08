# Пример проекта тестов  

Используемые технологии:  
Gradle, Java, Allure, Junit5, Selenide, Rest-assured  

Проект параметризирован:  
1. для запуска многопоточного выполнения указать -Pthreads=2 (2 потока)  
2. Для выбора браузера указать -Dbrowser=chrome (chrome or firefox)  
3. Версия браузера -Dversion=85 (пример для chrome)  
4. Выбор запуска UI тесты или API тесты через задачу в gradle.build  
5. Запуск браузера без UI (-Dheadless=true)  

в папке main/resources должен быть файл config.properties с параметрами для логина на github:  

github.login=LOGIN  
github.password=PASSWORD  
github.token=API_TOKEN  

Примеры локального запуска:  
gradle web -Pthreads=2 -Dbrowser=chrome allure:serve  
gradle api allure:serve 

К тесту в отчете прикладываются последние скриншоты и логи выполнения.  

![пример отчета](/screenshots/report.png) 
