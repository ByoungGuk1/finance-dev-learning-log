수업 진행은 11g 버전으로 진행

psudo calumn  
rownum?

---

## 참조 무결성

참조할 수 없는 키를 fk로 참조 할 수 없다.

## 개체 무결성

각 개체는 유일한 pk 값을 가져야한다.

## 도메인 무결성

설정된 제약조건을 만족해야한다.

## 고유 무결성

unique

```sql
-- 개체 무결성(PK) : Not null + unique
-- "HR"."EMPLOYEES" 테이블에 변경사항을 저장하는 중 한 개의 오류 발생:
--2 행: ORA-00001: unique constraint (HR.EMP_EMP_ID_PK) violated

--참조무결성 (FK)
--"HR"."EMPLOYEES" 테이블에 변경사항을 저장하는 중 한 개의 오류 발생:
--1 행: ORA-02291: integrity constraint (HR.EMP_JOB_FK) violated - parent key not found

--도메인 무결성( check )
--"HR"."EMPLOYEES" 테이블에 변경사항을 저장하는 중 한 개의 오류 발생:
--1 행: ORA-02290: check constraint (HR.EMP_SALARY_MIN) violated

--고유 무결성 (unique)
--"HR"."EMPLOYEES" 테이블에 변경사항을 저장하는 중 한 개의 오류 발생:
--2 행: ORA-00001: unique constraint (HR.EMP_EMAIL_UK) violated
```

# dba

## oracle의 메모리 구조

### shared pool

공유 메모리 (라이브러리 캐시) == SGA 내부

sql 입력 -> sql파싱 (문장을 잘라서 부분 분석) -> shared pool에 저장  
-> 이미 있는 값이라면 그대로 실행

바인딩 구문? 으로 사용하기  
`select * from tbl_table where id = ?;`

### buffer cache

SGA 내부  
데이터를 블록단위로 임시 저장

### redo log buffer

SGA 내부  
로그 파일에 변경 내역을 임시 저장

### PGA

개별 프로세스  
세션별 작업 공간

# all

## oracle data type

### 문자

varchar2(n) : n bypte의 String 타입 - 최대 4000byte  
CLOB : 장문 최대 4GB

### 숫자

NUMBER  
NUMBER(n) : 정수 n 자리  
NUMBER(n,m) : 전체 n자리 소수점 m자리 (정수는 n - m자리까지)

### 날짜 시간 타입

DATE : (오라클의 경우)초단위까지 포함 -> mySql의 경우 날짜까지만  
==(mySql) datetime

## select

### select 문 연산

(오라클)
`NVL(column_name, 0)` -> null인 경우 0으로 계산

구조확인 시
`desc table_name;`

### select 문 연결

select 문  
(오라클)
-> `column_name || column_name`

mysql => `concat(a,b)` -> ab

```sql
--직원정보: 번호, 이름, 급여, 연봉 , 세금(10%)
select EMPLOYEE_ID, FIRST_NAME, LAST_NAME,
        FIRST_NAME ||'--'|| LAST_NAME 연결연산자사용,
        concat( concat(FIRST_NAME, '--'),LAST_NAME)  함수이용,
        SALARY 급여,
        COMMISSION_PCT 커미션,
        SALARY + (SALARY * nvl(COMMISSION_PCT,0) )   급여2,
        SALARY*12 as "직원의 연봉",
        SALARY*12*0.1 "10%"
from employees;
```

### DISTINCT

중복 제거 -> 조회한 뭉치로 중복 확인

### NULL 특징

비교 불가 (=)

```SQL
SELECT *
FROM employees
WHERE commission_pct IS NULL;
```

```SQL
-- ORACLE 정책상 NULL은 최후순위
SELECT employee_id, first_name, salary
FROM EMPLOYEES
ORDER BY commission_pct ASC;
```

널을 맨 앞으로

```SQL
-- ORACLE 정책상 NULL은 최후순위
SELECT employee_id, first_name, salary, commission_pct
FROM EMPLOYEES
ORDER BY commission_pct NULLS FIRST;
```

## where

### 연산자

= : 같다  
<> : 다르다

### date type

Oracle date 의 default 는 'RR/MM/DD'  
50 이상이면 1900년대  
50 미만이면 2000년대

### 논리연산자

AND : 둘 모두 참  
OR : 하나라도 참  
NOT : 모두 거짓

BETWEEN A AND B : A와 B 사이

IN (A,B,C,...) : A,B,C ... 인 값들  
=> NOT IN으로 응용 가능  
=> NULL은 비교시 제외 => IS NOT NULL 사용

```TEXT
NOT IN + NULL 함정 (면접 빈출!)
아래 쿼리는 결과가 0행입니다. 왜일까요?

  SELECT * FROM employees WHERE department_id NOT IN (10, NULL);

이유: NULL이 포함된 NOT IN은 내부적으로
  department_id <> 10 AND department_id <> NULL
으로 변환되는데, '어떤 값 <> NULL'은 항상 UNKNOWN(FALSE처럼 처리)입니다.

해결: NOT IN 목록에 NULL이 없도록 하거나, IS NOT NULL 조건을 추가하세요.
```

### 패턴 검색 LIKE

```TEXT
LIKE 'S%' : S로 시작하는 값
LIKE '%a%' : 문자열 사이에 a가 포함된 값
LIKE '_ING' : ING로 끝나는 4글자
LIKE 'A___' : A로 시작하는 4글자

% 문자를 조회할 때
LIKE '%\%%' ESCAPE '\'
=> %가 포함된 문자열
```

### 연산자 우선순위

괄호
산술
비교
BETWEEN, IN, LIKE, IS NULL...
NOT
AND
OR
