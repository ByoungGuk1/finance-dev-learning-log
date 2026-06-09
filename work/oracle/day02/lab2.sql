--========================================
--		단일행 함수
--========================================
--
--1. 이름이 'adam' 인 직원의 급여와 입사일을 조회하시오.
SELECT salary, hire_date
FROM employees
WHERE LOWER(first_name) LIKE 'adam';


--2. 나라 명이 'united states of america' 인 나라의 국가 코드를 조회하시오.
SELECT country_id
FROM countries
WHERE LOWER(country_name) = 'united states of america';



--3. 'Adam의 입사일은 95/11/02 이고, 급여는 7000 입니다.' 이런 식으로 직원
--정보를 조회하시오.
SELECT first_name || '의 입사일은 ' || TO_CHAR(hire_date,'YY/MM/DD') || '이고, 급여는 ' || salary || '입니다.' AS "결과"
FROM employees;



--4. 이름이 5글자 이하인 직원들의 이름, 급여, 입사일을 조회하시오.
SELECT first_name, salary, hire_date
FROM employees
WHERE LENGTH(first_name) <= 5;



--5.2006년도에 입사한 직원의 이름, 입사일을 조회하시오.
SELECT first_name, hire_date
FROM employees
WHERE TO_CHAR(hire_date, 'YYYY') = 2006;



--6. 7년 이상 장기 근속한 직원들의 이름, 입사일, 급여, ***근무년차***를 조회하시오.
SELECT first_name,
       hire_date,
       salary,
       TRUNC(MONTHS_BETWEEN(SYSDATE, hire_date) / 12) AS "근무년차"
FROM employees
WHERE TRUNC(MONTHS_BETWEEN(SYSDATE, hire_date) / 12) >= 7;

--=============Group 함수==================

--7. 각 부서별 인원수를 조회하되 인원수가 5명 이상인 부서만 출력되도록 하시오.
SELECT department_id, COUNT(*)
FROM employees
GROUP BY department_id
HAVING COUNT(*) >= 5;



--8. 각 부서별 최대급여와 최소급여를 조회하시오.
--   단, 최대급여와 최소급여가 같은 부서는 직원이 한명일 가능성이 높기때문에 
--   조회결과에서 제외시킨다.
SELECT department_id, MAX(salary), MIN(salary)
FROM employees
GROUP BY department_id
HAVING COUNT(*) <> 1;



--9. 부서가 50, 80, 110 번인 직원들 중에서 급여를 5000 이상 24000 이하를 받는
--   직원들을 대상으로 부서별 평균 급여를 조회하시오.
--   다, 평균급여가 8000 이상인 부서만 출력되어야 하며, 출력결과를 평균급여가 높은
--   부서면저 출력되도록 해야 한다.
SELECT department_id, AVG(salary)
FROM employees
WHERE department_id IN (50, 80, 110)
    AND salary BETWEEN 5000 AND 24000
GROUP BY department_id
HAVING AVG(salary) >= 8000
ORDER BY AVG(salary) DESC;