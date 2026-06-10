식별자 오류 -> 컬럼명, 별칭, 테이블명 같은 식별자 오류

`INITCAP(str)` : 각 단어의 첫 글자는 대문자로, 나머지는 소문자

최대한 기존의 값을 손상시키지 않도록 입력되는 값을 변경되도록 노력

## 서브쿼리

from절에서 사용되는 서브쿼리 : 인라인뷰 -> 휘발  
select절에서 사용되는 서브쿼리 : 스칼라 서브쿼리

**상관 SUBQUERY** -> 외부 테이블이 내부 테이블로 들어가는 서브쿼리

### ANY

`< ANY(...)` : ... 중 최대 값 보다 작다  
=> `< val1 OR < val2 OR ...`  
=> `< MAX(...)`

`> ANY(...)` : ... 중 최대 값 보다 작다  
=> `> val1 OR > val2 OR ...`  
=> `> MIN(...)`

### ALL

`< ALL(...)` : ... 중 최소 값보다 작다  
=> `< val1 AND < val2 AND ...`  
=> `< MIN(...)`

`> ALL(...)` : ... 중 최소 값보다 작다  
=> `> val1 AND > val2 AND ...`  
=> `> MAX(...)`

### IN

`IN (a, b, ...)`  
=> `= a OR = b OR ...`

`NOT IN (a, b, ...)` : 구문 사용 시 NULL 조심해서 사용하기  
=> `<> a AND <> b AND ...` : NULL이 있는 경우 전체 데이터를 제외해 버린다

### EXISTS

단순 확인 용도로 속도면에서 `IN` 보다 빠르다.

IN : 서브쿼리 전체 결과를 메모리에 올린 후 비교  
EXISTS : 일치하는 행이 있는지만 확인 (값 목록 불필요)

```SQL
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
```

### INLINE VIEW

FROM 절에 사용하는 서브쿼리,  
임시 테이블처럼 사용

### 상관 서브 쿼리

서브쿼리가 메인 쿼리의 컬럼을 참조하는 서브쿼리  
메인 쿼리의 행마다 서브쿼리가 실행

일반 서브쿼리: 한 번만 실행 → 결과를 메인에 전달  
상관 서브쿼리: 메인 쿼리 행마다 반복 실행 (대용량 데이터 시 성능 주의)

### 스칼라 서브쿼리

SELECT 절에서 사용되는 SubQuery

### 다중 COLUMN SUBQUERY

```SQL
WHERE (A, B) IN (A- , B-)
```

## DML

DML(Data Manipulation Language)이란?
테이블의 데이터를 추가·수정·삭제하는 SQL 명령어입니다.
|키워드|설명|
|--|--|
|INSERT | 새 행 추가|
|UPDATE | 기존 행 수정|
|DELETE | 기존 행 삭제|
|MERGE | 조건에 따라 INSERT 또는 UPDATE 자동 선택|

### MERGE

대상 테이블에 데이터가 없으면 INSERT, 있으면 UPDATE를 자동으로 처리  
ETL(데이터 적재) 작업이나 배치 처리에서 자주 사용  
MySQL의 INSERT ... ON DUPLICATE KEY UPDATE 와 유사한 기능

## 트랜잭션

### 특징

| 종류 (ACID)            | 설명                                                    |
| ---------------------- | ------------------------------------------------------- |
| A (Atomicity) 원자성   | 모두 성공하거나 모두 실패 (부분 완료 없음)              |
| C (Consistency) 일관성 | 트랜잭션 전후 데이터 무결성 유지                        |
| I (Isolation) 고립성   | 진행 중인 트랜잭션의 중간 상태를 다른 세션이 볼 수 없음 |
| D (Durability) 지속성  | COMMIT된 데이터는 장애 발생 후에도 유지됨               |

```SQL
SELECT employee_id, salary
FROM   emp_test
WHERE  department_id = 90
FOR    UPDATE;
-- SELECT FOR UPDATE: 조회와 동시에 행 잠금 획득

-- NOWAIT: 잠금 획득 실패 시 즉시 오류 반환
SELECT employee_id, salary
FROM   emp_test WHERE employee_id = 100
FOR    UPDATE NOWAIT;

-- WAIT n: n초 대기 후 실패 시 오류
SELECT employee_id, salary
FROM   emp_test WHERE employee_id = 100
FOR    UPDATE WAIT 5;
```
