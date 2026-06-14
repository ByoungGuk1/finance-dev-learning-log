## INDEX

조회의 속도를 높이기 위해 사용

자동 생성: PRIMARY KEY, UNIQUE 제약조건 생성 시 자동으로 인덱스 생성  
수동 생성: 자주 WHERE·JOIN·ORDER BY에 사용되는 컬럼에 직접 생성

인덱스가 항상 빠른 것은 아니다:  
• 전체 행의 10~15% 이상 반환 시 Full Table Scan이 더 빠를 수 있음  
• DML(INSERT/UPDATE/DELETE) 시 인덱스도 함께 갱신되므로 성능 비용 발생

| 인덱스 종류    | 설명                          | 생성예시                                          |
| -------------- | ----------------------------- | ------------------------------------------------- |
| B-Tree (기본)  | 가장 일반적, 범위 검색 효율적 | CREATE INDEX idx_salary ON employees(salary)      |
| Unique Index   | 컬럼 값의 유일성 보장         | CREATE UNIQUE INDEX uq_email ON emp(email)        |
| 복합 Index     | 여러 컬럼 조합                | CREATE INDEX idx_dept_sal ON emp(dept_id, salary) |
| Function-based | 함수 적용 컬럼                | CREATE INDEX idx_upper ON emp(UPPER(last_name))   |

```SQL
select *
from employees
--where salary between 10000 and 20000;
where salary = 10000;
-- ① 통계 확인: employees 총 107행
-- ② 조건 분석: salary BETWEEN 10000 AND 20000
-- ③ 예측 반환 행: 약 35행 (전체의 약 33%)
-- ④ 비교:
--    INDEX RANGE SCAN COST  =  인덱스 탐색 + 35번 랜덤 I/O
--    FULL TABLE SCAN  COST  =  전체 블록 멀티블록 순차 읽기
-- ⑤ 결론: 작은 테이블 + 높은 선택률 → Full Scan이 저렴
```

자동으로 성능 비교 후 INDEX를 사용할지 말지 알아서 결정 후 진행

---

cmd창에서 alt 누르고 드래그 하면 세로로 긁어짐

eclipse에서 ctrl + y 소문자화

---

## view

1. 가상의 table (select문 저장)
2. 복잡한 sql을 반복 사용 시 단순화 하기 위해
3. 보안(특정 부서에 특정 column만 공개하기 위해)

## synonym

다른 객체(테이블, 뷰, 시퀀스 등)에 대한 별칭(Alias)을 만드는 객체입니다.

PUBLIC SYNONYM : 모든 사용자가 접근 가능 (DBA 권한 필요)  
PRIVATE SYNONYM: 생성한 사용자만 접근 가능

활용 예: HR.EMPLOYEES 대신 EMPLOYEES로 접근하거나,
긴 테이블명을 짧게 줄여 편의성 향상

대표적인 예시 : dual
sys라는 DBA가 dual table를 공개 시노님으로 만들어서 제공
