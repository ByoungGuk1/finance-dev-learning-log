--실습 과제 11-1  |  COMMIT · ROLLBACK 실습 (★★☆  응용)
--① emp_test 테이블에 3건의 행을 INSERT 하세요.
DESC emp_test;

INSERT INTO emp_test (last_name, email, hire_date, job_id)
VALUES ('A', 'B', SYSDATE, 'ABC');

INSERT INTO emp_test (last_name, email, hire_date, job_id)
VALUES ('B', 'C', SYSDATE, 'ABC');

INSERT INTO emp_test (last_name, email, hire_date, job_id)
VALUES ('C', 'D', SYSDATE, 'ABC');

--② INSERT 후 SELECT COUNT(*)로 행 수를 확인하세요.
SELECT COUNT(*)
FROM emp_test;

--③ ROLLBACK을 실행하고 다시 SELECT COUNT(*)로 원래 행 수가 복원되는지 확인하세요.
ROLLBACK;

--④ 다시 3건을 INSERT 후 COMMIT → 이후 ROLLBACK 시도 → 되돌릴 수 없음을 확인하세요.
INSERT INTO emp_test (last_name, email, hire_date, job_id)
VALUES ('A', 'B', SYSDATE, 'ABC');

INSERT INTO emp_test (last_name, email, hire_date, job_id)
VALUES ('B', 'C', SYSDATE, 'ABC');

INSERT INTO emp_test (last_name, email, hire_date, job_id)
VALUES ('C', 'D', SYSDATE, 'ABC');

COMMIT;
ROLLBACK;

SELECT COUNT(*)
FROM emp_test;

--
--실습 과제 11-2  |  SAVEPOINT 실습 (★★☆  응용)
--① emp_test에 INSERT 후 SAVEPOINT sp1 설정하세요.
INSERT INTO emp_test (last_name, email, hire_date, job_id)
VALUES ('C', 'D', SYSDATE, 'ABC');

SAVEPOINT sp1;

--② 추가로 UPDATE 후 SAVEPOINT sp2 설정하세요.
UPDATE emp_test
SET first_name = 'AAAA';

SAVEPOINT sp2;

--③ 추가로 DELETE를 실행하세요.
DELETE FROM emp_test;

--④ ROLLBACK TO sp2 → DELETE만 취소되는지 확인하세요.
ROLLBACK TO sp2;
SELECT * FROM emp_test;

--⑤ ROLLBACK TO sp1 → UPDATE까지 취소되는지 확인하세요.
ROLLBACK TO sp1;
SELECT * FROM emp_test;

--실습 과제 11-3  |  은행 이체 트랜잭션 시나리오 (★★★  심화)
--account_test(account_id, customer_name, balance) 테이블에서
--홍길동 계좌 → 김영희 계좌로 50만원 이체 트랜잭션을 구현하세요:

--① 홍길동 잔액 50만원 차감 UPDATE
UPDATE account_test
SET balance = balance - 500000
WHERE customer_name LIKE '홍길동';

SELECT * FROM account_test;
--② 김영희 잔액 50만원 증가 UPDATE
UPDATE account_test
SET balance = balance + 500000
WHERE customer_name = '김영희';

--③ 홍길동 잔액이 0 미만인지 확인
SELECT 
    CASE WHEN (
            SELECT balance
            FROM account_test
            WHERE customer_name LIKE '홍길동'
        ) < 0 THEN 'ROLLBACK'
        ELSE 'COMMIT'
    END "결과"
FROM DUAL;
-- → 잔액 부족: ROLLBACK (이체 실패 메시지)
-- → 잔액 충분: COMMIT  (이체 성공 메시지)
--④ 이체 전후 두 계좌의 잔액 합이 동일한지 검증하세요.
SELECT SUM(balance) AS "두 계좌 잔액 합계"
FROM account_test
WHERE customer_name IN ('홍길동', '김영희');