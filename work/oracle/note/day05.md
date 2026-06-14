# PLSQL

DB에서 미리 컴파일 된 프로그램

## 프로시저

| 선언 형식                   | 설명                  | 예시                          |
| --------------------------- | --------------------- | ----------------------------- |
| 변수명 타입;                | 단순 변수 선언        | v_name VARCHAR2(50);          |
| 변수명 타입 := 초기값;      | 초기값 포함 선언      | v_count NUMBER := 0;          |
| 변수명 타입 NOT NULL := 값; | NULL 불허 변수        | v_id NUMBER NOT NULL := 100;  |
| 상수명 CONSTANT 타입 := 값; | 상수 선언 (변경 불가) | c_tax CONSTANT NUMBER := 0.1; |
| 변수명 테이블.컬럼%TYPE;    | 컬럼 타입 자동 참조   | v_sal employees.salary%TYPE;  |
| 변수명 테이블%ROWTYPE;      | 테이블 행 전체 타입   | v_emp employees%ROWTYPE;      |

## FUNCTION

##
