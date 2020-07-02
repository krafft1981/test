Java. Тестовое задание
Постановка
Требуется разработать веб-приложение, функционально приближенное к системе
учета книг с дополнительным функционалом.


Обязательные требования

Стек технологий
   ●   Spring Boot
   ●   Spring Data
   ●   JPA (Hibernate)
   ●   Swagger 2


Сущности (с минимальным обязательным набором полей)
   ●   книга
          ○ наименование;
          ○ год издания;
          ○ аннотация;
          ○ авторы
   ● Автор
          ○ ФИО
          ○ год рождения
          ○ книги
Книга связана с Автором через many-to-many связь.


Требования к API
CRUD методы доступа к Авторам и Книгам:
   ● Создание
   ● Редактирование
   ● Получение
   ● Получение списка
   ● Удаление
Данные передаются в виде JSON.


СУБД
PostgreSQL (версии ?)
Система контроля версий
Проект должен быть залит на Github


Дополнительные требования (на выбор)

Вариант 1
Модификация системы путем добавления следующих сущностей и функционала:

Сущности
   ●   клиент
          ○ имя;
          ○ номер телефона;
          ○ заказы;
   ●   заказ
          ○ клиент;
          ○ список книг;
          ○ дата создания заказа;
          ○ дата исполнения заказа;
          ○ флаг исполнения заказа.

API
CRUD методы доступа к Заказам, аналогичным ​Обязательной части задания​.
Методы "Создать заказ", "Получить список заказов Клиента", "Выполнить заказ"
Метод, формирующий отчет по клиентам: Какой клиент сколько книг заказал за
указанный в параметрах период времени. Еще один необязательный параметр -
указывать, включать ли только завершенные заказы. Если параметр указан как false, в
отчет добавить информацию о том, был ли заказ завершен, иначе эту информацию не
добавлять.


Вариант 2
Модификация системы путем добавления следующих сущностей и функционала:
  ● магазин
        ○ адрес;
        ○ список книг;
  ● ценник
        ○ книга;
        ○ цена;
  ● продажи
        ○ дата продажи книги;
        ○ магазин, продавший книгу;
API
CRUD методы доступа к магазинам, аналогичным ​Обязательной части задания​.
Метод формирования отчетов о продажах по магазинам. Метод принимает в качестве
параметров:
1) Магазин (или список) по которым формируется отчет (необязательный)
2) Период даты, которая будет включена в отчет (необязательный)
Отчет должен включать в себя:
Магазин, общее количество проданных книг, общая выручка.

