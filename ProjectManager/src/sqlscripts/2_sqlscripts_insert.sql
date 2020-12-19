--CATEGORY------------------------------------------------

INSERT INTO roles(ROLE_NAME) VALUES('Администратор'),('Модератор'),('Пользователь');
INSERT INTO availability_risk(AVAILABILITY_NAME) VALUES('Есть'),('Нет');
INSERT INTO currencies(CURRENCY_EXCHANGE_RATE,CURRENCY_NAME) VALUES(2.53,'USD'),(3.07,'EUR'),(3.44,'RUB'),(19.387,'BTC');
INSERT INTO states_tasks(STATE_NAME) VALUES('Инициирование'),('Планировние'),('Разработка'),('Одобрение'),('Закрыт');
INSERT INTO priority_tasks(PRIOROTY_NAME) VALUES('Высокий'),('Средний'),('Низкий');

-------------------------------------------
-- при входе в системму нужно знать/ получить (USER_ID, USER_NICKNAME, USER_ROLE_ID
-- при работе с рисками задачами проекта нужно на каждом шаге знать PROJECT_ID выбранного объекта проекта
 
INSERT INTO users(USER_NAME,USER_SURNAME,USER_PATRONYMIC,USER_PASSWORD,USER_PHONE,USER_EMAIL,USER_NICKNAME,USER_ROLE_ID) VALUES
('Илья','Алейчик','Дмитриевич','Mic20207','+375291510636','ilya.alejchik0@gmail.com','admin','1'),
('Илья','Алейчик','Дмитриевич','Mic20207','+375333343156','ilya.alejchik@outlook.com','aicgtrade',DEFAULT);


INSERT INTO activity(LOG_TIME,LOG_DATE,LOG_USER_IP,LOG_USER_ID) VALUES 
(CURRENT_TIME,CURRENT_DATE,'127.0.0.1','2');


INSERT INTO projects(PROJECT_DATE_CREATION,PROJECT_TIME_CREATION,PROJECT_TERM_DELIVERY,PROJECT_COST_DELIVERY,PROJECT_NAME,PROJECT_DESCRIPTION,PROJECT_CURRENCY_ID,PROJECT_USER_ID) VALUES 
(CURRENT_DATE, CURRENT_TIME, '2020-12-22',25.0,'APM Series','Система управлеия производительностью приложений','1','2');


INSERT INTO tasks(TASK_NAME,TASK_NOTE,TASK_STATE_ID,TASK_PRIORITY_ID,TASK_PROJECT_ID) VALUES 
('Форма','Сделать функцию GetForm() для получени формы','1','3','1');


INSERT INTO risks(RISK_LOSS_INCOME,RISK_LOSS_TIME_INDAY,RISK_NAME,RISK_AVAILABILITY_ID,RISK_PROJECT_ID) VALUES
('0','30','Отсутствие согласованных требования Заказчика к системе','2','1');
	
