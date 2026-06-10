--실습 과제 9-1  |  단일행·다중행 서브쿼리 (★★☆  응용)
--① 급여가 전체 평균 급여 이상이고 부서번호가 50인 직원을 조회하세요.
SELECT o.*
FROM employees o
WHERE o.salary >= (
    SELECT AVG(salary) AS "전체 평균 급여"
    FROM employees
) AND o.department_id = 50;

--② IT_PROG 직무의 최소 급여보다 급여가 낮은 직원을 조회하세요.
SELECT *
FROM employees
WHERE salary < (
    SELECT MIN(e.salary)
    FROM employees e
    WHERE e.job_id = 'IT_PROG'
);

--③ 부서 80번 직원들의 급여 중 어떤 것보다도 높은 급여를 받는 직원을 ALL로 조회하세요.
SELECT *
FROM employees
WHERE salary > ALL(
    SELECT salary
    FROM employees
    WHERE department_id = 80
);

--④ 각 부서에서 가장 먼저 입사한 직원(MIN hire_date)의 이름과 입사일을 조회하세요.
SELECT e.first_name, e.hire_date
FROM employees e
JOIN (
    SELECT MIN(hire_date) AS "MIN_HIRE_DATE", department_id
    FROM employees
    GROUP BY department_id
    ) j ON e.department_id = j.department_id AND e.hire_date = j."MIN_HIRE_DATE";
--
--
--실습 과제 9-2  |  인라인 뷰 · EXISTS (★★☆  응용)
--① 급여 기준 상위 10명의 직원 정보를 ROWNUM을 이용한 인라인 뷰로 조회하세요.
SELECT *
FROM(
    SELECT *
    FROM employees
    ORDER BY salary DESC
)
WHERE ROWNUM <= 10;

--② 직원이 3명 이상인 부서의 부서명과 직원 수를 GROUP BY로 조회하고,
--   같은 결과를 EXISTS를 활용해서도 작성해 보세요.
SELECT d.department_name, j."직원수"
FROM departments d
JOIN(
SELECT department_id , COUNT(department_id) AS "직원수"
FROM employees
WHERE department_id IS NOT NULL
GROUP BY department_id
HAVING COUNT(department_id) >= 3
) j ON d.department_id = j.department_id;

-- **
SELECT d.department_name,
       (
           SELECT COUNT(*)
           FROM employees e
           WHERE e.department_id = d.department_id
       ) AS "직원수"
FROM departments d
WHERE EXISTS (
    SELECT 1
    FROM employees e
    WHERE e.department_id = d.department_id
    GROUP BY e.department_id
    HAVING COUNT(*) >= 3
);


--③ 매니저 역할을 하는 직원만 조회하는 SQL을 IN과 EXISTS로 각각 작성하세요.
SELECT *
FROM employees e
WHERE e.employee_id IN (
    SELECT manager_id
    FROM employees
    WHERE manager_id IS NOT NULL
    GROUP BY manager_id
)
ORDER BY e.employee_id;

--**
SELECT *
FROM employees m
WHERE EXISTS (
    SELECT 1
    FROM employees e
    WHERE e.manager_id = m.employee_id
)
ORDER BY m.employee_id;

--
--
--실습 과제 9-3  |  상관 서브쿼리 · 종합 (★★★  심화)
--① 각 직무(JOB_ID)에서 평균 급여보다 높은 급여를 받는 직원을 조회하세요.
--   출력: 직원명, 직무ID, 급여, 해당 직무 평균급여'
SELECT e.first_name, e.job_id , e.salary, j."평균급여"
FROM employees e
JOIN (
    SELECT job_id, AVG(salary) AS "평균급여"
    FROM employees
    GROUP BY job_id
) j ON e.job_id = j.job_id
WHERE e.salary > j."평균급여";

--② 부서별 최고 급여자의 이름, 부서명, 급여를 조회하세요.
--   (힌트: 인라인 뷰에서 부서별 MAX(salary) 먼저 구하고 JOIN 활용)
SELECT e.first_name, d.department_name, e.salary
FROM employees e
JOIN(
    SELECT department_id, MAX(salary) AS "최고 급여"
    FROM employees
    GROUP BY department_id
    HAVING department_id IS NOT NULL
) j ON e.department_id = j.department_id
JOIN departments d ON j.department_id = d.department_id 
WHERE e.salary = j."최고 급여"; 

--③ 자신의 매니저보다 급여가 높은 직원을 상관 서브쿼리로 찾으세요.
SELECT *
FROM employees m
WHERE m.salary > (
    SELECT salary
    FROM employees e
    WHERE e.employee_id = m.manager_id
);