--실습 과제 10-1  |  INSERT 실습 (★★☆  응용)
--① 아래 테이블을 생성하고 3건의 계좌 데이터를 INSERT 하세요:
CREATE TABLE account_test (account_id NUMBER(6) PRIMARY KEY,
    account_no CHAR(14) UNIQUE, customer_name VARCHAR2(20),
    balance NUMBER(18,2) DEFAULT 0, open_date DATE);
--
--   INSERT 데이터:
--   (100001, '11122233344455', '홍길동', 1500000, 오늘날짜)
--   (100002, '22233344455566', '김영희', 3000000, 오늘날짜)
--   (100003, '33344455566677', '이철수',  500000, 오늘날짜)
INSERT INTO account_test (account_id, account_no, customer_name, balance, open_date)
VALUES (100001, '11122233344455', '홍길동', 1500000, SYSDATE);
INSERT INTO account_test (account_id, account_no, customer_name, balance, open_date)
VALUES (100002, '22233344455566', '김영희', 3000000, SYSDATE);
INSERT INTO account_test (account_id, account_no, customer_name, balance, open_date)
VALUES (100003, '33344455566677', '이철수',  500000, SYSDATE);

SELECT * FROM account_test;

--
--② employees에서 급여 15000 이상인 직원 정보를 emp_test에 다중행 INSERT 하세요.
CREATE TABLE emp_test
AS
SELECT *
FROM employees
WHERE salary >= 15000;

--**
INSERT INTO emp_test
SELECT *
FROM employees
WHERE salary >= 15000;

SELECT * FROM emp_test;

--실습 과제 10-2  |  UPDATE · DELETE 실습 (★★☆  응용)
--① account_test에서 홍길동의 잔액을 200만원으로 UPDATE 하세요.
UPDATE account_test
SET balance = 2000000
WHERE customer_name LIKE '홍길동';

SELECT * FROM account_test;

--**② employees 전체 평균 급여보다 낮은 급여의 emp_test 직원을 10% 인상하세요. **
UPDATE emp_test
SET salary = salary * 1.1
WHERE salary < (
    SELECT AVG(salary)
    FROM employees
);

SELECT * FROM emp_test;
--③ account_test에서 잔액 100만원 미만인 계좌를 DELETE 하세요.
DELETE FROM account_test
WHERE balance < 1000000;

SELECT * FROM account_test;

--④ DELETE 전후의 행 수를 COUNT(*)로 확인하세요.
SELECT COUNT(*)
FROM account_test;

-- **실습 과제 10-3  |  MERGE 심화 (★★★  심화)**
--① 소스 테이블 생성: emp_new AS SELECT ... salary*1.2 FROM employees WHERE dept=90
--   추가로 신규직원 1명(employee_id=999) INSERT
CREATE TABLE emp_new
AS
SELECT employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary * 1.2 AS salary, commission_pct, manager_id, department_id
FROM employees
WHERE department_id = 90;

INSERT INTO emp_new (employee_id,first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id)
VALUES ( 999, 'aa', 'BB', 'CC', '010-0000-0000', SYSDATE, 'IT', 5000, NULL, 100, 90);

SELECT *
FROM emp_new
WHERE department_id = 90 OR employee_id = 999
ORDER BY employee_id;
--
--② MERGE INTO emp_test USING emp_new ON (employee_id 일치):
--   - 일치하면: salary, job_id UPDATE
--   - 없으면: 전체 컬럼 INSERT
MERGE INTO emp_test t
USING emp_new s ON (t.employee_id = s.employee_id)
WHEN MATCHED THEN
    UPDATE SET
        t.salary = s.salary,
        t.job_id = s.job_id
WHEN NOT MATCHED THEN
    INSERT
    VALUES (s.employee_id, s.first_name, s.last_name, s.email, s.phone_number, s.hire_date, s.job_id, s.salary, s.commission_pct, s.manager_id, s.department_id);

--③ MERGE 후 결과를 SELECT로 확인하세요.
SELECT *
FROM emp_test
WHERE department_id = 90 OR employee_id = 999
ORDER BY employee_id;
