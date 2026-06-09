--========================================
--		SELECT 기본
--========================================

--1. 급여가 15000 이상인 직원들의 이름, 급여, 부서id를 조회하시오.
SELECT first_name, salary, department_id
FROM employees
WHERE salary >= 15000;


--2. 직원 중에서 연봉이 170000 이상인 직원들의 이름, 연봉을 조회하시오.
--   연봉은 급여(salary)에 12를 곱한 값입니다.
SELECT first_name, salary * 12 AS "연봉"
FROM employees
WHERE salary * 12 >= 170000;



--3. 직원 중에서 부서id가 없는 직원의 이름과 급여를 조회하시오.
SELECT first_name, salary
FROM employees
WHERE department_id IS NULL;



--4. 2004년 이전에 입사한 직원의 이름, 급여, 입사일을 조회하시오.
SELECT first_name, salary, hire_date
FROM employees
WHERE hire_date < TO_DATE(2004, 'YYYY');



-- 논리연산자 -- 
--1. 80, 50 번 부서에 속해있으면서 급여가 13000 이상인 직원의 이름, 급여, 부서id
--를 조회하시오.
SELECT first_name, salary, department_id
FROM employees
WHERE department_id IN (80,50)
    AND salary >= 13000;




--2. 2005년 이후에 입사한 직원들 중에서 급여가 1300 이상 20000 이하인 직원들의 
--이름, 급여, 부서id, 입사일을 조회하시오.
SELECT first_name, salary, department_id, hire_date
FROM employees
WHERE salary BETWEEN 1300 AND 20000
    AND hire_date > TO_DATE(2005,'YYYY');




-- SQL 비교연산자 --
--1. 80, 50 번 부서에 속해있으면서 급여가 13000 이상인 직원의 이름, 급여, 부서id
--를 조회하시오.
SELECT first_name, salary, department_id
FROM employees
WHERE department_id IN (80,50)
    AND salary >= 13000;


--2. 2005년 이후에 입사한 직원들 중에서 급여가 1300 이상 30000 이하인 직원들의 
--이름, 급여, 부서id, 입사일을 조회하시오.
SELECT first_name, salary, department_id, hire_date
FROM employees
WHERE hire_date > TO_DATE(2005,'YYYY')
    AND salary BETWEEN 1300 AND 30000;



--3. 2005년도 입사한 직원의 정보만 출력하시오.
SELECT *
FROM EMPLOYEES
WHERE TO_CHAR(hire_date,'YYYY') = 2005;




--4. 이름이 D로 시작하는 직원의 이름, 급여, 입사일을 조회하시오.
SELECT first_name, salary, hire_date
FROM employees
WHERE first_name LIKE 'D%';



--5. 12월에 입사한 직원의 이름, 급여, 입사일을 조회하시오.
SELECT first_name, salary, hire_date
FROM employees
WHERE TO_CHAR(hire_date,'MM') = 12;



--6. 이름에 le 가 들어간 직원의 이름, 급여, 입사일을 조회하시오.
SELECT first_name, salary, hire_date
FROM employees
WHERE first_name LIKE '%le%'
    OR first_name LIKE '%le'
    OR first_name LIKE 'le%';




--7. 이름이 m으로 끝나는 직원의 이름, 급여, 입사일을 조회하시오.
SELECT first_name, salary, hire_date
FROM employees
WHERE first_name LIKE '%m';



--8. 이름의 세번째 글자가 r인 이름, 급여, 입사일을 조회하시오.
SELECT first_name, salary, department_id, hire_date
FROM employees
WHERE first_name LIKE '__r%'
    OR first_name LIKE '__r';



--9. 커미션을 받는 직원의 이름, 커미션, 급여를 조회하시오.
SELECT first_name, commission_pct, salary
FROM employees
WHERE commission_pct IS NOT NULL;



--10. 커미션을 받지 않는 직원의 이름, 커미션, 급여를 조회하시오.
SELECT first_name, NVL(commission_pct, 0), salary
FROM employees
WHERE commission_pct IS NULL;



--11. 2000년대에 입사해서 30, 50, 80 번 부서에 속해있으면서, 
--급여를 5000 이상 17000 이하를 받는 직원을 조회하시오. 
--단, 커미션을 받지 않는 직원들은 검색 대상에서 제외시키며, 먼저 입사한 직원이 
--***먼저 출력되어야 하며 입사일이 같은 경우 급여가 많은 직원이 먼저 출력되록 하시오.***
SELECT *
FROM employees
WHERE TO_CHAR(hire_date, 'YYYY') >= 2000
    AND department_id IN (30, 50, 80)
    AND salary BETWEEN 5000 AND 17000
ORDER BY hire_date ASC, salary DESC;



