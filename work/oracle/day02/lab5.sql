-- SELF JOIN
--1. 직원의 이름과 관리자 이름을 조회하시오.
SELECT oe.first_name, je.first_name
FROM employees oe
JOIN employees je ON oe.manager_id = je.employee_id;



--2. 직원의 이름과 관리자 이름을 조회하시오.
--관리자가 없는 직원정보도 모두 출력하시오.
SELECT oe.first_name, je.first_name
FROM employees oe
LEFT JOIN employees je ON oe.manager_id = je.employee_id;
 



--3. 관리자 이름과 관리자가 관리하는 직원의 수를 조회하시오.
--단, 관리직원수가 3명 이상인 관리자만 출력되도록 하시오.
SELECT e.first_name AS "관리자 이름", sub."SUB_COUNT" AS "관리직원수"
FROM employees e
JOIN (
SELECT count(*) AS "SUB_COUNT", manager_id
FROM employees m
GROUP BY manager_id
HAVING COUNT(*) >= 3) sub ON e.employee_id = sub.manager_id;
