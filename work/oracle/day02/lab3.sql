--========================================
--		JOIN
--========================================

--1.직원들의 이름과 직급명(job_title)을 조회하시오.
SELECT employees.first_name, jobs.job_title
FROM employees
JOIN jobs ON employees.job_id = jobs.job_id;


--2.부서이름과 부서가 속한 도시명(city)을 조회하시오.
SELECT d.department_name, l.city
FROM departments d
JOIN locations l ON d.location_id = l.location_id;


--3. 직원의 이름과 근무국가명을 조회하시오. (employees, departments, locations,countries)
SELECT e.first_name, c.country_name
FROM employees e
JOIN departments d ON e.department_id = d.department_id
JOIN locations l ON d.location_id = l.location_id
JOIN countries c ON l.country_id = c.country_id;


--4. 직책(job_title)이 'manager' 인 사람의 이름, 직책, 부서명을 조회하시오.
SELECT e.first_name, j.job_title, d.department_name
FROM employees e
JOIN jobs j ON e.job_id = j.job_id
JOIN departments d ON e.department_id = d.department_id
WHERE j.job_title LIKE '%'|| INITCAP('manager%'); 


--5. 직원들의 이름, 입사일, 부서명을 조회하시오.
SELECT e.first_name, e.hire_date, d.department_name
FROM employees e
JOIN departments d ON e.department_id = d.department_id;


--6. 직원들의 이름, 입사일, 부서명을 조회하시오.
--단, 부서가 없는 직원이 있다면 그 직원정보도 출력결과에 포함시킨다.
SELECT e.first_name, e.hire_date, d.department_name
FROM employees e
LEFT OUTER JOIN departments d ON e.department_id = d.department_id;


--7. 직원의 이름과 직책(job_title)을 출력하시오.
--단, 사용되지 않는 직책이 있다면 그 직책정보도 출력결과에 포함시키시오.
SELECT e.first_name, j.job_title
FROM employees e
RIGHT JOIN jobs j ON e.job_id = j.job_id;

INSERT INTO jobs
VALUES('play', '매일놀기', 20000, 30000);
COMMIT;

select JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY
from jobs;

UPDATE jobs SET max_salary = 50000 WHERE job_id LIKE 'play';
COMMIT;