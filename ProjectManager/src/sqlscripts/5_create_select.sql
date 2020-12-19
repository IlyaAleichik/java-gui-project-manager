--CATEGORY------------------------------------------------
SELECT * FROM roles;
SELECT * FROM availability_risk;
SELECT * FROM currencies;
SELECT * FROM states_tasks;
SELECT * FROM priority_tasks;

SELECT ROLE_NAME FROM roles;
SELECT AVAILABILITY_NAME FROM availability_risk;
SELECT CURRENCY_EXCHANGE_RATE,CURRENCY_NAME FROM currencies;
SELECT STATE_NAME FROM states_tasks;
SELECT PRIOROTY_NAME FROM priority_tasks;
----------------------------------------------------------

SELECT * FROM activity;
SELECT * FROM projects;
SELECT * FROM risks;
SELECT * FROM tasks;
SELECT * FROM users;


SELECT * FROM users WHERE (user_nickname = 'aicgtrade' OR user_email = '') AND user_password = 'vdfv';


SELECT * FROM users WHERE (user_nickname = 'aicgtrade' OR user_email = '') AND user_password = 'Mic20207';


SELECT * FROM projects_view WHERE project_user_id = '2'

SELECT * FROM projects WHERE project_user_id = '2'

SELECT COUNT(*) FROM tasks






