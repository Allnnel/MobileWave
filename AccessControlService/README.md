# Микросервис "AccessControlService"


## Цель

Реализация возможности управления правами и доступностью пользователей на платформе.

# Бизнес требование

## Типы блокировки клиентов

- finBlock: Финансовая блокировка клиента
- admBlock: Административная блокировка клиента

## Сценарий 1: Проверка блокировки клиента

Вызов метода GET /sec/checkBlock микросервиса Права и доступы.

### Параметры:

- ctn: Номер телефона
- token: Системный токен

#### Вызов метода GET /sec/checkSystemToken микросервиса Система токенов.

##### Параметры:
- ctn и token из шага 1

##### Проверка ответа от /sec/checkToken:

- Если ошибка: TOKEN_ERROR
- Если успешно: Продолжение сценария

##### Проверка наличия блокировки на клиенте в таблице usersBlock:

- Если блокировка не найдена: Продолжение сценария
- Если блокировка найдена: Продолжение сценария

##### Формирование ответа:

- status: Успех или ошибка
- clientBlocked: Присутствие блокировки (true/false)
- При наличии блокировок: clientBlock: Список блокировок
- При ошибке: errorCode и errorMessage

## Сценарий 2: Назначение блокировки на клиента

Вызов метода POST /sec/block микросервиса Права и доступы.

### Параметры:

- ctn: Номер телефона
- token: Системный токен
- blockType: Тип блокировки
- endDate: Необязательный параметр
- description: Необязательный параметр

#### Вызов метода GET /sec/checkSystemToken микросервиса Система токенов.

##### Параметры: ctn и token из шага 1

##### Проверка ответа от /sec/checkToken:

- Если ошибка: TOKEN_ERROR
- Если успешно: Продолжение сценария

##### Проверка наличия типа блокировки в таблице blockList:

- Если тип найден: Продолжение сценария
- Если тип не найден: BLOCK_TYPE_NOT_FOUND

##### Проверка наличия блокировки на клиенте в таблице usersBlock:

- Если блокировка не найдена: Продолжение сценария
- Если блокировка найдена: CTN_ALREADY_BLOCKED

##### Создание блокировки в таблице usersBlock с использованием значений из шага 1.

- Проставление текущей даты в поле startDate.
- Проставление null для необязательных полей, если не переданы.

##### Формирование ответа:

- status: Успех или ошибка
- При ошибке: errorCode и errorMessage

## Сценарий 3: Изменение блокировки на клиента

Вызов метода PUT /sec/block микросервиса Права и доступы.

### Параметры:

- ctn: Номер телефона
- token: Системный токен
- blockType: Тип блокировки
- endDate: Необязательный параметр
- description: Необязательный параметр

#### Вызов метода GET /sec/checkSystemToken микросервиса Система токенов.

##### Параметры: ctn и token из шага 1

##### Проверка ответа от /sec/checkToken:

- Если ошибка: TOKEN_ERROR
- Если успешно: Продолжение сценария

##### Проверка наличия блокировки на клиенте типа из шага 1:

- Если блокировка не найдена: CTN_NOT_BLOCKED
- Если блокировка найдена: Продолжение сценария

##### Обновление блокировки в таблице usersBlock с использованием значений из шага 1.

- Поле startTime не изменяется.
- Проставление null для необязательных полей, если не переданы.

##### Формирование ответа:

- status: Успех или ошибка
- При ошибке: errorCode и errorMessage

## Сценарий 4: Удаление блокировки клиента

Вызов метода DELETE /sec/block микросервиса Права и доступы.

### Параметры:

- ctn: Номер телефона
- blockType: Тип блокировки
- token: Системный токен

#### Вызов метода GET /sec/checkSystemToken микросервиса Система токенов.

##### Параметры: ctn и token из шага 1

##### Проверка ответа от /sec/checkToken:

- Если ошибка: TOKEN_ERROR
- Если успешно: Продолжение сценария

##### Проверка наличия блокировки на клиенте типа из шага 1:

- Если блокировка не найдена: CTN_NOT_BLOCKED
- Если блокировка найдена: Продолжение сценария

##### Удаление блокировки из таблицы usersBlock по типу из шага 1.

##### Формирование ответа:

- status: Успех или ошибка
- При ошибке: errorCode и errorMessage

## База данных

| Таблица      | Поля           |
|--------------|----------------|
| BlockList    | blockType      |
|              | description    |
|--------------|----------------|
| usersBlock   | ctn            |
|              | blockType      |
|              | startDate      |
|              | endDate        |
|              | description    |

