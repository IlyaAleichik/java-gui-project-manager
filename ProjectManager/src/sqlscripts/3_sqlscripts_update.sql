-- UPDATE activity SET WHERE  not need to update ACTIVITY

UPDATE projects SET 
PROJECT_DATE_CREATION = CURRENT_DATE, 
PROJECT_TIME_CREATION = CURRENT_TIME,
PROJECT_TERM_DELIVERY,
PROJECT_COST_DELIVERY,
PROJECT_NAME,
PROJECT_DESCRIPTION,
PROJECT_CURRENCY_ID 
WHERE PROJECT_ID = 1;
  
UPDATE risks SET 
RISK_LOSS_INCOME,
RISK_LOSS_TIME_INDAY,
RISK_NAME,
RISK_AVAILABILITY_ID,
WHERE RISK_ID = 1;

UPDATE tasks SET 
TASK_NAME,
TASK_NOTE,
TASK_STATE_ID,
TASK_PRIORITY_ID,
WHERE TASK_ID = 1;

UPDATE users SET 
USER_NAME,
USER_SURNAME,
USER_PATRONYMIC,
USER_PASSWORD,
USER_IMG,
USER_PHONE,
USER_EMAIL,
WHERE USER_ID = 1;



