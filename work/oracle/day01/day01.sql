select tname from tab order by tname desc;

select * from employees;

desc employees;

--

select * from employees order by employee_id desc;

select banner from v$version;

-- mysql:select getdate from table형식
-- oracle의 경우
select sysdate as "오늘 날짜"
from dual;

-- 비어있는 한줄짜리 테이블
desc dual;
select * from dual;

-- alias
select 1+1 as "덧셈"
from dual;

-- 갯수 제한
-- psudo calum (oracle 제한)
-- rownum : data를 가져와서 번호를 부여
select rownum, employees.*
from employees
where rownum <= 5;

--date 타입
select first_name, to_char(hire_date,'yyyy-mm-dd hh:mi:ss') from employees;

--
select * from user_tables;

select * from all_tables;

-- dba 권한 필요
select * from dba_tables;

select * from user_tab_columns;

---

--직원 정보, 번호, 이름, 급여, 연봉
select * from employees;
-- NVL(column name, 0) -> null인 경우 0으로 계산
select
    EMPLOYEE_ID,
    FIRST_NAME,
    SALARY AS "급여",
    COMMISSION_PCT,
    SALARY + (SALARY * NVL(COMMISSION_PCT, 0)) AS "급여2",
    SALARY * 12 AS "직원의 연봉",
    SALARY * 12 * 1.1 AS "연봉 1.1"
from employees;

select
    EMPLOYEE_ID,
    FIRST_NAME || ' ' || LAST_NAME AS "이름"
from employees;

--중복 제거
-- 부서 조회
SELECT *
FROM DEPARTMENTS;

--직원이 근무하는 부서의 정보
SELECT DISTINCT DEPARTMENT_ID
FROM EMPLOYEES;

--두개의 조합으로 중복 제거
SELECT DISTINCT DEPARTMENT_ID, JOB_ID
FROM EMPLOYEES
ORDER BY 1;

-- COLUMN의 순서로 작성해도 동작
SELECT *
FROM EMPLOYEES
ORDER BY 6 ASC;
--ORDER BY HIRE_DATE ASC;

SELECT DISTINCT DEPARTMENT_ID AS "123", JOB_ID
FROM EMPLOYEES
ORDER BY "123";

SELECT *
FROM employees
WHERE commission_pct IS NULL;

-- ORACLE 정책상 NULL은 최후순위
SELECT employee_id, first_name, salary, commission_pct
FROM EMPLOYEES
ORDER BY commission_pct NULLS FIRST;

SELECT employee_id, first_name, salary, commission_pct
FROM EMPLOYEES
ORDER BY commission_pct DESC NULLS LAST;

-- Oracle date 의 default 는 'RR/MM/DD'
-- 50 이상이면 1900년대
-- 50 미만이면 2000년대
SELECT
    employee_id,
    first_name || ' ' || last_name AS "이름",
    hire_date,
    department_id
FROM employees
WHERE hire_date >= TO_DATE('2005-01-01', 'YYYY-MM-DD')
ORDER BY hire_date;

SELECT
    employee_id as id,
    first_name || ' ' || last_name AS "이름",
    hire_date,
    department_id,
    salary
FROM employees
WHERE salary >= '10000'
ORDER BY salary;

SELECT
    employee_id as id,
    first_name || ' ' || last_name AS "이름",
    hire_date,
    department_id,
    salary
FROM employees
WHERE salary <> 2100
ORDER BY salary;

-- 급여가 1500 이상 2200 이하인 직원 조회
SELECT
    employee_id as id,
    first_name || ' ' || last_name AS "이름",
    hire_date,
    department_id,
    salary
FROM employees
WHERE salary BETWEEN 1500 AND 2200
ORDER BY salary;

-- 부서 ID가 60,90,100 인 직원 조회
SELECT
    employee_id as id,
    first_name || ' ' || last_name AS "이름",
    hire_date,
    department_id,
    salary
FROM employees
WHERE department_id IN (60,90,100)
ORDER BY department_id;

-- 부서 ID가 60,90,100 인 직원을 제외하고 조회
SELECT
    employee_id as id,
    first_name || ' ' || last_name AS "이름",
    hire_date,
    department_id,
    salary
FROM employees
WHERE department_id NOT IN (60,90,100)
ORDER BY department_id;

SELECT
    employee_id as id,
    first_name || ' ' || last_name AS "이름",
    hire_date,
    department_id,
    salary
FROM employees
WHERE department_id NOT IN (60,90) OR department_id IS NULL
ORDER BY department_id NULLS FIRST;
