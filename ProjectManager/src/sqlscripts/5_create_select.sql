--CATEGORY------------------------------------------------
SELECT * FROM roles;
SELECT * FROM availability_risk;
SELECT * FROM currencies;
SELECT * FROM states_tasks;
SELECT * FROM priority_tasks;

----------------------------------------------------------

SELECT * FROM activity;
SELECT * FROM projects;
SELECT * FROM risks;
SELECT * FROM tasks;
SELECT * FROM users;

SELECT * FROM users WHERE (user_nickname = 'aicgtrade' OR user_email = '') AND user_password = 'Mic20207';
SELECT * FROM projects_view WHERE project_user_id = 2
SELECT * FROM risks_view WHERE project_id = '1' AND project_user_id = '2'
SELECT * FROM tasks_view WHERE project_id = '' AND project_user_id = ''






