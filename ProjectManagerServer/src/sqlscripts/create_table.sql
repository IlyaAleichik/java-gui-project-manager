-- Users Control and Activity Block

DROP TABLE IF EXISTS type_emails,type_roles,users,activity_log;
CREATE TABLE type_roles(
role_id SERIAL PRIMARY KEY,
role_name VARCHAR(25)
);
INSERT INTO type_roles (role_name) VALUES('Администратор'),('Модератор'),('Пользователь');
SELECT * FROM type_roles;


CREATE TABLE users(
user_id SERIAL PRIMARY KEY,
user_nickname VARCHAR(25) UNIQUE NOT NULL,
user_name VARCHAR(25),
user_surname VARCHAR(25),
user_patronymic VARCHAR(25),
user_password VARCHAR(25) NOT NULL,
user_img VARCHAR(128),
user_phone VARCHAR(100),
user_email VARCHAR(254) NOT NULL,
-------------------------FK   
user_typerole_id INT REFERENCES type_roles(role_id) DEFAULT 3);

INSERT INTO users (user_type_role_id,user_nickname,user_name,user_surname,user_patronymic,user_password,user_img,user_phone,user_email) VALUES 
(3,'BriannaWare','Althea','Ayala','Ramos','eratgfdtr32432','nisl sem','67-453-69-96','vulputate.risus.a@hendrerit.com'),
(2,'IndigoPearson','Curran','Wilder','Roman','323214frrewq','enim non nisi. Aenean eget. In nec orci. Donec','17-629-96-46','in.aliquet.lobortis@Mauris.edu'),
(2,'CadeTBeasley','Freya','Bates','Stafford','quamfsdgfreagr3241','cade1215433','+375-76-836-53-42','amet.massa@sagittisplaceratCras.ca'),
(2,'StellaWFranklin','Imogene','Contreras','Talley','et32413324','musueac','+375-46-462-03-07','nec.ligula.consectetuer@Phasellus.com'),
(3,'IshmaelFlynn','Cade','Odonnell','Parrish','enim32433','molestiet','30-574-25-77','magna.Lorem@disparturientmontes.co.uk'),
(2,'AxelMclaughlin','Lisandra','Hanson','Riley','Suspendisse324','laoreet ipsum','+375-76-414-53-12','arcu.Vestibulum.ut@Crasinterdum.org'),
(2,'GarrisonLane','Alvin','Clark','Knowles','@Cras4325','sociis','+375-58-801-30-57','gravida.nunc.sed@auguescelerisquemollis.net'),
(3,'JanaMPate','Lana','Sheppard','Browning','porttitor5455','metus','96-136-23-27','Sed.nunc.est@elita.co.uk');

INSERT INTO users (user_nickname,user_name,user_surname,user_patronymic,user_password,user_img,user_phone,user_email)
VALUES ('AiCGtrade','Илья','Алейчик','Дмитриевич','Mic20208','C:\User\Image\ava.jpg','+375-33-334-31-56','ilya.alejchik@outlook.com');

SELECT * FROM users ORDER BY users.user_id DESC;


DROP VIEW IF EXISTS users_view;
CREATE VIEW users_view AS
SELECT users.user_nickname AS "Уникальное имя", users.user_name AS "Имя", users.user_surname AS "Фамилия",
users.user_patronymic AS "Отчество",
users.user_password AS "Пароль",
users.user_img AS "Изображение",
users.user_phone AS "Телефон",
users.user_email AS "Эл.почта",
type_roles.role_name AS "Права доступа"
FROM users INNER JOIN type_roles ON users.user_type_role_id = type_roles.role_id;


CREATE TABLE activity_log( 
log_id SERIAL PRIMARY KEY,
log_user_ip CIDR,
log_time TIME,
log_date DATE,

-------------------------FK
log_user_id INT REFERENCES users(user_id)
);
INSERT INTO activity_log (log_user_id,log_user_ip,log_time,log_date) VALUES ('9','127.0.0.1',CURRENT_TIME, CURRENT_DATE);
SELECT
user_id,
users.user_nickname,
users.user_email,
type_roles.role_name,
log_user_ip,
log_time,
log_date
FROM activity_log INNER JOIN users ON users.user_id = activity_log.log_user_id 
INNER JOIN type_roles ON type_roles.role_id = users.user_type_role_id;

DROP VIEW IF EXISTS activity_log_view;
CREATE VIEW activity_log_view AS
SELECT
users.user_nickname,
users.user_email,
type_roles.role_name,
log_user_ip,
log_time,
log_date
FROM activity_log INNER JOIN users ON users.user_id = activity_log.log_user_id 
INNER JOIN type_roles ON type_roles.role_id = users.user_type_role_id;



-- Management and Finances Project Block

CREATE TABLE currencies(
currency_id SERIAL,
currency_name
);

--Projects Control
CREATE TABLE projects(
project_id SERIAL,
project_name,
project_description,
project_owner,
project_term_delivery,
project_cost_delivery,

-------------------------FK
project_currency_id
);

-- Task Project and State Block


CREATE TABLE states_tasks(
state_id SERIAL,
state_name
);

CREATE TABLE priority_tasks(
priority_id SERIAL,
prioroty_name
);

CREATE TABLE file_tasks(
file_id SERIAL,
file_to_path
);

CREATE TABLE tasks(
task_id SERIAL
task_name,
task_note,

-------------------------FK
task_priority_id,
task_state_id,
task_file_id
);


