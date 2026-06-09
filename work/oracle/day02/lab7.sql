--==========================================
--	Inline View 와 Top-N SubQuery
--==========================================
--
--1. 급여를 가장 많이 받는 상위 5명의 직원 정보를 조회하시오.
SELECT *
FROM(
    SELECT *
    FROM employees
    ORDER BY salary DESC
)
WHERE ROWNUM <= 5;


--2. 커미션을 가장 많이 받는 상위 3명의 직원 정보를 조회하시오.
SELECT *
FROM(
    SELECT *
    FROM employees
    ORDER BY commission_pct DESC NULLS LAST
)
WHERE ROWNUM <= 3;


--3. 월별 입사자 수를 조회하되, 입사자 수가 5명 이상인 월만 출력하시오.
SELECT TO_CHAR(hire_date, 'MM') AS "월", COUNT(*)
FROM employees
GROUP BY TO_CHAR(hire_date, 'MM')
HAVING COUNT(*) >= 5
ORDER BY TO_CHAR(hire_date, 'MM');


--4. 년도별 입사자 수를 조회하시오. 
--단, 입사자수가 많은 년도부터 출력되도록 합니다.
SELECT TO_CHAR(hire_date, 'YYYY') AS "년도", COUNT(*) AS "입사자 수"
FROM employees
GROUP BY TO_CHAR(hire_date, 'YYYY')
ORDER BY COUNT(*) DESC;
