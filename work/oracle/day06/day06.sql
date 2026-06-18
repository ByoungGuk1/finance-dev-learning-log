SELECT E.first_name, E.last_name, E.salary, D.department_name, J.job_title
FROM employees E
JOIN departments D ON E.department_id = D.department_id
JOIN jobs J ON E.job_id = J.job_id
WHERE D.department_id = 60;