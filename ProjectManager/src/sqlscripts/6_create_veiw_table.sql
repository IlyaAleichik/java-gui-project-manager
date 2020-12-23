
  --POJECTS VIEW
 CREATE VIEW projects_view AS SELECT 
projects.project_id,
projects.project_date_creation,
projects.project_time_creation,
projects.project_term_delivery,
projects.project_cost_delivery,
projects.project_name,
projects.project_description,
currencies.currency_exchange_rate,
currencies.currency_name,
projects.project_user_id
FROM projects 
INNER JOIN currencies ON currencies.currency_id = projects.project_currency_id ;


--RISKS VIEW
CREATE VIEW risks_view AS SELECT
risk_id,
risk_name,
risk_loss_income,
risk_loss_time_inday,
projects.project_name,
availability_risk.availability_name,
availability_risk.availability_id,
projects.project_id,
projects.project_user_id
FROM risks
INNER JOIN availability_risk ON availability_risk.availability_id = risk_availability_id
INNER JOIN projects ON projects.project_id = risk_project_id;

---TASKS VIEW
CREATE VIEW tasks_view AS SELECT task_id,
task_name,
task_note,
states_tasks.state_name,
priority_tasks.prioroty_name,
projects.project_name,
states_tasks.state_id,
priority_tasks.priority_id,
projects.project_user_id,
projects.project_id
FROM tasks
INNER JOIN states_tasks ON states_tasks.state_id = task_state_id
INNER JOIN priority_tasks ON priority_tasks.priority_id = task_priority_id
INNER JOIN projects ON projects.project_id = task_project_id;


CREATE VIEW activity_log_view AS SELECT
log_time,
log_date,
log_user_ip,
users.user_email,
users.user_nickname,
roles.role_name
FROM activity
INNER JOIN users ON users.user_id = activity.log_user_id
INNER JOIN roles ON roles.role_id = users.user_role_id;





SELECT * FROM projects_view WHERE project_user_id = 2
SELECT * FROM risks_view WHERE project_id = '1' AND project_user_id = '2'
SELECT * FROM tasks_view WHERE project_user_id = 2 AND project_id = 1
SELECT * FROM activity_log_view WHERE user_nickname = 'aicgtrade'









---TASKS VIEW на проверку
CREATE VIEW tasks_view AS SELECT task_id,
task_name,
task_note,
states_tasks.state_name,
priority_tasks.prioroty_name,
projects.project_name,
states_tasks.state_id,
priority_tasks.priority_id,
projects.project_user_id,
projects.project_id
FROM tasks
INNER JOIN states_tasks ON states_tasks.state_id = task_state_id
INNER JOIN priority_tasks ON priority_tasks.priority_id = task_priority_id
INNER JOIN projects ON projects.project_id = task_project_id

-- SELECT * FROM tasks_view WHERE project_user_id = 2 AND project_id = 1












