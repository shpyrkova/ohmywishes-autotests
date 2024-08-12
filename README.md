<h1>Проект автоматизации тестирования приложения <a target="_blank" href="https://ohmywishes.ru/"> Ohmywishes </a> </h1>

<p align="center">
<img alt="Ohmywishes" src="images/logo/ohmywishes.png"  >
</p>

## Содержание
+ [Описание](#Описание)
+ [Технологии и инструменты](#Технологии-и-инструменты)
+ [Реализованные проверки](#Реализованные-проверки)


## Описание
Ohmywishes — сервис для создания вишлистов. Можно создавать собственные вишлисты и следить за тем, о чём мечтают ваши друзья.
Проект автоматизации состоит из трех частей:
- UI-тесты на WEB-приложение
- Тесты на API WEB-приложения 
- Мобильные тесты на Android <br/>


**Особенности проекта**:
- `Page Object` для описания страниц WEB приложения
- Использование `Owner` для придания тестам легкости конфигурации
- Возможность запуска тестов: локально, удалённо, по тегам
- Использование `Faker` для генерации данных
- Использование `Lombok` для моделей данных
- Предусловия в WEB и API тестах создаются через API
- Возможность запуска тестов напрямую из Allure TestOps
- Интеграция с Jira
- Уведомление о результатах прохождения в Telegram
- По результатам прохождения автотестов генерируется Allure отчет. Содержание отчета:
    - Шаги теста
    - Скриншот страницы (экрана) на последнем шаге
    - Логи консоли браузера
    - Видео выполнения автотеста

## Технологии и инструменты

<div align="center">
<a href="https://www.jetbrains.com/idea/"><img alt="InteliJ IDEA" height="50" src="images/logo/Idea.svg" width="50"/></a>
<a href="https://github.com/"><img alt="GitHub" height="50" src="images/logo/GitHub.svg" width="50"/></a>  
<a href="https://www.java.com/"><img alt="Java" height="50" src="images/logo/Java.svg" width="50"/></a>
<a href="https://gradle.org/"><img alt="Gradle" height="50" src="images/logo/Gradle.svg" width="50"/></a>  
<a href="https://junit.org/junit5/"><img alt="JUnit 5" height="50" src="images/logo/Junit5.svg" width="50"/></a>
<a href="https://selenide.org/"><img alt="Selenide" height="50" src="images/logo/Selenide.svg" width="50"/></a>
<a href="https://aerokube.com/selenoid/"><img alt="Selenoid" height="50" src="images/logo/Selenoid.svg" width="50"/></a>
<a href="https://rest-assured.io/"><img alt="RestAssured" height="50" src="images/logo/RestAssured.svg" width="50"/></a>
<a href="https://appium.io/"><img alt="Appium" height="50" src="images/logo/Appium.svg" width="50"/></a>
<a href="https://developer.android.com/studio"><img alt="Android Studio" height="50" src="images/logo/Android_Studio.svg" width="50"/></a>
<a href="https://www.jenkins.io/"><img alt="Jenkins" height="50" src="images/logo/Jenkins.svg" width="50"/></a>
<a href="https://github.com/allure-framework/"><img alt="Allure Report" height="50" src="images/logo/Allure.svg" width="50"/></a>
<a href="https://qameta.io/"><img alt="Allure TestOps" height="50" src="images/logo/Allure_TO.svg" width="50"/></a>
<a href="https://www.atlassian.com/software/jira"><img alt="Jira" height="50" src="images/logo/Jira.svg" width="50"/></a>  
<a href="https://telegram.org/"><img alt="Telegram" height="50" src="images/logo/Telegram.svg" width="50"/></a>
</div>

## Реализованные проверки
### WEB
- [x] Успешная авторизация по email и паролю
- [x] Попытка авторизации с невалидным паролем
- [x] Главная страница: Для неавторизованного пользователя по переходу на 'Мои желания' открывается страница авторизации
- [x] Создание нового желания с заполнением всех полей
- [x] Отметка желания как подаренного
- [x] Добавление желания в созданный пользователем список
- [x] Создание игры Тайный санта с заполнением всех полей

### API
- [x] Получение данных о пользователе (о себе)
- [x] Получение данных о желании неавторизованным пользователем
- [x] Удаление желания
- [x] Создание списка желаний
- [x] При удалении кастомного списка желаний желания из этого списка не удаляются из системы

### Mobile
- [x] Авторизация по электронной почте
- [x] Запрос на сброс пароля
## Запуск тестов

> [!NOTE]
> Убедитесь, что у вас установлены Java, Gradle, IntelliJ IDEA и в качестве браузера используется Chrome
>

Конфигурационные файлы `.properties` лежат в папке `resources`. <br/>
При необходимости в них можно менять параметры окружения (браузеры, устройства и их версии).

### Допустимые комбинации

```mermaid 
flowchart LR
    A(gradle) --> B(clean)
    B --> C{Выбрать тег}
    C --> D[test]
    C --> E[web]
    C --> F[api]
    C --> G[android]
    C --> H[for_jenkins]
    E --> I[-Denv=local]
    E --> J[-Denv=remote]
    G --> K[-DdeviceHost=emulator]
    G --> L[-DdeviceHost=real]
```

### Запуск тестов на Jenkins
Так как тесты на Android запускаются только локально на эмуляторе или реальном устройстве, для Jenkins выделена отдельная задача for_jenkins, запускающая все WEB и API тесты. <br>
Для запуска на jenkins необходимо также указывать параметр удаленного запуска -Denv=remote. 
```
gradle clean for_jenkins -Denv=remote
```
#### Параметры, которыми можно управлять (указывать их необязательно):
```
-DbrowserName - наименование браузера (_по умолчанию - <code>chrome</code>_).
-DbrowserVersion - номер версии браузера (_по умолчанию - <code>122</code>_).
-DbrowserSize - размер окна браузера. (_по умолчанию - <code>1980x1080</code>_).
```
### Локальный запуск тестов
Тесты могут быть запущены как командами через терминал, так и по кнопке в IntelliJ IDEA. 
#### WEB
Команда без параметров по умолчанию запускает все тесты локально: 

```
gradle clean web
```
Для запуска на удаленном Selenoid нужно указать параметр env:
```
gradle clean web -Denv=remote
```

#### API
Команда запускает все API тесты: 
```
gradle clean api 
```

#### Mobile

Для запуска мобильных тестов нужно: 
1. Запустить Appium Server и устройство, на котором будут выполняться тесты.
2. Обновить параметры устройства в файлах resources/mobile/real.properties или resources/mobile/emulation.properties.
3. Определить значение DdeviceHost:
- <code>-DdeviceHost=emulator</code> : тесты будут запущены в эмуляторе. 
- <code>-DdeviceHost=real</code> : тесты будут запущены на реальном подключенном устройстве. 
4. Запустить следующую команду с указанием deviceHost:
```
gradle clean android -DdeviceHost=emulation
```

#### Построение Allure отчета после локального запуска

Команда для открытия отчета в браузере:
```
gradle allureServe
```


