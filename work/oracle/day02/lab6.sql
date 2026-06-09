--========================================
--		SubQuery
--========================================
--
--
--1. 직원들의 이름, 입사일, 부서명을 조회하시오.
--단, 부서가 없는 직원이 있다면 그 직원정보도 출력결과에 포함시킨다.
--그리고 부서가 없는 직원에 대해서는 '<부서없음>' 이 출력되도록 한다.
--(outer-join , nvl() )
SELECT e.first_name, e.hire_date, NVL(d.department_name, '<부서없음>')
FROM employees e
LEFT JOIN departments d ON e.department_id = d.department_id; 



--2. 직원의 직책에 따라 월급을 다르게 지급하려고 한다.
--직책에 'Manager'가 포함된 직원은 급여에 0.5를 곱하고
--나머지 직원들에 대해서는 원래의 급여를 지급하도록 한다. 
--적절하게 조회하시오. (decode)
SELECT e.first_name, 
    (
        CASE 
            WHEN LOWER(j.job_title) LIKE '%manager%' THEN e.salary + e.salary * 0.5
            ELSE e.salary
        END
    ) AS "급여"
FROM employees e
JOIN jobs j ON e.job_id = j.job_id;




--3. 각 부서별로 최저급여를 받는 직원의 이름과 부서id, 급여를 조회하시오.
SELECT m.first_name, m.department_id, m.salary
FROM employees m
JOIN (
    SELECT department_id, MIN(salary) AS "min_salary"
    FROM employees
    GROUP BY department_id
)e ON m.department_id = e.department_id 
WHERE m.salary = e."min_salary";




--4. 각 직급별(job_title) 인원수를 조회하되 사용되지 않은 직급이 있다면 해당 직급도
--출력결과에 포함시키시오. 그리고 직급별 인원수가 3명 이상인 직급만 출력결과에 포함시키시오.
SELECT job_title, COUNT(e.employee_id)
FROM employees e
RIGHT JOIN jobs j ON e.job_id = j.job_id
GROUP BY job_title
HAVING COUNT(e.employee_id) >= 3;





--5. 각 부서별 최대급여를 받는 직원의 이름, 부서명, 급여를 조회하시오.
SELECT e.first_name, d.department_name, e.salary
FROM employees e
JOIN departments d ON e.department_id = d.department_id
JOIN (
    SELECT MAX(salary) AS MAX_SALARY, department_id
    FROM employees
    GROUP BY department_id
)j ON j.MAX_SALARY = e.salary
WHERE j.department_id = e.department_id;

--6. 직원의 이름, 부서id, 급여를 조회하시오. 그리고 직원이 속한 해당 부서의 
--최소급여를 마지막에 포함시켜 출력 하시오.
SELECT e.first_name, e.department_id, e.salary, d."해당 부서의 최소급여"
FROM employees e
LEFT JOIN (
    SELECT MIN(SALARY) AS "해당 부서의 최소급여", department_id
    FROM employees e
    GROUP BY department_id
    ) d ON e.department_id = d.department_id;


