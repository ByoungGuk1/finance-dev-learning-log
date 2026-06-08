--실습 과제 5-1  |  비교·논리 연산자 (★☆☆  기초)
--① EMPLOYEES에서 급여가 8000 이상 15000 이하인 직원을 조회하세요.
--   (BETWEEN 방식과 비교연산자 방식 두 가지 모두 작성)
--② 부서 번호가 20, 50, 80인 직원의 이름, 급여, 부서번호를 조회하세요.
--   (IN 방식과 OR 방식 두 가지 모두 작성)

select *
from EMPLOYEES
where SALARY between 8000 and 15000;

select *
from EMPLOYEES
where DEPARTMENT_ID in (20,50,80);


--실습 과제 5-2  |  LIKE · IS NULL (★★☆  응용)
--① 이름(LAST_NAME)이 'K'로 시작하는 직원을 조회하세요.
--② 이름(FIRST_NAME)에 'an'이 포함된 직원을 조회하세요 (대소문자 구분 주의).
--③ 매니저(MANAGER_ID)가 없는 직원을 조회하세요.
--④ EMAIL 이 5글자인 직원을 조회하세요. (LIKE + 언더바 5개)

--① 이름(LAST_NAME)이 'K'로 시작하는 직원을 조회하세요.
select LAST_NAME || ' ' || FIRST_NAME as "이름"
from EMPLOYEES
where LAST_NAME like 'K%';

--② 이름(FIRST_NAME)에 'an'이 포함된 직원을 조회하세요 (대소문자 구분 주의).
select LAST_NAME || ' ' || FIRST_NAME as "이름"
from EMPLOYEES
where FIRST_NAME like '%an%';

--③ 매니저(MANAGER_ID)가 없는 직원을 조회하세요.
select LAST_NAME || ' ' || FIRST_NAME as "이름", MANAGER_ID
from EMPLOYEES
where MANAGER_ID is null;

--④ EMAIL 이 5글자인 직원을 조회하세요. (LIKE + 언더바 5개)
select LAST_NAME || ' ' || FIRST_NAME as "이름", EMAIL as "이메일"
from EMPLOYEES
where EMAIL like '_____';


--실습 과제 5-3  |  복합 조건 (★★☆  응용)
--① 급여가 5000 이상이고 커미션(COMMISSION_PCT)이 있는 직원을 조회하세요.
--② 부서 90 또는 100에 속하며 급여가 10000 이상인 직원을 조회하세요.
--③ 2000년~2003년 사이에 입사한 직원 중 급여가 8000 이상인 직원을 조회하세요.

--① 급여가 5000 이상이고 커미션(COMMISSION_PCT)이 있는 직원을 조회하세요.
select
    LAST_NAME || ' ' || FIRST_NAME as "이름", EMAIL as "이메일",
    SALARY,
    COMMISSION_PCT
from EMPLOYEES
where SALARY >= 5000 and COMMISSION_PCT is not null;

--② 부서 90 또는 100에 속하며 급여가 10000 이상인 직원을 조회하세요.
select
    LAST_NAME || ' ' || FIRST_NAME as "이름",
    SALARY,
    DEPARTMENT_ID
from EMPLOYEES
where DEPARTMENT_ID in (90,100) and SALARY >= 10000;

--③ 2000년~2003년 사이에 입사한 직원 중 급여가 8000 이상인 직원을 조회하세요.
select
    LAST_NAME || ' ' || FIRST_NAME as "이름", EMAIL as "이메일",
    SALARY,
    HIRE_DATE
from EMPLOYEES
where HIRE_DATE between to_date('2000-01-01','YYYY-MM-DD') and to_date('2003-12-31','YYYY-MM-DD');


--아래 조건을 모두 만족하는 직원의 '연봉 계산서'를 조회하세요:
--
--  조건 1: 부서 번호가 60, 80, 100 중 하나
--  조건 2: 급여가 5000 이상 15000 이하
--  조건 3: 직무 ID가 'IT_'로 시작하거나 'SA_'로 시작
--  조건 4: 커미션이 있는(IS NOT NULL) 직원
--
--  출력: 사번, 이름(full name), 직무ID, 급여, 커미션, 연봉(salary*12), 부서번호
--  정렬: 연봉 내림차순

select
    EMPLOYEE_ID as "사번",
    LAST_NAME || ' ' || FIRST_NAME as "이름",
    JOB_ID as "직무 ID",
    SALARY as "급여",
    COMMISSION_PCT as "커미션",
    SALARY * 12 as "연봉",
    DEPARTMENT_ID as "부서번호"
from EMPLOYEES
where
    DEPARTMENT_ID in (60, 80, 100)
    and SALARY between 5000 and 15000
    and (JOB_ID like 'IT\_%' escape '\' or JOB_ID like 'SA\_%' escape '\')
    and COMMISSION_PCT is not null
order by "연봉" desc;
