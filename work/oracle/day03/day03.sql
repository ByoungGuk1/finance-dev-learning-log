SELECT job_id, MAX(employee_id) AS "MAX_EM_ID", MIN(first_name) AS "MIN_EM_FIRST_NAME", SUM(salary) AS "SUM_EM_SALARY", MIN(hire_date) AS "MIN_EM_HIRE_DATE" --5
FROM employees --1
WHERE department_id = 50 --2
GROUP BY job_id --3
HAVING SUM(salary) >= 50000 --4
ORDER BY job_id; --6

-- 서브쿼리
-- 1.단일 행 서브쿼리
-- 1-1. 전체 평균 급여보다 많이 받는 직원
SELECT employees.first_name, employees.salary
FROM employees
WHERE salary >= (
    SELECT AVG(salary)
    FROM employees
    );

-- 1-2. 급여를 제일 많이 받은 직원의 이름과 입사일과 부서코드
SELECT first_name, hire_date, department_id, salary
FROM employees
WHERE salary = (
    SELECT MAX(salary)
    FROM employees
    );

-- 1-3. Neena와 같은 부서에서 근무하는 직원
SELECT *
FROM employees
WHERE department_id = (
    SELECT department_id
    FROM employees
    WHERE employees.first_name LIKE INITCAP('neena')
    );

-- 1-4. 최근에 입사한 사람
SELECT *
FROM employees
WHERE hire_date = (
    SELECT MAX(hire_date)
    FROM employees
    );


-- 2.다중 행 서브쿼리
-- 2-1. Alexander 와 같은 부서에 있는 직원들 조회
SELECT *
FROM employees
WHERE department_id = ANY (
    SELECT department_id
    FROM employees
    WHERE employees.first_name LIKE INITCAP('alexander')
    );

-- 2-2. 매니저인 직원만 조회 (이름, 급여, 입사일, 부서)
-- > SELF JOIN
SELECT employee_id, first_name, salary, hire_date, department_id
FROM employees o
JOIN(
    SELECT DISTINCT manager_id
    FROM employees
    WHERE manager_id IS NOT NULL
) j ON o.employee_id = j.manager_id
ORDER BY o.employee_id;
-- > SUBQUERY (IN 또는 = ANY)
SELECT employee_id, first_name, salary, hire_date, department_id
FROM employees
WHERE employee_id IN (
    SELECT DISTINCT manager_id
    FROM employees
    WHERE manager_id IS NOT NULL
)
ORDER BY employee_id;

-- 2-3. 매니저가 아닌 직원만 조회 (이름, 급여, 입사일, 부서)
SELECT employee_id, first_name, salary, hire_date, department_id
FROM employees
WHERE employee_id NOT IN (
    SELECT DISTINCT manager_id
    FROM employees
    WHERE manager_id IS NOT NULL
)
ORDER BY employee_id;

-- 2-4. ALL : 부서번호가 30인 직원 모두보다 급여가 높은 직원 = `> MAX(...)`
SELECT *
FROM employees
WHERE salary > ALL (
    SELECT salary
    FROM employees
    WHERE department_id = 30
    );

-- 2-5. ANY : 부서번호가 30인 직원 중 누군가 보다 급여가 높은 직원 = `> MIN(...)`
SELECT *
FROM employees
WHERE salary > ANY (
    SELECT salary
    FROM employees
    WHERE department_id = 30
    );

-- 2-6. 부서에 직원이 한명 이상인 부서 조회
-- 상관 SUBQUERY -> 외부 테이블이 내부 테이블로 들어가는 서브쿼리
SELECT *
FROM departments
WHERE EXISTS (
    SELECT 1
    FROM employees
    WHERE employees.department_id = departments.department_id
);

-- 2-7. 부서에 직원이 없는 부서 조회
SELECT *
FROM departments
WHERE NOT EXISTS (
    SELECT 1
    FROM employees
    WHERE employees.department_id = departments.department_id
);


-- 3. INLINE VIEW : FROM 절의 SUBQUERY
-- 3-1 급여 상위 5번째인 조회
SELECT tt.first_name, tt.salary
FROM(
    SELECT ROWNUM AS "index", t.first_name, t.salary
    FROM(
        SELECT first_name, salary
        FROM employees
        ORDER BY salary DESC
    ) t
    WHERE ROWNUM <= 5
) tt
WHERE tt."index" = 5;

-- 4. 상관서브쿼리
-- 4-1. 각 직원의 급여가 자신이 속한 부서 평균 급여보다 높은 직원
SELECT o1.department_id, o1.first_name, o1.salary, ROUND(o2."평균급여")
FROM employees o1
JOIN (
    SELECT department_id, AVG(salary) AS "평균급여"
    FROM employees
    WHERE department_id IS NOT NULL
    GROUP BY department_id
) o2 ON o1.department_id = o2.department_id
WHERE o1.salary > o2."평균급여";

--> 상관
SELECT department_id, first_name
FROM employees o2
WHERE salary > (
    SELECT AVG(salary) AS "평균급여"
    FROM employees o1
    WHERE department_id IS NOT NULL
        AND o1.department_id = o2.department_id
    );



-- DDL (Data Definition Language)
-- (같은 구조로) TABLE 생성 후 DATA 복사 (INSERT)
-- > 제약조건은 복사되지 않음
CREATE TABLE emp_test
AS
SELECT * FROM employees;

SELECT * FROM emp_test;
-- 구조만 복사
CREATE TABLE emp_test2
AS
SELECT * FROM employees WHERE 1 = 0;

SELECT * FROM emp_test2;

CREATE TABLE emp_test3
AS
SELECT employee_id, first_name, salary, hire_date, job_id, department_id FROM employees;

SELECT * FROM emp_test3;

CREATE TABLE emp_test4
AS
SELECT employee_id, first_name, salary, hire_date, job_id, department_id FROM employees WHERE 1 = 0;

SELECT * FROM emp_test4;

-- DML
INSERT INTO emp_test2 (
  employee_id, first_name, last_name, email,
  hire_date,   job_id,     salary,    department_id
)
VALUES (
  300, '길동', '홍', 'HONG',
  TO_DATE('2024-04-22','YYYY-MM-DD'), 'IT_PROG', 6000, 60
);

INSERT INTO emp_test2
VALUES (
  300, '길동', '홍', 'HONG', '010-1234-5678',
  TO_DATE('2024-04-22','YYYY-MM-DD'), 'IT_PROG', 6000, 0.12, 100, 60
);

INSERT INTO emp_test4
VALUES(1, 'a', 1000, SYSDATE, 'play', 1000);

INSERT INTO emp_test4 (employee_id, first_name, salary, hire_date, job_id)
VALUES(1, 'a', 1000, SYSDATE, 'play');


-- SubQuery 로 INSERT (AS 불필요)
INSERT INTO emp_test4
(
    SELECT employee_id, first_name, salary, hire_date, job_id, department_id
    FROM employees
    WHERE employees.department_id = 60
);

-- UPDATE 수정
UPDATE emp_test4
SET first_name = '수정',
    salary = 2000,
    department_id = 90
WHERE employee_id = 1;

UPDATE emp_test4
SET first_name = '수정',
    salary = (
        SELECT salary
        FROM emp_test4
        WHERE first_name = 'Diana'
        ),
    department_id = 99
WHERE employee_id = 1;

--DELETE
DELETE
FROM emp_test4
WHERE employee_id = 1;

DELETE
FROM emp_test4;

SELECT * FROM emp_test4;

DELETE FROM emp_test3;

-- > DML : INSERT, DELETE, UPDATE 는 해당 세션에서만 적용
-- > DDL 은 자동으로 COMMIT;












-- DDL 자동 커밋
-- 제약조건은 복사 안됨, NOT NULL은 복사.
CREATE TABLE emp_test5
AS
SELECT *
FROM employees
WHERE department_id = 60;

SELECT * FROM emp_test5;

DESC EMP_TEST5;

INSERT INTO emp_test5(employee_id, first_name, last_name, email, hire_date, job_id)
VALUES (1, 'AA', 'BB', 'CC', SYSDATE, 'IT');

SELECT * FROM emp_test5 WHERE employee_id = 1;


CREATE TABLE emp_test6
AS
SELECT *
FROM employees
WHERE 1 = 0;

-- DDL : CREATE TABLE ... => 자동으로 커밋 됨
-- DML : INSERT, DELETE, UPDATE => 자동으로 커밋되지 않음

--MERGE
DROP TABLE emp_test;

CREATE TABLE emp_test
AS
SELECT * FROM employees WHERE 1 = 0;

DESC emp_test;

INSERT INTO emp_test (employee_id, first_name, last_name, email, hire_date, job_id, salary, department_id)
VALUES (100, 'A', 'B', 'C', SYSDATE, 'IT', 1000, 90);

SELECT * FROM emp_test;

MERGE INTO emp_test d
USING  employees   s  ON (d.employee_id = s.employee_id)
WHEN MATCHED THEN
  UPDATE SET d.salary = s.salary,
             d.job_id = s.job_id
WHEN NOT MATCHED THEN
  INSERT (employee_id, first_name, last_name, email,
          hire_date, job_id, salary, department_id)
  VALUES (s.employee_id, s.first_name, s.last_name, s.email,
          s.hire_date, s.job_id, s.salary, s.department_id);


desc departments;

commit;

-- 트랜잭션
INSERT INTO jobs
VALUES ('AA', '공부', 1000, 2000);

DELETE FROM jobs
WHERE job_id = 'play';

SELECT *
FROM jobs;

commit;


INSERT INTO jobs
VALUES ('BB', '공부2', 1000, 2000);

DELETE FROM jobs
WHERE job_id = 'AA';

SELECT *
FROM jobs;

ROLLBACK;


DELETE FROM EMP_TEST;
COMMIT;


SELECT * FROM emp_test;

INSERT INTO emp_test (employee_id,first_name,last_name,email,hire_date,job_id,salary)
VALUES (301,'테스트1','홍','TEST1',SYSDATE,'IT_PROG',5000);

SAVEPOINT sp1;   -- 저장 지점 1

INSERT INTO emp_test (employee_id,first_name,last_name,email,hire_date,job_id,salary)
VALUES (302,'테스트2','김','TEST2',SYSDATE,'IT_PROG',6000);

SAVEPOINT sp2;   -- 저장 지점 2

DELETE FROM emp_test WHERE employee_id = 301;

ROLLBACK TO sp2;  -- DELETE만 취소 → 301, 302 모두 존재
ROLLBACK TO sp1;  -- INSERT 302 + DELETE 취소 → 301만 존재
ROLLBACK;         -- 전체 취소 → 301도 사라짐
COMMIT;


SELECT SYS_CONTEXT('USERENV','SESSIONID') FROM DUAL;



DELETE FROM emp_test;
