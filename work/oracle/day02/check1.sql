--실습 과제 7-1  |  집계 함수 기초 (★☆☆  기초)
--① EMPLOYEES 전체에서 직원 수, 최고급여, 최저급여, 평균급여, 급여합계를 한 번에 조회하세요.
SELECT COUNT(*) AS "직원 수", MAX(SALARY) AS "최고 급여", MIN(SALARY) AS "최저 급여", AVG(SALARY) AS "평균 급여" , SUM(SALARY) AS "급여 합계"
FROM employees;

--② JOBS 테이블에서 직무별 최소·최대 급여의 차이(max_salary - min_salary)를 구하고,
--   급여 범위가 가장 큰 직무 순으로 상위 5개를 출력하세요.
SELECT *
FROM(
    SELECT JOB_ID AS "직무 ID", MIN_SALARY AS "최소 급여", MAX_SALARY AS "최대 급여" , (max_salary - min_salary) AS "최대 급여-최소 급여"
    FROM JOBS
    ORDER BY (MAX_SALARY - MIN_SALARY) DESC
    )
WHERE ROWNUM <= 5;

--실습 과제 7-2  |  GROUP BY · HAVING (★★☆  응용)
--① 부서별 직원 수를 구하되, 직원이 5명 이상인 부서만 출력하세요.
SELECT department_id, COUNT(*)
FROM employees 
group by department_id
HAVING COUNT(*) >= 5
ORDER BY COUNT(*) DESC;

--② 직무(JOB_ID)별 평균 급여를 구하고, 평균 급여가 전체 평균보다 높은 직무만 출력하세요.
--   (힌트: HAVING AVG(salary) > (SELECT AVG(salary) FROM employees))
SELECT JOB_ID, AVG(SALARY) AS "평균 급여"
FROM EMPLOYEES
GROUP BY JOB_ID
HAVING AVG(salary) > (SELECT AVG(salary) FROM employees)
ORDER BY AVG(SALARY) DESC;

--③ 입사 연도별(EXTRACT(YEAR FROM hire_date)) 입사자 수를 조회하세요.
SELECT EXTRACT(YEAR FROM hire_date), COUNT(*)
FROM EMPLOYEES
GROUP BY EXTRACT(YEAR FROM hire_date)
ORDER BY EXTRACT(YEAR FROM hire_date);

--실습 과제 7-3  |  금융 도메인 응용 (★★★  심화)
--아래 요구사항에 맞는 '부서별 급여 분석 보고서'를 작성하세요:
--  조건: 커미션 있는 직원만 대상
--  출력: 부서번호, 직원수, 평균급여(반올림 정수), 커미션 포함 평균총수입,
--        최고총수입(salary+salary*commission_pct), 최저총수입
--  필터: 커미션 포함 평균총수입이 10000 이상인 부서만
--  정렬: 평균총수입 내림차순

SELECT department_id AS "부서번호",
    COUNT(*) AS "직원 수",
    ROUND(AVG(SALARY)) AS "평균 급여",
    ROUND(AVG(SALARY + NVL(commission_pct, 0) * SALARY)) AS "평균총수입",
    MAX(SALARY + NVL(commission_pct, 0) * SALARY) AS "최고 총 수입",
    MIN(SALARY + NVL(commission_pct, 0) * SALARY) AS "최저 총 수입"
FROM employees
WHERE commission_pct IS NOT NULL
group by department_id
HAVING AVG(SALARY + NVL(commission_pct, 0) * SALARY) >= 10000
ORDER BY ROUND(AVG(SALARY + NVL(commission_pct, 0) * SALARY)) DESC;
