-- DQL -> select
-- DML -> insert, delete, update, merge
-- select 내부에 selcet -> subQuerty

--SELECT 
--FROM
--WHERE
--GROUP BY
--HAVING
--ORDER BY

---

INSERT INTO jobs(job_id, job_title, max_salary, min_salary)
VALUES ('a2','b2',100,10);

select * from jobs;

rollback;

--transaction
-- 적용 : commit
-- 취소 : rollback;

----start
-- 제약조건을 칼럼에 붙여서 작성 => column level
-- 마지막에 제약조건을 작성 => table level (권장)
CREATE TABLE accounts (
  account_id     NUMBER(10)      NOT NULL,
  account_no     CHAR(14)        NOT NULL,
  customer_name  VARCHAR2(50)    NOT NULL,
  account_type   VARCHAR2(10)    DEFAULT 'SAVINGS',
  balance        NUMBER(18,2)    DEFAULT 0,
  open_date      DATE            DEFAULT SYSDATE,
  status         CHAR(1)         DEFAULT 'A',
  created_at     TIMESTAMP       DEFAULT SYSTIMESTAMP,
  CONSTRAINT pk_accounts PRIMARY KEY (account_id),
  CONSTRAINT uq_account_no UNIQUE (account_no),
  CONSTRAINT ck_status CHECK (status IN ('A','C','F')),
  CONSTRAINT ck_balance CHECK (balance >= 0),
  CONSTRAINT ck_type CHECK (account_type IN ('SAVINGS','CHECKING','LOAN'))
);


desc accounts;

INSERT INTO accounts(account_id, account_no, customer_name)
VALUES(1,'shinhan1', '신한');

-- ORA-00947: not enough values -> INSERT의 값의 갯수가 부족
INSERT INTO accounts
VALUES (2, 'shinhan2', '신한', 'CHECKING', 2000, SYSDATE, 'C', SYSTIMESTAMP); 

-- ORA-02290: check constraint (HR.CK_TYPE) violated -> CHECK 오류
INSERT INTO accounts
VALUES (3, 'shinhan3', '신한', 'CHECKING', 2000, SYSDATE, 'A', SYSTIMESTAMP);

SELECT * FROM accounts;


CREATE TABLE account3(
    account_id number,
    account_name varchar2(20),
    constraint pk_account3__accountid  primary key (account_id)
);

desc account3;

---time2
create table empl_backup as select * from employees;
desc empl_backup;


-- 테이블 수정
ALTER TABLE ACCOUNTS ADD MEMO VARCHAR2(200);
DESC accounts;

COMMIT;
SELECT * FROM accounts;

ALTER TABLE accounts MODIFY customer_name VARCHAR2(100);

UPDATE accounts SET customer_name = '목요일목요일목요일목요일목요일목요일'
WHERE account_id = 1;

ALTER TABLE accounts DROP COLUMN memo;
ALTER TABLE accounts DROP (memo, status);

ALTER TABLE accounts RENAME COLUMN account_no TO acct_number;
-- 테이블명 변경
RENAME accounts TO bank_accounts;
ALTER TABLE bank_accounts RENAME TO accounts;

DESC emp_backup;
DROP TABLE emp_backup;

DROP TABLE accounts CASCADE CONSTRAINTS;


--INDEX
DESC employees;

SELECT * 
FROM employees
WHERE employee_id = 100;

SELECT * 
FROM employees
WHERE email = 'AA';

SELECT * 
FROM employees
WHERE first_name = 'Steven';

--값에 변형이 일어나서 인덱스를 사용하지 않고 FULL SCAN
SELECT *
FROM employees
WHERE LOWER(first_name) = 'steven';

-- 따라서 테이블의 값에 영향을 안주는 방식으로 조회
SELECT *
FROM employees
WHERE first_name = INITCAP('steven');

-- 인덱스 생성
CREATE INDEX idx_emp_dept ON employees(department_id);

-- 인덱스 조회
SELECT index_name, index_type, uniqueness, column_name
FROM   user_indexes  i
  JOIN user_ind_columns c ON i.index_name = c.index_name
WHERE  i.table_name = 'EMPLOYEES'
ORDER  BY index_name, column_position;

-- 인덱스 삭제
DROP INDEX idx_emp_salary;


-----------------
-- 테스트 테이블 생성 (employees 구조와 동일)
DROP TABLE emp_test PURGE;

CREATE TABLE emp_test (
    employee_id   NUMBER(10),
    first_name    VARCHAR2(50),
    job_id        VARCHAR2(20),
    salary        NUMBER(10),
    hire_date     DATE
);

-- 인덱스 생성 (EMP_JOB_IX 와 동일 구조)
CREATE INDEX idx_emptest_job ON emp_test(job_id);

-- 10만 건 INSERT (CONNECT BY LEVEL 활용)
INSERT INTO emp_test
SELECT
    ROWNUM,
    'EMP_' || ROWNUM,
    CASE MOD(ROWNUM, 10)
        WHEN 0 THEN 'IT_PROG'   WHEN 1 THEN 'SA_REP'
        WHEN 2 THEN 'ST_CLERK'  WHEN 3 THEN 'AD_ASST'
        WHEN 4 THEN 'FI_ACCOUNT' WHEN 5 THEN 'HR_REP'
        WHEN 6 THEN 'MK_REP'   WHEN 7 THEN 'PR_REP'
        WHEN 8 THEN 'PU_CLERK'  ELSE   'SH_CLERK'
    END,
    ROUND(DBMS_RANDOM.VALUE(2000, 20000)),
    SYSDATE - ROUND(DBMS_RANDOM.VALUE(0, 3650))
FROM DUAL
CONNECT BY LEVEL <= 100000;   -- 10만 건

COMMIT;

select count(*) from emp_test;
select count(*) from emp_test where job_id = 'IT_PROG';

-- 옵티마이저 통계 수집 (정확한 실행계획을 위해 필수)
EXEC DBMS_STATS.GATHER_TABLE_STATS(USER, 'EMP_TEST');

-- 직군별 분포 확인 (각 10,000건씩)
SELECT job_id, COUNT(*) AS 건수
FROM   emp_test
GROUP  BY job_id
ORDER  BY job_id;

--
-- ① 인덱스 정상 사용: 컬럼 원본값 비교
-- > 인덱스보다 풀스캔의 비용이 더 낮다면 풀스캔 사용
-- > 다음 코드는 힌트를 이용해서 강제로 index를 사용 -> 비용이 더 많이 든다는 것을 확인
EXPLAIN PLAN FOR
SELECT + INDEX(emp_test idx_emptest_job)
FROM emp_test
WHERE job_id = 'IT_PROG';
SELECT * FROM TABLE(DBMS_XPLAN.DISPLAY);

EXPLAIN PLAN FOR
SELECT * FROM emp_test WHERE job_id = 'IT_PROG';
SELECT * FROM TABLE(DBMS_XPLAN.DISPLAY);


---SEQ 만들기

create sequence seq_number;
-- > default : 1부터 숫자 끝까지
create sequence seq_number start with 10;
-- > 10부터 시작
create sequence seq_number increment by 10;
-- > 10씩 증가

create table empl_test2(
    id number primary key,
    name varchar2(100)
);

insert into empl_test2 values (seq_number.nextval, '홍길동');
select * from empl_test2;

-- view
-- 1) 가상의 table (select문 저장)
-- 2) 복잡한 sql을 반복 사용 시 단순화 하기 위해
-- 3) 보안(특정 부서에 특정 column만 공개하기 위해)

-- 직원+부서+위치 복합 뷰 생성
CREATE OR REPLACE VIEW vw_emp_detail AS
SELECT e.employee_id,
       e.first_name || ' ' || e.last_name AS full_name,
       e.salary,
       e.hire_date,
       d.department_name,
       l.city,
       j.job_title
FROM   employees   e
  JOIN departments d ON e.department_id = d.department_id
  JOIN locations   l ON d.location_id   = l.location_id
  JOIN jobs        j ON e.job_id        = j.job_id;

-- 뷰 사용 (일반 테이블처럼 쿼리)
SELECT * FROM vw_emp_detail WHERE city = 'Seattle';

-- 뷰 목록 확인
SELECT view_name FROM user_views;

-- 뷰 삭제
DROP VIEW vw_emp_detail;

--시노님 -> 테이블 as 생성
-- 대표적인 예시 : dual
-- sys라는 DBA가 dual table를 공개 시노님으로 만들어서 제공

create synonym emp for employees;

select * from emp;



-- 제약조건
-- 1. PRIMARY KEY
-- 2. NOT NULL
-- 3. UNIQUE
-- 4. CHECK
-- 5. FOREIGN KEY

-- 복합 PK (두 컬럼 조합이 PK)
CREATE TABLE order_items (
  order_id    NUMBER(10) NOT NULL,
  product_id  NUMBER(10) NOT NULL,
  quantity    NUMBER(5),
  CONSTRAINT pk_order_items PRIMARY KEY (order_id, product_id)
);

INSERT INTO order_items VALUES(1,1,100);
INSERT INTO order_items VALUES(1,2,100);

SELECT * FROM order_items;

select * from user_constraints where table_name = 'ORDER_ITEMS';
SELECT * FROM USER_CONS_COLUMNS WHERE TABLE_NAME = 'ORDER_ITEMS';

-- 

create table customers(
    customer_id number primary key,
    customer_name varchar2(100),
    email varchar2(50) unique
);

CREATE TABLE transactions2 (
  txn_id      NUMBER(10)  NOT NULL,
  customer_id NUMBER(10)  NOT NULL,
  amount      NUMBER(18,2),
  CONSTRAINT pk_txn2    PRIMARY KEY (txn_id),
  CONSTRAINT fk_txn_cust FOREIGN KEY (customer_id)
    REFERENCES customers (customer_id)
    ON DELETE CASCADE   -- 고객 삭제 시 거래내역도 자동 삭제
);

-- FK 참조 무결성 위반 테스트
INSERT INTO transactions2 VALUES (1, 9999, 10000);

insert into customers values(100,'1','123');
insert into transactions2 values(1,100,2000);
insert into transactions2 values(2,100,3000);

insert into customers values(200,'2','1234');

-- 자식 레코드가 있는 경우에 부모를 삭제할 때
-- 기본 : 삭제 불가 (에러)
-- ON DELETE CASCADE : 자식도 삭제 (ROW 삭제)
-- ON DELETE SET NULL : 자식 자리에 값을 NULL로 변경 후 부모 삭제
delete from customers where customer_id = 100;

SELECT * FROM transactions2;







---------

create table char_test(
    int number primary key,
    cust_name1 char(10),
    cust_name2 varchar2(10)
);

insert into char_test values(1,'aa','aa');
insert into char_test values(2,'bb','bb');

commit;
select * from char_test where cust_name1 = 'aa       ';
select * from char_test where cust_name2 = 'aa       ';



