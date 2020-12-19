--test view with math function
CREATE VIEW projects AS SELECT
PROJECT_DATE_CREATION,
PROJECT_TIME_CREATION,
PROJECT_TERM_DELIVERY,
PROJECT_COST_DELIVERY,
PROJECT_NAME,
PROJECT_DESCRIPTION,
PROJECT_COST_DELIVERY * currencies.CURRENCY_EXCHANGE_RATE AS BYN
FROM projects 
INNER JOIN currencies ON currencies.CURRENCY_ID = projects.PROJECT_CURRENCY_ID 
GROUP BY PROJECT_DATE_CREATION, PROJECT_TIME_CREATION,PROJECT_TERM_DELIVERY,PROJECT_COST_DELIVERY,PROJECT_NAME,PROJECT_DESCRIPTION,currencies.CURRENCY_EXCHANGE_RATE;
 ------------------------
  
 DROP VIEW IF EXISTS projects_view
  
 CREATE VIEW projects_view AS SELECT 
tasks.task_name,
projects.project_id,
projects.project_date_creation,
projects.project_time_creation,
projects.project_term_delivery,
projects.project_cost_delivery,
projects.project_name,
projects.project_description,
currencies.currency_exchange_rate,
currencies.currency_name,
projects.project_user_id,
(SELECT CAST((SELECT COUNT(*) FROM tasks WHERE task_state_id = 5) AS DEC(12,4) ) / (SELECT COUNT(*) FROM tasks) * 100 AS "finish_proceed")
FROM tasks 
INNER JOIN projects ON projects.project_id = tasks.task_project_id 
INNER JOIN currencies ON currencies.currency_id = projects.project_currency_id 
--WHERE projects.project_user_id = 2
GROUP BY projects.project_id,
projects.project_date_creation,
projects.project_time_creation,
projects.project_term_delivery,
projects.project_cost_delivery,
projects.project_name,
projects.project_description,
projects.project_currency_id,
projects.project_user_id,
currencies.currency_exchange_rate,
currencies.currency_name,
tasks.task_name






CREATE VIEW risks AS SELECT
RISK_LOSS_INCOME,
RISK_LOSS_TIME_INDAY,
RISK_NAME,
RISK_AVAILABILITY_ID
FROM risks

CREATE VIEW tasks AS SELECT
TASK_NAME,
TASK_NOTE,
TASK_STATE_ID,
TASK_PRIORITY_ID
FROM tasks

CREATE VIEW users AS SELECT
USER_NAME,
USER_SURNAME,
USER_PATRONYMIC,
USER_PASSWORD,
USER_IMG,
USER_PHONE,
USER_EMAIL
FROM users




















