-- PLSQL

SET SERVEROUTPUT ON; -- 스크립트 출력에서 보기

-- 가장 간단한 PL/SQL 블록 (익명 블록)
BEGIN
  DBMS_OUTPUT.PUT_LINE('Hello, PL/SQL!');
END;
/
DECLARE
 v_emp_id     NUMBER          := 100;
 v_name       VARCHAR2(100);
  v_salary     employees.salary%TYPE;   -- 컬럼 타입 자동 참조
  c_bonus_rate CONSTANT NUMBER := 0.1;  -- 상수
  v_email employees.email%TYPE;
BEGIN
v_email := 'ZZDSFLKJ';
v_salary := 2000;
dbms_output.put_line('v_email = ' || v_email);
  -- SELECT INTO: 단일 행 조회 결과를 변수에 저장
  SELECT first_name || ' ' || last_name, salary
  INTO   v_name, v_salary
  FROM   employees
  WHERE  employee_id = v_emp_id;

  DBMS_OUTPUT.PUT_LINE('직원명: ' || v_name);
  DBMS_OUTPUT.PUT_LINE('급여:   ' || v_salary);
  DBMS_OUTPUT.PUT_LINE('보너스: ' || v_salary * c_bonus_rate);
END;
/

DECLARE
  v_salary  employees.salary%TYPE;
  v_grade   VARCHAR2(10);
BEGIN
  SELECT salary INTO v_salary
  FROM   employees WHERE employee_id = 103;

  IF    v_salary >= 15000 THEN v_grade := 'S등급';
  ELSIF v_salary >= 10000 THEN v_grade := 'A등급';
  ELSIF v_salary >=  5000 THEN v_grade := 'B등급';
  ELSE                         v_grade := 'C등급';
  END IF;

  DBMS_OUTPUT.PUT_LINE('급여: ' || v_salary || ' / 등급: ' || v_grade);
END;
/



DECLARE
  v_dept_id  NUMBER := 90;
  v_dept_name VARCHAR2(30);
BEGIN
  -- 단순 CASE
  CASE v_dept_id
    WHEN 10  THEN v_dept_name := '총무';
    WHEN 60  THEN v_dept_name := 'IT';
    WHEN 80  THEN v_dept_name := '영업';
    WHEN 90  THEN v_dept_name := '경영진';
    ELSE          v_dept_name := '기타';
  END CASE;

  DBMS_OUTPUT.PUT_LINE('부서: ' || v_dept_name);
END;
/

DECLARE
  v_cnt NUMBER := 1;
BEGIN
  LOOP
    DBMS_OUTPUT.PUT_LINE('반복: ' || v_cnt);
    v_cnt := v_cnt + 1;
    EXIT WHEN v_cnt > 5;  -- 탈출 조건
  END LOOP;
END;
/


-- 숫자 범위 FOR LOOP
BEGIN
  FOR i IN 1..5 LOOP
    DBMS_OUTPUT.PUT_LINE(i || '번째 실행');
  END LOOP;
END;
/

-- REVERSE: 역순
BEGIN
  FOR i IN REVERSE 1..5 LOOP
    DBMS_OUTPUT.PUT_LINE(i);
  END LOOP;
END;
/

DECLARE
  v_sum NUMBER := 0;
  v_i   NUMBER := 1;
BEGIN
  WHILE v_i <= 100 LOOP
    v_sum := v_sum + v_i;
    v_i   := v_i + 1;
  END LOOP;
  DBMS_OUTPUT.PUT_LINE('1~100 합계: ' || v_sum);
END;
/


-- %TYPE: 컬럼 타입 자동 참조
DECLARE
  v_ename  employees.first_name%TYPE;
  v_salary employees.salary%TYPE;
BEGIN
  SELECT first_name, salary
  INTO   v_ename, v_salary
  FROM   employees WHERE employee_id = 100;
  DBMS_OUTPUT.PUT_LINE(v_ename || ': ' || v_salary);
END;
/

-- %ROWTYPE: 테이블 전체 행 타입 참조
DECLARE
  v_emp employees%ROWTYPE;  -- 테이블의 모든 컬럼을 포함
  v_dept departments%ROWTYPE;
BEGIN
SELECT * into v_dept FROM DEPARTMENTS WHERE DEPARTMENT_ID=50;

  SELECT * INTO v_emp
  FROM   employees WHERE employee_id = 101;

  DBMS_OUTPUT.PUT_LINE('이름: ' || v_emp.first_name || ' ' || v_emp.last_name);
  DBMS_OUTPUT.PUT_LINE('직무: ' || v_emp.job_id);
  DBMS_OUTPUT.PUT_LINE('급여: ' || v_emp.salary);
  DBMS_OUTPUT.PUT_LINE(V_dept.department_id);
END;
/


-- 사용자 정의 레코드 타입
DECLARE
  TYPE t_emp_info IS RECORD (
    emp_id   NUMBER,
    fullname VARCHAR2(100),
    dept     VARCHAR2(30),
    salary   NUMBER
  );
  v_info t_emp_info;
BEGIN
  SELECT e.employee_id,
         e.first_name || ' ' || e.last_name,
         d.department_name,
         e.salary
  INTO   v_info
  FROM   employees e JOIN departments d
         ON e.department_id = d.department_id
  WHERE  e.employee_id = 100;

  DBMS_OUTPUT.PUT_LINE(v_info.emp_id || ' | ' || v_info.fullname
    || ' | ' || v_info.dept || ' | ' || v_info.salary);
END;
/

DECLARE
  -- 숫자 키 연관 배열
  TYPE t_salary_list IS TABLE OF NUMBER
    INDEX BY PLS_INTEGER;
  v_salaries t_salary_list;

  -- VARCHAR2 키 연관 배열 (사전처럼 사용)
  TYPE t_dept_map IS TABLE OF VARCHAR2(50)
    INDEX BY VARCHAR2(10);
  v_dept t_dept_map;
BEGIN
  -- 숫자 키 사용
  v_salaries(1) := 24000;
  v_salaries(2) := 17000;
  v_salaries(3) :=  9000;

  FOR i IN 1..3 LOOP
    DBMS_OUTPUT.PUT_LINE(i || '번째 급여: ' || v_salaries(i));
  END LOOP;

  -- 문자열 키 사용 (부서 코드 → 부서명 매핑)
  v_dept('IT')   := 'IT 개발팀';
  v_dept('FIN')  := '재무팀';
  v_dept('SALES'):= '영업팀';

  DBMS_OUTPUT.PUT_LINE('IT 부서: ' || v_dept('IT'));
END;
/

DECLARE
  TYPE t_name_list IS TABLE OF VARCHAR2(50);
  v_names t_name_list := t_name_list();  -- 빈 컬렉션 초기화
BEGIN
  -- EXTEND: 공간 확장 후 값 저장
  v_names.EXTEND;  v_names(1) := 'Steven King';
  v_names.EXTEND;  v_names(2) := 'Neena Kochhar';
  v_names.EXTEND;  v_names(3) := 'Lex De Haan';

  DBMS_OUTPUT.PUT_LINE('총 ' || v_names.COUNT || '명');
  FOR i IN 1..v_names.COUNT LOOP
    DBMS_OUTPUT.PUT_LINE(i || '. ' || v_names(i));
  END LOOP;

  -- DELETE: 특정 요소 삭제
  v_names.DELETE(2);
  DBMS_OUTPUT.PUT_LINE('삭제 후 COUNT: ' || v_names.COUNT);
END;
/



DECLARE
  TYPE t_emp_ids   IS TABLE OF employees.employee_id%TYPE;
  TYPE t_emp_names IS TABLE OF VARCHAR2(100);
  v_ids   t_emp_ids;
  v_names t_emp_names;
BEGIN
  -- BULK COLLECT: 한 번에 여러 행 수집
  SELECT employee_id,
         first_name || ' ' || last_name
  BULK COLLECT INTO v_ids, v_names
  FROM   employees
  WHERE  department_id = 60;

  DBMS_OUTPUT.PUT_LINE('IT 부서 직원 수: ' || v_ids.COUNT);
  FOR i IN 1..v_ids.COUNT LOOP
    DBMS_OUTPUT.PUT_LINE(v_ids(i) || ' - ' || v_names(i));
  END LOOP;
END;
/












DECLARE
  -- 커서 선언
  CURSOR c_emp IS
    SELECT employee_id, first_name, salary
    FROM   employees
    WHERE  department_id = 90
    ORDER  BY salary DESC;

  v_id    employees.employee_id%TYPE;
  v_name  employees.first_name%TYPE;
  v_sal   employees.salary%TYPE;
BEGIN
  OPEN c_emp;   -- 커서 열기 (쿼리 실행)

  LOOP
    FETCH c_emp INTO v_id, v_name, v_sal;  -- 한 행 가져오기
    EXIT WHEN c_emp%NOTFOUND;              -- 더 이상 행이 없으면 종료

    DBMS_OUTPUT.PUT_LINE(v_id || ' | ' || v_name || ' | ' || v_sal);
  END LOOP;

  CLOSE c_emp;  -- 커서 닫기
END;
/

-- 인라인 커서 FOR LOOP (커서 선언 없이 직접)
DECLARE
BEGIN
  FOR r IN (SELECT first_name, salary FROM employees WHERE department_id = 90) LOOP
    DBMS_OUTPUT.PUT_LINE(r.first_name || ': ' || r.salary);
  END LOOP;
END;
/







create table emp_back as select * from employees;

desc emp_back;
select * from emp_back where employee_id = 103;

-- IN 매개변수 프로시저: 직원 급여 인상
CREATE OR REPLACE PROCEDURE raise_salary (
  p_emp_id   IN emp_back.employee_id%TYPE,
  p_pct      IN NUMBER DEFAULT 0.1   -- 기본값 10%
)
IS
  v_current_sal emp_back.salary%TYPE;
BEGIN
  SELECT salary INTO v_current_sal
  FROM   emp_back WHERE employee_id = p_emp_id;

  UPDATE emp_back
  SET    salary = salary * (1 + p_pct)
  WHERE  employee_id = p_emp_id;

  DBMS_OUTPUT.PUT_LINE(
    '직원 ' || p_emp_id || ' 급여 인상: '
    || v_current_sal || ' → ' || ROUND(v_current_sal * (1 + p_pct))
  );
  COMMIT;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    DBMS_OUTPUT.PUT_LINE('오류: ' || p_emp_id || '번 직원이 없습니다.');
    ROLLBACK;
END raise_salary;
/

-- 프로시저 실행
EXEC raise_salary(103);           -- 기본값 10% 인상
EXEC raise_salary(103, 0.15);     -- 15% 인상

-- 또는 CALL 문 사용
CALL raise_salary(103, 0.1);
























-- OUT 매개변수: 직원 정보 조회 프로시저
CREATE OR REPLACE PROCEDURE get_emp_info (
  p_emp_id   IN  employees.employee_id%TYPE,
  p_name     OUT VARCHAR2,
  p_salary   OUT employees.salary%TYPE,
  p_dept     OUT departments.department_name%TYPE
)
IS
BEGIN
  SELECT e.first_name || ' ' || e.last_name,
         e.salary,
         d.department_name
  INTO   p_name, p_salary, p_dept
  FROM   employees   e
    JOIN departments d ON e.department_id = d.department_id
  WHERE  e.employee_id = p_emp_id;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    p_name   := '미존재';
    p_salary := 0;
    p_dept   := '미배정';
END get_emp_info;
/

-- OUT 매개변수 프로시저 호출
DECLARE
  v_name   VARCHAR2(100);
  v_salary NUMBER;
  v_dept   VARCHAR2(50);
BEGIN
  get_emp_info(101, v_name, v_salary, v_dept);
  DBMS_OUTPUT.PUT_LINE(v_name || ' | ' || v_salary || ' | ' || v_dept);
END;
/






















-- 함수 생성: 직원 연봉 계산
CREATE OR REPLACE FUNCTION get_annual_sal (
  p_emp_id IN employees.employee_id%TYPE
)
RETURN NUMBER
IS
  v_monthly  employees.salary%TYPE;
  v_comm     employees.commission_pct%TYPE;
BEGIN
  SELECT salary, NVL(commission_pct, 0)
  INTO   v_monthly, v_comm
  FROM   employees
  WHERE  employee_id = p_emp_id;

  RETURN (v_monthly + v_monthly * v_comm) * 12;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN 0;
END get_annual_sal;
/

-- 함수 호출 1: SELECT 절에서 사용
SELECT employee_id,
       first_name,
       salary,
       get_annual_sal(employee_id) AS 연봉
FROM   employees
WHERE  department_id = 80
  AND  ROWNUM <= 5
ORDER  BY 연봉 DESC;

-- 함수 호출 2: PL/SQL 블록에서 사용
DECLARE
  v_ann NUMBER;
BEGIN
  v_ann := get_annual_sal(145);
  DBMS_OUTPUT.PUT_LINE('145번 연봉: ' || TO_CHAR(v_ann,'999,999,999'));
END;
/







select * from user_source where name = 'RAISE_SALARY';

























-- 패키지 명세 (공개 API 선언)
CREATE OR REPLACE PACKAGE pkg_emp_mgmt AS
  -- 공개 상수
  c_max_raise CONSTANT NUMBER := 0.3;

  -- 공개 프로시저·함수 선언
  PROCEDURE raise_salary(p_emp_id IN NUMBER, p_pct IN NUMBER DEFAULT 0.1);
  FUNCTION  get_annual_sal(p_emp_id IN NUMBER) RETURN NUMBER;
  PROCEDURE print_dept_summary(p_dept_id IN NUMBER);
END pkg_emp_mgmt;
/

-- 패키지 본문 (구현)
CREATE OR REPLACE PACKAGE BODY pkg_emp_mgmt AS

  PROCEDURE raise_salary(p_emp_id IN NUMBER, p_pct IN NUMBER DEFAULT 0.1) IS
  BEGIN
    IF p_pct > c_max_raise THEN
      RAISE_APPLICATION_ERROR(-20001, '인상률이 최대치를 초과합니다.');
    END IF;
    UPDATE employees SET salary = salary * (1 + p_pct)
    WHERE  employee_id = p_emp_id;
    COMMIT;
  END raise_salary;

  FUNCTION get_annual_sal(p_emp_id IN NUMBER) RETURN NUMBER IS
    v_sal NUMBER;
  BEGIN
    SELECT salary * 12 INTO v_sal FROM employees WHERE employee_id = p_emp_id;
    RETURN v_sal;
  EXCEPTION WHEN NO_DATA_FOUND THEN RETURN 0;
  END get_annual_sal;

  PROCEDURE print_dept_summary(p_dept_id IN NUMBER) IS
  BEGIN
    FOR r IN (SELECT first_name, salary FROM employees
              WHERE department_id = p_dept_id ORDER BY salary DESC) LOOP
      DBMS_OUTPUT.PUT_LINE(r.first_name || ': ' || r.salary);
    END LOOP;
  END print_dept_summary;

END pkg_emp_mgmt;
/

-- 패키지 실행
EXEC pkg_emp_mgmt.raise_salary(103, 0.05);
SELECT pkg_emp_mgmt.get_annual_sal(100) FROM DUAL;
EXEC pkg_emp_mgmt.print_dept_summary(90);













--- 트리거
-- 자동으로 실행되는 프로시저

CREATE TABLE EMP01 (
  EMPNO   NUMBER(4)    PRIMARY KEY,
  ENAME   VARCHAR2(20) NOT NULL,
  JOB     VARCHAR2(20)
);


CREATE OR REPLACE TRIGGER TRG_01
AFTER INSERT                   -- INSERT 완료 후 실행
ON EMP01                       -- 대상 테이블
-- FOR EACH ROW 생략 → 문장 레벨 트리거
BEGIN
  DBMS_OUTPUT.PUT_LINE('신입사원이 입사했습니다.');
END;
/


INSERT INTO EMP01 VALUES(1, '전원지', '화가');
-- 출력: 신입사원이 입사했습니다.
 
-- 여러 건 INSERT 해도 트리거는 1회만 실행 (문장 레벨)
INSERT INTO EMP01 VALUES(2, '홍길동', '개발자');
-- 출력: 신입사원이 입사했습니다.  ← 문장당 1번

select * from emp01;



CREATE SEQUENCE SAL01_SALNO_SEQ;  -- 급여번호 자동 채번
CREATE TABLE SAL01 (
  SALNO   NUMBER(4)   PRIMARY KEY,
  SAL     NUMBER(7,2),
  EMPNO   NUMBER(4)   REFERENCES EMP01(EMPNO)
);
 

create or replace trigger trg_02
after insert
on emp01 for each row
begin 
    insert into sal01 values(
        sal01_salno_seq.nextval,
        100,
        :new.empno
    );
    dbms_output.put_line('자동으로 sal01테이블 삽입');
end;
/


INSERT INTO EMP01 VALUES(3, '전수빈', '프로그래머');
INSERT INTO EMP01 VALUES(4, '12', '1');
SELECT * FROM EMP01;    -- 사원 확인
SELECT * FROM SAL01;    -- 급여 자동 생성 확인




-- 직원이 삭제되면 sal01의 값이 자동으로 제거
desc sal01;
create or replace trigger trg03
after delete on emp01 for each row
begin
    delete from sal01
    where empno = :old.empno;
end;
/

delete from emp01 where empno = 3;




































--- 트리거 실습

create table product (
    id number primary key,
    name varchar2(20) not null,
    company varchar2(20),
    price number,
    amount number
);

drop table product;

-- 상품 테이블
CREATE TABLE 상품 (
  상품코드   CHAR(6)       PRIMARY KEY,
  상품명     VARCHAR2(12)  NOT NULL,
  제조사     VARCHAR2(12),
  소비자가격 NUMBER(8),
  재고수량   NUMBER        DEFAULT 0
);
 
-- 입고 테이블
CREATE TABLE 입고 (
  입고번호   NUMBER(6)  PRIMARY KEY,
  상품코드   CHAR(6)    REFERENCES 상품(상품코드),
  입고일자   DATE       DEFAULT SYSDATE,
  입고수량   NUMBER(6),
  입고단가   NUMBER(8),
  입고금액   NUMBER(8)
);
 
-- 샘플 데이터
INSERT INTO 상품(상품코드,상품명,제조사,소비자가격) VALUES('A00001','세탁기','LG',500);
INSERT INTO 상품(상품코드,상품명,제조사,소비자가격) VALUES('A00002','컴퓨터','LG',700);
INSERT INTO 상품(상품코드,상품명,제조사,소비자가격) VALUES('A00003','냉장고','삼성',600);
COMMIT;

CREATE OR REPLACE TRIGGER TRG_04
AFTER INSERT ON 입고
FOR EACH ROW
BEGIN
  UPDATE 상품
  SET    재고수량 = 재고수량 + :NEW.입고수량
  WHERE  상품코드 = :NEW.상품코드;
END;
/
 
-- 실행 확인
INSERT INTO 입고(입고번호,상품코드,입고수량,입고단가,입고금액) VALUES(1,'A00001',5,320,1600);
SELECT * FROM 상품;    -- 세탁기 재고수량: 0 → 5

CREATE OR REPLACE TRIGGER TRG_05
AFTER UPDATE ON 입고
FOR EACH ROW
BEGIN
  UPDATE 상품
  SET    재고수량 = 재고수량 + (-:OLD.입고수량 + :NEW.입고수량)
                           -- 기존 수량 빼고 새 수량 더함
  WHERE  상품코드 = :NEW.상품코드;
END;
/
 
-- 실행 확인: 입고번호 1번 수량을 5 → 10으로 변경
UPDATE 입고 SET 입고수량=10, 입고금액=3200 WHERE 입고번호=1;
SELECT * FROM 상품;    -- 세탁기 재고수량: 5 → 10


CREATE OR REPLACE TRIGGER TRG_06
AFTER DELETE ON 입고
FOR EACH ROW
BEGIN
  UPDATE 상품
  SET    재고수량 = 재고수량 - :OLD.입고수량
  WHERE  상품코드 = :OLD.상품코드;
END;
/
 
-- 실행 확인: 입고번호 1번 삭제
DELETE FROM 입고 WHERE 입고번호=1;
SELECT * FROM 상품;    -- 세탁기 재고수량: 10 → 0





-- 트리거 삭제
DROP TRIGGER TRG_06;
 
-- 트리거 활성화 / 비활성화
ALTER TRIGGER TRG_04 DISABLE;  -- 일시 중지
ALTER TRIGGER TRG_04 ENABLE;   -- 다시 활성화
 
-- 테이블의 모든 트리거 비활성화
ALTER TABLE 입고 DISABLE ALL TRIGGERS;
ALTER TABLE 입고 ENABLE  ALL TRIGGERS;

-- 생성된 트리거 목록 조회
SELECT trigger_name, trigger_type, triggering_event,
       table_name, status
FROM   user_triggers
ORDER BY table_name;


