# Python 학습 내용 정리

## 1. PowerShell에서 conda를 인식하지 못하는 문제

CMD에서는 `conda`가 실행되지만 PowerShell에서 다음 오류가 발생했다.

```text
conda: The term 'conda' is not recognized...
```

CMD에서 PowerShell용 conda 초기화를 실행한다.

```cmd
conda init powershell
```

이후 PowerShell 또는 VS Code를 완전히 종료했다가 다시 실행한다.

```powershell
conda --version
```

---

## 2. requirements.txt 파일을 찾지 못하는 문제

다음 명령은 현재 폴더에 `requirements.txt`가 있어야 실행된다.

```powershell
python -m pip install -r requirements.txt
```

다음 오류는 현재 폴더에 파일이 없다는 뜻이다.

```text
Could not open requirements file:
No such file or directory: 'requirements.txt'
```

하위 폴더에서 파일을 검색한다.

```powershell
Get-ChildItem -Recurse -Filter requirements.txt
```

파일을 찾았다면 경로를 지정한다.

```powershell
python -m pip install -r .\폴더명\requirements.txt
```

---

## 3. 딕셔너리에서 값 조회하기

잘못된 코드:

```python
rate.values[grade]
```

`rate.values`는 메서드이므로 `[]`로 조회할 수 없다.

```text
TypeError: 'builtin_function_or_method' object is not subscriptable
```

등급이 딕셔너리의 키라면 직접 조회한다.

```python
rate[grade]
```

수정 코드:

```python
def calc_interest(balance, grade):
    rate = {
        "VIP": 0.03,
        "GOLD": 0.02,
        "SILVER": 0.01
    }

    return rate[grade] * balance
```

퍼센트는 다음과 같이 소수로 표현한다.

```text
3%  = 0.03
2%  = 0.02
1%  = 0.01
30% = 0.3
```

---

## 4. Jupyter Notebook의 이전 실행 결과

Jupyter Notebook은 코드를 수정해도 셀을 자동으로 다시 실행하지 않는다.

코드를 수정한 뒤 다음 단축키로 셀을 다시 실행한다.

```text
Shift + Enter
```

이전 코드의 오류나 출력이 계속 남으면 커널을 재시작하고 셀을 다시 실행한다.

```text
Restart Kernel → Run All
```

현재 화면의 코드와 출력 결과가 다르다면 이전 실행 결과가 남았는지 확인한다.

---

## 5. 등급별 인원수와 잔액 합계 구하기

딕셔너리를 반복문 안에서 다음처럼 다시 대입하면 기존 내용이 사라진다.

```python
for account in accounts:
    count_by_grade = {get_grade(account["balance"]): 0}
```

마지막 반복에서 만든 딕셔너리만 남기 때문에 다른 등급을 조회하면 `KeyError`가 발생할 수 있다.

```text
KeyError: 'VIP'
```

딕셔너리는 반복문 전에 한 번 만들고, 등급별 값을 누적한다.

```python
accounts = [
    {"name": "홍길동1", "balance": 52000100},
    {"name": "김민수1", "balance": 8003000},
    {"name": "홍길동2", "balance": 52000200},
    {"name": "김민수2", "balance": 8002000},
    {"name": "홍길동3", "balance": 52000300},
    {"name": "김민수3", "balance": 8001000},
]

count_by_grade = {}
sum_by_grade = {}

for account in accounts:
    grade = get_grade(account["balance"])

    if grade not in count_by_grade:
        count_by_grade[grade] = 0
        sum_by_grade[grade] = 0

    count_by_grade[grade] += 1
    sum_by_grade[grade] += account["balance"]
```

결과:

```python
count_by_grade
# {'VIP': 3, 'SILVER': 3}

sum_by_grade
# {'VIP': 156000600, 'SILVER': 24006000}
```

평균 잔액 계산:

```python
avg_by_grade = {}

for grade in count_by_grade:
    avg_by_grade[grade] = (
        sum_by_grade[grade] / count_by_grade[grade]
    )
```

---

## 6. Python 생성자와 self

Python의 생성자는 `__init__`이다.

```python
class BankEmployee:
    def __init__(self, name, salary):
        self.name = name
        self.salary = salary
```

생성자를 정의할 때는 첫 번째 매개변수로 `self`를 작성한다.

```python
def __init__(self, name, salary):
```

객체를 생성하거나 메서드를 호출할 때는 `self`를 직접 전달하지 않는다.

```python
employee = BankEmployee("홍길동", 3_000_000)
```

Python이 생성된 객체를 `self` 자리에 자동으로 전달한다.

```text
정의할 때: self 작성
호출할 때: self 생략
```

---

## 7. 클래스에는 생성자를 하나만 정의한다

Python은 Java와 같은 생성자 오버로딩을 지원하지 않는다.

```python
class BankEmployee:
    def __init__(self):
        pass

    def __init__(self, name, salary):
        self.name = name
        self.salary = salary
```

위 코드에서는 두 번째 `__init__`이 첫 번째 `__init__`을 덮어쓴다.

따라서 클래스마다 `__init__`을 하나만 작성한다.

---

## 8. 매개변수 0개, 1개, 2개 처리하기

여러 생성자를 만들지 않고 기본 매개변수를 사용한다.

```python
class BankEmployee:
    def __init__(self, name=None, salary=0):
        self.name = name
        self.salary = salary
```

다음 세 가지 방식으로 생성할 수 있다.

```python
employee1 = BankEmployee()
employee2 = BankEmployee("홍길동")
employee3 = BankEmployee("김민수", 3_000_000)
```

전달되는 값:

```text
BankEmployee()
→ name=None, salary=0

BankEmployee("홍길동")
→ name="홍길동", salary=0

BankEmployee("김민수", 3_000_000)
→ name="김민수", salary=3000000
```

---

## 9. 상속과 super()

`super()`는 상속 순서에서 다음 클래스의 메서드를 호출할 때 사용한다.

부모 클래스:

```python
class BankEmployee:
    def __init__(self, name, salary):
        self.name = name
        self.salary = salary

    def work(self):
        print("은행 업무")
```

자식 클래스:

```python
class Teller(BankEmployee):
    def __init__(self, name, salary):
        super().__init__(name, salary)

    def work(self):
        super().work()
        print("입출금 창구 업무")
```

부모 생성자를 호출할 때는 `self`를 전달하지 않는다.

```python
super().__init__(name, salary)
```

잘못된 코드:

```python
super().__init__(self, name, salary)
```

`super()`가 현재 객체를 부모 생성자의 `self` 자리에 자동으로 전달한다.

---

## 10. 자식 클래스의 생성자를 생략할 수 있는 경우

자식 클래스가 부모와 동일하게 초기화된다면 `__init__`을 생략할 수 있다.

```python
class Teller(BankEmployee):
    def work(self):
        super().work()
        print("입출금 창구 업무")


class LoanOfficer(BankEmployee):
    pass
```

부모 생성자를 그대로 상속받는다.

```python
teller = Teller("홍길동", 3_000_000)
loan_officer = LoanOfficer("김민수", 4_000_000)
```

자식만의 속성이 필요할 때 생성자를 정의한다.

```python
class Teller(BankEmployee):
    def __init__(self, name, salary, counter_number):
        super().__init__(name, salary)
        self.counter_number = counter_number
```

정리:

```text
부모와 초기화 방식이 같음
→ 자식의 __init__ 생략

자식만의 속성이 있음
→ 자식의 __init__ 작성
→ super().__init__(부모에게 필요한 값) 호출
```

---

## 11. Python 객체와 JSON 변환

파일에 JSON을 저장할 때:

```python
json.dump()
```

JSON 파일을 읽을 때:

```python
json.load()
```

문자열로 변환할 때는 함수 이름 뒤에 `s`가 붙는다.

| 작업                      | 함수           |
| ------------------------- | -------------- |
| Python 객체 → JSON 파일   | `json.dump()`  |
| JSON 파일 → Python 객체   | `json.load()`  |
| Python 객체 → JSON 문자열 | `json.dumps()` |
| JSON 문자열 → Python 객체 | `json.loads()` |

파일 저장:

```python
import json

data = {
    "name": "홍길동",
    "age": 20
}

with open("data.json", "w", encoding="utf-8") as file:
    json.dump(data, file, ensure_ascii=False, indent=2)
```

파일 읽기:

```python
with open("data.json", "r", encoding="utf-8") as file:
    data = json.load(file)
```

---

## 12. API 통신에서 JSON 사용하기

API 통신에서는 보통 `dump()`나 `load()`를 직접 사용하지 않고 HTTP 라이브러리가 변환을 처리한다.

GET 요청:

```python
import requests

response = requests.get(
    "https://api.example.com/users/1",
    timeout=5
)

response.raise_for_status()

data = response.json()
print(data["name"])
```

`response.json()`은 응답받은 JSON 문자열을 Python 객체로 변환한다.

개념적으로 다음과 비슷하다.

```python
data = json.loads(response.text)
```

POST 요청:

```python
payload = {
    "name": "홍길동",
    "age": 20
}

response = requests.post(
    "https://api.example.com/users",
    json=payload,
    timeout=5
)

response.raise_for_status()
result = response.json()
```

`json=payload`를 사용하면 라이브러리가 다음 작업을 처리한다.

```text
Python 딕셔너리 → JSON 변환
Content-Type: application/json 설정
HTTP 요청 전송
```

---

## 13. 계좌 정보를 JSON 파일로 저장하기

딕셔너리의 키는 문자열로 작성한다.

```python
account = {
    "account_no": "12-12-11111",
    "amount": 1
}
```

딕셔너리 값은 `[]`로 접근한다.

```python
account["account_no"]
account["amount"]
```

객체 속성처럼 접근하면 안 된다.

```python
# 잘못된 코드
account.due_date

# 올바른 코드
account["due_date"]
```

전체 예제:

```python
import re
import datetime
import json


def is_valid_account(account_no):
    pattern = r"^\d{2,3}-\d{2,3}-\d{5,7}$"
    return bool(re.match(pattern, account_no))


now = datetime.datetime.now()

account = {
    "account_no": "12-12-11111",
    "amount": 1,
    "checked_at": now.strftime("%Y-%m-%d"),
    "due_date": None
}

if is_valid_account(account["account_no"]):
    due_date = now + datetime.timedelta(days=90)
    account["due_date"] = due_date.strftime("%Y-%m-%d")

    with open("result.json", "w", encoding="utf-8") as file:
        json.dump(
            account,
            file,
            ensure_ascii=False,
            indent=2
        )

    print("result.json 저장 완료")
else:
    print("유효하지 않은 계좌번호입니다.")
```

---

## 14. 날짜 형식 주의사항

연월일을 출력하려면 다음 형식을 사용한다.

```python
strftime("%Y-%m-%d")
```

각 기호의 의미:

```text
%Y: 네 자리 연도
%m: 두 자리 월
%d: 두 자리 일
```

`%D`와 `%d`는 의미가 다르다.

```python
"%Y-%m-%d"  # 2026-09-07
"%D"        # 09/07/26
```

날짜 계산 후 문자열로 변환하려면 계산 결과에 `strftime()`을 적용한다.

```python
due_date = (
    datetime.datetime.now() + datetime.timedelta(days=90)
).strftime("%Y-%m-%d")
```

---

## 핵심 요약

```text
딕셔너리 값 조회
→ dictionary[key]

생성자 정의
→ def __init__(self, ...)

생성자와 메서드 호출
→ self를 직접 전달하지 않음

부모 생성자 호출
→ super().__init__(값)

여러 형태의 생성자
→ 생성자를 여러 개 만들지 않고 기본 매개변수 사용

JSON 파일 저장과 읽기
→ dump() / load()

JSON 문자열 변환
→ dumps() / loads()

API 응답 JSON 변환
→ response.json()

날짜의 일
→ %d
```
