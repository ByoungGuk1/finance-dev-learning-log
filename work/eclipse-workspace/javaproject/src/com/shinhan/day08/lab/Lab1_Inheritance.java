package com.shinhan.day08.lab;
/**
 * =====================================================
 * [초급 LAB 1] 상속 기초 — 은행 직원 클래스 계층
 * 모듈: Module 02 - 7장 상속
 * 난이도: ★☆☆
 * 목표: 1. extends로 부모-자식 클래스 관계 선언
 *       2. super()로 부모 생성자 호출
 *       3. @Override로 메서드 재정의
 *       4. 자동 타입 변환 체험
 * 선행지식: 6장 클래스 (필드, 생성자, 메서드)
 * 예상시간: 40분
 * =====================================================
 *
 * 클래스 구조:
 *   Employee (부모)
 *   ├── Teller    (자식1) — 창구 직원
 *   └── Manager  (자식2) — 지점장
 */

// =====================================================
// Employee.java — 부모 클래스 (완성되어 있음, 수정 금지)
// =====================================================
class Employee {

    private String name;       // 이름
    private String employeeId; // 사번
    private int    baseSalary; // 기본급

    public Employee(String name, String employeeId, int baseSalary) {
        this.name       = name;
        this.employeeId = employeeId;
        this.baseSalary = baseSalary;
        System.out.println("[직원등록] " + name + " (" + employeeId + ")");
    }

    public String getName()       { return name;       }
    public String getEmployeeId() { return employeeId; }
    public int    getBaseSalary() { return baseSalary; }

    public void introduce() {
        System.out.println("안녕하세요. 저는 " + name + " 직원입니다.");
    }

    public int calculatePay() {
        return baseSalary;
    }

    @Override
    public String toString() {
        return String.format("Employee{이름=%s, 사번=%s, 기본급=%,d}",
                name, employeeId, baseSalary);
    }
}


// =====================================================
// Teller.java — 자식 클래스1 (창구 직원)
// =====================================================
class Teller extends Employee {

    // TODO [★☆☆] 필드를 추가하세요 (3분)
    // - branchName    : String  (담당 지점명, private)
    // - windowNumber  : int     (창구 번호, private)
	private String branchName;
	private int windowNumber;

    // TODO [★☆☆] 생성자를 작성하세요 (7분)
    // 매개변수: name, employeeId, baseSalary, branchName, windowNumber
    // 조건: 첫 줄에 super(name, employeeId, baseSalary) 호출
    //       this.branchName = branchName;
    //       this.windowNumber = windowNumber;
    //       "[창구배정] 지점명 N번 창구" 출력
	//	
    // public Teller(String name, String employeeId, int baseSalary,
    //               String branchName, int windowNumber) {
    //     super(...);
    //     ...
    // }
	public Teller(String name, String employeeId, int baseSalary, String branchName, int windowNumber) {
		super(name, employeeId, baseSalary);
		this.branchName = branchName;
		this.windowNumber = windowNumber;
		System.out.println("[창구배정] " + branchName + " " + windowNumber + "번 창구");
	}


    // TODO [★★☆] introduce() 메서드를 오버라이딩하세요 (8분)
    // 조건1: @Override 어노테이션 사용
    // 조건2: super.introduce() 먼저 호출 (부모 메서드 재사용)
    // 조건3: 그 다음 "담당 지점: 지점명 N번 창구입니다." 출력
    //
    // @Override
    // public void introduce() {
    //     super.introduce();
    //     ...
    // }
	@Override
	public void introduce() {
		super.introduce();
		System.out.println("담당 지점: " + branchName + " " + windowNumber + "번 창구입니다.");
	}


    // TODO [★★☆] calculatePay() 메서드를 오버라이딩하세요 (7분)
    // 창구 직원 급여 = 기본급 + 창구수당(200,000원)
    // 조건: super.calculatePay() 활용
    // 출력: "[급여계산] 홍길동(창구직원): 기본급 3,000,000 + 창구수당 200,000 = 3,200,000"
    // 반환: 최종 급여 (int)
	@Override
	public int calculatePay() {
		int result = 0;
		result = super.calculatePay() + 200_000;
		System.out.println("[급여계산] "+getName()+"(창구직원): 기본급 "+getBaseSalary()+" + 창구수당 200,000 = "+result);
		return result;
	}

	
    // TODO [★☆☆] Getter를 작성하세요 (2분)
    // getBranchName(), getWindowNumber()
	public String getBranchName() {
		return branchName;
	}
	public int getWindowNumber() {
		return windowNumber;
	}
}


// =====================================================
// Manager.java — 자식 클래스2 (지점장)
// =====================================================
class Manager extends Employee {

    // TODO [★☆☆] 필드를 추가하세요 (3분)
    // - branchName     : String  (담당 지점명, private)
    // - teamSize       : int     (팀원 수, private)
	private String branchName;
	private int teamSize;

	
    // TODO [★☆☆] 생성자를 작성하세요 (5분)
    // 매개변수: name, employeeId, baseSalary, branchName, teamSize
    // 조건: super() 첫 줄 호출, "[지점장배정] 지점명 지점" 출력
	public Manager(String name, String employeeId, int baseSalary, String branchName, int teamSize) {
		super(name, employeeId, baseSalary);
		this.branchName = branchName;
		this.teamSize = teamSize;
		System.out.println("[지점장배정] "+branchName+" 지점");
	}

	
    // TODO [★★☆] introduce() 오버라이딩 (5분)
    // super.introduce() 호출 후
    // "담당 지점: 지점명 지점 (팀원 N명)" 출력
	@Override
	public void introduce() {
		super.introduce();
		System.out.println("담당 지점: "+branchName+" 지점 (팀원 "+teamSize+"명)");
	}


    // TODO [★★☆] calculatePay() 오버라이딩 (7분)
    // 지점장 급여 = 기본급 + 관리수당(팀원수 × 100,000원)
    // 출력: "[급여계산] 김부장(지점장): 기본급 5,000,000 + 관리수당 500,000 = 5,500,000"
    // 반환: 최종 급여 (int)
	@Override
	public int calculatePay() {
		int result = 0;
		int bounus = teamSize * 100_000;
		result = super.calculatePay() + bounus;
		System.out.println("[급여계산] "+getName()+"(지점장): 기본급 "+getBaseSalary()+" + 관리수당 "+bounus+" = "+result);
		return result;
	}
	

    // TODO [★☆☆] Getter를 작성하세요 (2분)
    // getBranchName(), getTeamSize()
	public String getBranchName() {
		return branchName;
	}
	public int getTeamSize() {
		return teamSize;
	}
}


// =====================================================
// Lab1Main — 실행 및 결과 확인
// =====================================================
public class Lab1_Inheritance {

    public static void main(String[] args) {

        System.out.println("========== [1] 객체 생성 & super() 확인 ==========");
        // TODO [★☆☆] Teller, Manager 객체를 각각 1개씩 생성하세요 (3분)
        // t1: "홍길동", "T001", 3_000_000, "강남지점", 3
        Teller t1 = new Teller("홍길동", "T001", 3_000_000, "강남지점", 3);
        // m1: "김부장", "M001", 5_000_000, "강남지점", 5
        Manager m1 = new Manager("김부장", "M001", 5_000_000, "강남지점", 5);
        // ⚠️ 생성 시 콘솔에 출력되는 순서 확인
        //    → 부모 생성자([직원등록]) 먼저, 자식 생성자([창구배정]) 나중


        System.out.println("\n========== [2] 오버라이딩 — introduce() ==========");
        // TODO: t1.introduce(), m1.introduce() 호출
        t1.introduce();
        m1.introduce();
        // → super.introduce() 덕분에 부모 출력 후 자식 출력 확인


        System.out.println("\n========== [3] 급여 계산 ==========");
        // TODO: t1.calculatePay(), m1.calculatePay() 호출 및 결과 출력
        t1.calculatePay();
        m1.calculatePay();


        System.out.println("\n========== [4] 자동 타입 변환 ==========");
        // TODO [★★☆] 아래 코드를 완성하고 동작을 확인하세요 (5분)
        // Employee e = t1;        // 자식 → 부모 자동 변환
        Employee e = t1;
        // e.introduce();          // 어떤 introduce()가 실행될까? (오버라이딩된 것!)
        e.introduce();
        // e.calculatePay();       // 어떤 calculatePay()가 실행될까?
        e.calculatePay();
//        e.getWindowNumber();	//	부모 타입으로는 자식 고유 메서드 접근 불가


        System.out.println("\n========== [5] toString() 상속 확인 ==========");
        // TODO: System.out.println(t1), System.out.println(m1) 출력
        // → 부모의 toString()이 그대로 상속됨을 확인
        System.out.println(t1);
        System.out.println(m1);
    }
}
