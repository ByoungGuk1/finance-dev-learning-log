--========================================
--		SubQuery
--========================================
--1. 'IT'부서에서 근무하는 직원들의 이름, 급여, 입사일을 조회하시오.
SELECT employees.first_name, employees.salary, employees.hire_date
FROM employees
JOIN departments ON employees.department_id = departments.department_id
WHERE departments.department_name LIKE 'IT';


--2. 'Alexander' 와 같은 부서에서 근무하는 직원의 이름과 부서id를 조회하시오.
SELECT employees.first_name, employees.department_id
FROM employees
WHERE department_id IN (
    SELECT department_id
    FROM employees
    WHERE employees.first_name = 'Alexander'
    );


--3. 80번부서의 평균급여보다 많은 급여를 받는 직원의 이름, 부서id, 급여를 조회하시오.
SELECT employees.first_name, employees.department_id, employees.salary
FROM employees
WHERE salary > (
    SELECT AVG(salary)
    FROM employees
    WHERE department_id = 80
    );



--4. 'South San Francisco'에 근무하는 직원의 최소급여보다 급여를 많이 받으면서 
--50 번부서의 평균급여보다 많은 급여를 받는 직원의 이름, 급여, 부서명, 
--부서id를 조회하시오.
SELECT te.first_name, te.salary, d.department_name, d.department_id
FROM employees te
JOIN departments d ON te.department_id = d.department_id
WHERE te.salary > (
    SELECT MIN(e.salary)
    FROM employees e
    JOIN departments d ON e.department_id = d.department_id
    JOIN locations l ON d.location_id = l.location_id
    WHERE l.city = 'South San Francisco'
    ) 
    AND te.salary > (
        SELECT AVG(salary)
        FROM employees
        WHERE department_id = 50
    );


-------------------scott/tiger (emp, dept)

--1. BLAKE와 동일한 부서에 속한 모든 사원의 이름및 입사일을 표시하는 질의를 작성하시오.
--결과에서 BLAKE는 제외시킵니다.

 


--2. 부서의 위치가 DALLAS인 모든 사원의 이름, 부서번호 , 직무를 표시하시오 
SELECT e.first_name, e.department_id, j.job_title, l.*, j.*, d.*
FROM employees e
JOIN jobs j ON e.job_id = j.job_id
JOIN departments d ON e.department_id = d.department_id
JOIN locations l ON d.location_id = l.location_id
WHERE l.street_address LIKE '%DALLAS%';


--3. KING에게 보고하는 모든 사원의 이름과 급여를 표시하는 질의를 작성하시오 
SELECT employees.first_name, employees.salary
FROM employees
WHERE manager_id IN (
    SELECT employee_id
    FROM employees
    WHERE last_name LIKE '%King%'
    );
 