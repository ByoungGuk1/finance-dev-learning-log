--실습 과제 8-1  |  INNER JOIN 연습 (★★☆  응용)
--① EMPLOYEES와 DEPARTMENTS를 조인하여 직원명, 부서명, 급여를 조회하세요.
--   부서 80번(Sales) 직원만 대상으로, 급여 내림차순 정렬하세요.
SELECT e.first_name || ' ' || e.last_name AS "직원명", d.department_name AS "부서명", e.salary AS "급여"
FROM EMPLOYEES e
JOIN DEPARTMENTS d ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
WHERE e.department_id IN (80)
ORDER BY e.salary DESC;

--② EMPLOYEES와 JOBS를 조인하여 직원명, 직무제목(JOB_TITLE), 최소급여, 최대급여, 실제급여를 조회하세요.
--   실제급여가 해당 직무 최대급여의 90% 이상인 직원만 출력하세요.
SELECT e.first_name || ' ' || e.last_name AS "직원명", j.job_title AS "직무명", j.min_salary AS "최소급여", j.max_salary AS "최대급여", e.salary AS "실제 급여"
FROM EMPLOYEES e
JOIN JOBS j ON e.job_id = j.job_id
WHERE e.salary >= (j.max_salary * 0.9);




--실습 과제 8-2  |  OUTER JOIN + SELF JOIN (★★☆  응용)
--① LEFT JOIN을 사용하여 부서가 없는 직원도 포함한 전체 직원 목록을 출력하세요.
SELECT *
FROM employees e
LEFT JOIN departments d ON e.department_id = d.department_id; 

--② 직원수가 0인 부서(직원이 없는 부서)만 출력하는 SQL을 작성하세요.
--   (힌트: RIGHT JOIN 또는 LEFT JOIN + WHERE e.employee_id IS NULL)
SELECT d.*
FROM employees e
RIGHT JOIN departments d ON e.department_id = d.department_id
WHERE e.employee_id IS NULL;

--③ SELF JOIN으로 각 직원과 그들의 매니저 이름을 조회하고,
--   매니저 급여보다 본인 급여가 더 높은 직원을 찾으세요.
SELECT oe.first_name || ' ' || oe.last_name AS "본인 이름", je.first_name || ' ' || je.last_name AS "매니저 이름"
FROM employees oe
JOIN employees je ON oe.manager_id = je.employee_id
WHERE oe.salary > je.salary;





--실습 과제 8-3  |  다중 조인 종합 (★★★  심화)
--아래 요구사항에 맞는 '금융 본부 직원 현황 보고서'를 작성하세요:
--
--  요구사항:
--  • 부서번호 100(Finance)과 110(Accounting) 소속 직원 전체
--  • 출력: 직원명, 직무제목(JOB_TITLE), 부서명, 도시, 국가명,
--           급여, 커미션(NVL처리), 총수입(salary + salary*NVL(comm,0))
--  • 매니저 이름도 함께 출력 (SELF JOIN 활용)
--  • 총수입 내림차순 정렬

SELECT e.first_name || ' ' || e.last_name AS "직원명",
    j.job_title AS "직무 명",
    d.department_name AS "부서명",
    l.city AS "도시",
    c.country_name AS "국가명",
    e.salary AS "급여",
    NVL(e.commission_pct, 0) AS "커미션",
    (e.salary + e.salary * NVL(e.commission_pct, 0)) AS "총수입",
    je.first_name || ' ' || je.last_name AS "매니저 명"
FROM employees e
JOIN JOBS j ON e.job_id = j.job_id
JOIN departments d ON e.department_id = d.department_id
JOIN locations l ON d.location_id = l.location_id
JOIN countries c ON l.country_id = c.country_id
JOIN employees je ON e.manager_id = je.employee_id
WHERE d.department_id IN (100, 110)
ORDER BY (e.salary + e.salary * NVL(e.commission_pct, 0)) DESC;
