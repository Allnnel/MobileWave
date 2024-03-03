# Отчет по заданию "BreadcrumbsMobileWave":

Проект представляет собой сервис по предоставлению мобильной связи клиентам. Этот сервис разработан для обучения и оценки новых подходов к предоставлению мобильной связи, 
а также для оценки эффективности различных внутренних систем. 

## Микросервис "AccessControlService"

## Цель
Обеспечение контроля доступа пользователей к системе путем управления блокировками.
 
### Сценарий 1: Проверка блокировки клиента
- **Запрос**: GET /sec/checkBlock
- **Параметры входа**: ctn (Номер телефона), token (Системный токен)
- **Возвращаемое**: 
  - status (Успех или ошибка)
  - clientBlocked (Присутствие блокировки: true/false)
  - clientBlock (Список блокировок, если присутствуют)
  - errorCode и errorMessage при ошибке

### Сценарий 2: Назначение блокировки на клиента
- **Запрос**: POST /sec/block
- **Параметры входа**: ctn (Номер телефона), token (Системный токен), blockType (Тип блокировки), endDate (Необязательный), description (Необязательный)
- **Возвращаемое**: 
  - status (Успех или ошибка)
  - errorCode и errorMessage при ошибке

### Сценарий 3: Изменение блокировки на клиента
- **Запрос**: PUT /sec/block
- **Параметры входа**: ctn (Номер телефона), token (Системный токен), blockType (Тип блокировки), endDate (Необязательный), description (Необязательный)
- **Возвращаемое**: 
  - status (Успех или ошибка)
  - errorCode и errorMessage при ошибке

### Сценарий 4: Удаление блокировки клиента
- **Запрос**: DELETE /sec/block
- **Параметры входа**: ctn (Номер телефона), blockType (Тип блокировки), token (Системный токен)
- **Возвращаемое**: 
  - status (Успех или ошибка)
  - errorCode и errorMessage при ошибке

## База данных

### Таблица BlockList
- **blockType**: Тип блокировки
- **description**: Описание блокировки
- 
### Таблица usersBlock
- **ctn**: Номер телефона пользователя
- **blockType**: Тип блокировки
- **startDate**: Дата начала блокировки
- **endDate**: Дата окончания блокировки
- **description**: Описание блокировки

# Микросервис "SystemTokenProvider"

## Цель
Предоставление микросервисам возможности использовать системные токены вместо токенов авторизации.

## Описание
Системные токены хранятся в базе данных (БД), и к их созданию нет API.

## Сценарий 1: Проверка наличия системного токена
- **Запрос**: GET /sec/st/checkSystemToken
- **Параметры входа**: systemToken (системный токен)
- Бекенд проверяет наличие системного токена в таблице systemToken (БД).
  - Если токен не найден, сценарий завершается ошибкой TOKEN_NOT_FOUND.
  - Если токен найден, бекенд формирует ответ:
    - status: success или error
    - system: система потребитель из таблицы
    - startDate: дата начала из таблицы
    - endDate: дата окончания из таблицы
    - description: описание окончания
  - При наличии ошибки передается поле errorCode и errorMessage с описанием ошибки.

## Сценарий 2: Проверка валидности любого токена
- **Запрос**: GET /sec/st/checkToken
- **Параметры входа**: ctn (необязательный параметр), token (проверяемый токен)
- Если было передано ctn в шаге 1, бекенд вызывает метод GET /core/sr/checkAuthToken микросервиса Система регистрации, на вход передаются параметры:
  - ctn: номер телефона
  - token: токен авторизации
- Если получен ответ success, бекенд переходит к шагу 4.
- Бекенд проверяет наличие токена в таблице systemToken (БД).
  - Если токен не найден, сценарий завершается ошибкой TOKEN_NOT_FOUND.
  - Если токен найден, бекенд формирует ответ:
    - status: success или error
  - При наличии ошибки передается поле errorCode и errorMessage с описанием ошибки.

## База данных
### Таблица systemToken
| Поле          | Описание               |
|---------------|------------------------|
| systemToken   | Токен                  |
| startDate     | Дата начала действия   |
| endDate       | Дата окончания         |
| description   | Описание                |
| system        | Система потребитель    |

## Ошибки
| Код ошибки      | Сообщение передаваемое на фронт |
|-----------------|----------------------------------|
| TOKEN_NOT_FOUND | Токен не найден                   |

# Микросервис "BalanceManagement"

## Цель
Реализация баланса пользователей и механизма списания средств.

## Сценарии

### 1. Создание баланса пользователя
- POST /balance/userBalance
- Параметры: systemToken, ctn
- Проверяет системный токен, создает баланс, если отсутствует.

### 2. Получение баланса пользователя
- GET /balance/userBalance
- Параметры: systemToken, ctn
- Возвращает текущий баланс пользователя.

### 3. Изменение баланса пользователя
- PUT /balance/userBalance
- Параметры: systemToken, ctn, operation, value, description (опционально)
- Обновляет баланс пользователя, учитывает тип операции и блокировки.

### 4. Получение истории операций пользователя
- GET /balance/userOperationHistory
- Параметры: systemToken, ctn
- Возвращает историю операций пользователя.

### 5. Получение ежедневной операции пользователя
- GET /balance/userOperation
- Параметры: systemToken, ctn, name
- Возвращает ежедневную операцию пользователя.

### 6. Создание ежедневной операции пользователя
- POST /balance/userOperation
- Параметры: systemToken, name, ctn, operation, value, active, description (опционально)
- Создает новую ежедневную операцию для пользователя.

## Сценарий 7: Изменение ежедневной операции у пользователя
- PUT /balance/userOperation
- Параметры: systemToken, body (name, ctn, operation, value, active, description)
- Формирует ответ: status (success или error).

## Сценарий 8: Удаление ежедневной операции у пользователя
- DELETE /balance/userOperation
- Параметры: systemToken, name, ctn
- Формирует ответ: status (success или error).

## Сценарий 9: Получение списка ежедневных операций у пользователя
- GET /balance/userOperationList
- Параметры: systemToken, ctn
- Формирует ответ: status (success или error), userOperationList: [ {operation}, {operation}… ].

## Сценарий 10: Получение суммы ежедневных операций у пользователя
- GET /balance/userOperationListSum
- Параметры: systemToken, ctn
- Формирует ответ: status (success или error), userIsActiveSumm: {{summ}}, userNotActiveSumm: {{summ}}.

### Технический сценарий. Ежедневные операции у пользователей:
- Создается поток для ежедневных операций в 2:00 по МСК.
- Операции залогированы и отражены в таблице usersOperationHistory.
- Получает список операций со статусом "active" = true, сортирует их по пользователям.
- Для каждой операции вызывается метод PUT /balance/userBalance микросервиса Балансы пользователей.
- Логируется количество начисленных и списанных средств.

## База данных

### Таблица usersBalance
- **ctn**: Номер телефона
- **balance**: Баланс

### Таблица usersOperationHistory
- **ctn**: Номер телефона
- **operation**: Тип операции
- **value**: Сумма
- **description**: Описание операции
- **date**: Дата операции

### Таблица usersOperationList
- **name**: Наименование операции
- **ctn**: Номер телефона
- **operation**: Тип операции
- **value**: Сумма
- **description**: Описание операции
- **Active**: Boolean. Активна ли операция
- **DateLastWithdrawal**: Дата последней операции

## Ошибки

| Код ошибки                  | Сообщение передаваемое на фронт   |
|-----------------------------|-----------------------------------|
| BALANCE_ALREADY_CREATED     | Баланс уже создан                 |
| BALANCE_NOT_FOUND           | Баланс не найден                  |
| OPERATION_TYPE_NOT_FOUND    | Тип операции не найден            |
| OPERATION_ALREADY_CREATED   | Операция уже создана              |
| OPERATION_NOT_FOUND         | Операция не найдена               |


