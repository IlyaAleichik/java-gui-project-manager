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





--USERS VIEW
SELECT * FROM users WHERE (user_nickname = 'aicgtrade' OR user_email = '') AND user_password = 'Mic20207';

--RISKS VIEW
SELECT
risk_id,
risk_name,
risk_loss_income,
risk_loss_time_inday,
projects.project_name,
projects.project_id,
projects.project_user_id
FROM risks
inner join availability_risk on availability_risk.availability_id = risk_availability_id
inner join projects on projects.project_id = risk_project_id

---TASKS VIEW
SELECT task_id,
task_name,
task_note,
states_tasks.state_name,
priority_tasks.prioroty_name,
projects.project_name,
projects.project_user_id
from tasks
inner join states_tasks on states_tasks.state_id = task_state_id
inner join priority_tasks on priority_tasks.priority_id = task_priority_id
inner join projects on projects.project_id = task_project_id
where project_name = 'APM Series' and project_user_id = '2'


SELECT COUNT(*) FROM tasks






