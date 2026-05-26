package com.shinhan.day08.lab;

/**
 * =====================================================
 * [고급 LAB 5] 상속 계층 설계 — 금융 상품 관리 시스템
 * 모듈: Module 02 - 7장 상속
 * 난이도: ★★★
 * 목표: 1. 3단계 상속 계층 설계 (조부모→부모→자식)
 *       2. 추상 클래스 + 구체 클래스 혼합
 *       3. 다형성으로 상품 포트폴리오 관리
 *       4. 강제 타입 변환 + instanceof 패턴매칭 활용
 *       5. final 클래스로 상속 제한
 * 선행지식: 7장 전체 (상속, 추상, 다형성, instanceof, final)
 * 예상시간: 80분
 * =====================================================
 *
 * 3단계 상속 계층:
 *
 *  FinancialAsset (추상, 최상위)
 *  ├── DepositProduct (추상, 예금 계열)
 *  │   ├── DemandDeposit     (보통예금, 구체)
 *  │   └── TimeDeposit       (정기예금, final — 더 이상 상속 불가)
 *  └── InvestmentProduct (추상, 투자 계열)
 *      ├── StockFund         (주식형 펀드, 구체)
 *      └── BondFund          (채권형 펀드, 구체)
 *
 *  Portfolio — 포트폴리오 (FinancialAsset[] 다형성)
 *  RiskAnalyzer — 위험도 분석기 (매개변수 다형성)
 */

enum RiskType{
	LOW,MED,HIGH
}

// =====================================================
// FinancialAsset — 최상위 추상 클래스
// =====================================================
abstract class FinancialAsset {

    // TODO [★☆☆] 공통 필드 선언 (5분)
    // - assetId      : final String  (자산번호, protected)
    // - productName  : String        (상품명, protected)
    // - investAmount : int           (투자금액, protected)
    // - ownerName    : String        (소유자, protected)
	protected final String assetId;
	protected String productName;
	protected int investAmount;
	protected String ownerName;
	

    // TODO [★☆☆] 생성자 (3분)
    // 매개변수: assetId, productName, investAmount, ownerName
    // "[자산등록] assetId | productName | ownerName" 출력
    public FinancialAsset(String assetId, String productName, int investAmount, String ownerName) {
		this.assetId = assetId;
		this.productName = productName;
		this.investAmount = investAmount;
		this.ownerName = ownerName;
		System.out.println("[자산등록] "+assetId+" | "+productName+" | "+ownerName);
	}
    

    // TODO [★★☆] 추상 메서드 3개 선언 (3분)
    // - calculateProfit()  : int     — 수익/이자 계산
    // - getRiskLevel()     : String  — 위험도 반환 ("LOW"/"MED"/"HIGH")
    // - getAssetCategory() : String  — 자산분류 반환 ("예금"/"투자")
    public abstract int calculateProfit();
    public abstract RiskType getRiskLevel();
    public abstract String getAssetCategory();
    

    // TODO [★★☆] 공통 메서드 구현 (5분)
    // - getProfitRate() : double
    //   수익률(%) = calculateProfit() / investAmount * 100.0
    public double getProfitRate() {
		return (double)calculateProfit() / investAmount * 100.0;
	}
    
    // - printAssetInfo()
    //   출력:
    //   "[자산현황] assetId | ownerName"
    //   "  상품: productName | 분류: getAssetCategory()"
    //   "  투자금: N원 | 수익: N원 | 수익률: N.N%"
    //   "  위험도: getRiskLevel()"
    public void printAssetInfo() {
		System.out.println("[자산현황] "+assetId+" | "+ownerName);
		System.out.println("\t상품: "+productName+" | 분류: "+getAssetCategory());
		System.out.print("\t투자금: "+investAmount+"원 | 수익: "+calculateProfit()+"원 | 수익률: ");
		System.out.printf("%.1f%%\n",getProfitRate());
		System.out.println("\t위험도: "+getRiskLevel());
	}


	public String getAssetId()      { return assetId;      }
    public String getOwnerName()    { return ownerName;    }
    public int    getInvestAmount() { return investAmount; }
    public String getProductName()  { return productName;  }

    @Override
    public String toString() {
        return String.format("Asset{%s, %s, %s, %,d원}",
                assetId, productName, getAssetCategory(), investAmount);
    }
}


// =====================================================
// DepositProduct — 중간 추상 클래스 (예금 계열 공통)
// =====================================================
abstract class DepositProduct extends FinancialAsset {

    protected double interestRate; // 연이율 (%)
    protected int    termMonths;   // 약정기간 (개월)

    
    // TODO [★☆☆] 생성자 (3분)
    // 매개변수: assetId, productName, investAmount, ownerName, interestRate, termMonths
    // super(...) 호출
	public DepositProduct(String assetId, String productName, int investAmount, String ownerName, double interestRate,
			int termMonths) {
		super(assetId, productName, investAmount, ownerName);
		this.interestRate = interestRate;
		this.termMonths = termMonths;
	}


    // TODO [★☆☆] getAssetCategory() 구현 (1분)
    // 반환: "예금"
	@Override
	public String getAssetCategory() {
		return "예금";
	}


    // TODO [★☆☆] getRiskLevel() 구현 (1분)
    // 예금은 항상 LOW
    // 반환: "LOW"
	@Override
	public RiskType getRiskLevel() {
		return RiskType.LOW;
	}


    // TODO [★★☆] 예금 공통 메서드 구현 (5분)
    // - calculateSimpleInterest() : int  (단리 이자)
    //   이자 = investAmount × interestRate/100 × termMonths/12
	public int calculateSimpleInterest() {
		int interest = (int)(investAmount * interestRate/100 * termMonths/12);
		return interest;
	}
    // - printDepositDetail()
    //   "  [예금상세] 연이율: N.N% | 약정: N개월 | 만기이자: N원"
	public void printDepositDetail() {
		System.out.println("\t[예금상세] 연이율: "+interestRate+"% | 약정: "+termMonths+"개월 | 만기이자: "+calculateSimpleInterest()+"원");
	}
}


// =====================================================
// InvestmentProduct — 중간 추상 클래스 (투자 계열 공통)
// =====================================================
abstract class InvestmentProduct extends FinancialAsset {

    protected double expectedReturn; // 기대수익률 (%)
    protected double volatility;     // 변동성 (위험도 계산용)

    
    // TODO [★☆☆] 생성자 (3분)
    // 매개변수: assetId, productName, investAmount, ownerName, expectedReturn, volatility
    // super(...) 호출
	public InvestmentProduct(String assetId, String productName, int investAmount, String ownerName,
			double expectedReturn, double volatility) {
		super(assetId, productName, investAmount, ownerName);
		this.expectedReturn = expectedReturn;
		this.volatility = volatility;
	}


    // TODO [★☆☆] getAssetCategory() 구현 (1분)
    // 반환: "투자"
	@Override
	public String getAssetCategory() {
		return "투자";
	}


	// TODO [★★☆] getRiskLevel() 구현 (5분)
    // volatility(변동성)에 따라 위험도 결정:
    //   volatility < 5.0  → "LOW"
    //   volatility < 15.0 → "MED"
    //   그 외             → "HIGH"
	@Override
	public RiskType getRiskLevel() {
		RiskType risk = null;
		if(volatility < 5.0) {
			risk = RiskType.LOW;
		} else if(volatility < 15.0) {
			risk = RiskType.MED;
		} else {
			risk = RiskType.HIGH;
		}
		return risk;
	}
	

    // TODO [★★☆] 투자 공통 메서드 구현 (5분)
    // - calculateExpectedProfit() : int
    //   기대수익 = investAmount × expectedReturn / 100
	public int calculateExpectedProfit() {
		int com = (int)(investAmount * expectedReturn / 100);
		return com;
	}
    // - printInvestmentDetail()
    //   "  [투자상세] 기대수익률: N.N% | 변동성: N.N% | 위험도: N"
	public void printInvestmentDetail() {
		System.out.println("\t[투자상세] 기대수익률: "+expectedReturn+"% | 변동성: "+volatility+"% | 위험도: "+getRiskLevel());
	}
}


// =====================================================
// DemandDeposit — 보통예금 (구체 클래스)
// =====================================================
class DemandDeposit extends DepositProduct {

    private int monthsHeld;   // 실제 보유 개월수
    
    // TODO [★☆☆] 생성자 (3분)
    // 매개변수: assetId, ownerName, investAmount, monthsHeld
    // 상품명 "보통예금", 이율 1.5, termMonths = monthsHeld
    // super(assetId, "보통예금", investAmount, ownerName, 1.5, monthsHeld) 호출
	public DemandDeposit(String assetId, String ownerName, int investAmount, int monthsHeld) {
		this(assetId, "보통예금", investAmount, ownerName, 1.5, monthsHeld, monthsHeld);
	}
	public DemandDeposit(String assetId, String productName, int investAmount, String ownerName, double interestRate, int termMonths, int monthsHeld) {
		super(assetId, productName, investAmount, ownerName, interestRate, termMonths);
		this.monthsHeld = monthsHeld;
	}


    // TODO [★★☆] calculateProfit() 구현 (5분)
    // 단리 이자 사용 (부모 메서드 재사용)
    // 출력: "[보통예금 이자] ownerName: N원"
    // 반환: 이자
	@Override
	public int calculateProfit() {
		int result = 0;
		result = super.calculateSimpleInterest();
		System.out.println("["+productName+" 이자] "+ownerName+": "+result+"원");
		return result;
	}
	

    // TODO [★☆☆] printAssetInfo() 오버라이딩 (5분)
    // super.printAssetInfo() 호출 후
    // printDepositDetail() 추가 출력
	@Override
	public void printAssetInfo() {
		super.printAssetInfo();
		printDepositDetail();
	}
}


// =====================================================
// TimeDeposit — 정기예금 (final — 더 이상 상속 불가!)
// =====================================================
final class TimeDeposit extends DepositProduct {

    private boolean isMatured;   // 만기 여부


    // TODO [★☆☆] 생성자 (3분)
    // 매개변수: assetId, ownerName, investAmount, interestRate, termMonths
    // super(assetId, "정기예금", investAmount, ownerName, interestRate, termMonths)
    // isMatured = false
	public TimeDeposit(String assetId, String ownerName, int investAmount, double interestRate,	int termMonths) {
		this(assetId, "정기예금", investAmount, ownerName, interestRate, termMonths, false);
	}
	public TimeDeposit(String assetId, String productName, int investAmount, String ownerName, double interestRate, int termMonths, boolean isMatured) {
		super(assetId, productName, investAmount, ownerName, interestRate, termMonths);
		this.isMatured = isMatured;
	}
	

    // TODO [★★☆] calculateProfit() 구현 (5분)
    // 만기 전: 이자 × 0.5 (중도해지 패널티)
    // 만기 후: 정상 이자
    // 출력: "[정기예금] 만기여부에 따른 이자 출력"
	@Override
	public int calculateProfit() {
		int result = 0;
		if(!isMatured) {
			result = (int)(super.calculateSimpleInterest() * 0.5);
		} else {
			result = super.calculateSimpleInterest();
		}
		return result;
	}


    // TODO [★★☆] mature() 만기 처리 메서드 추가 (3분)
    // isMatured = true
    // "[만기처리] productName ownerName 만기이자: N원" 출력
	public boolean mature() {
		isMatured = true;
		System.out.println("[만기처리] "+productName+" "+ownerName+" 만기이자: "+calculateProfit()+"원");
		return isMatured;
	}

    // TODO [★☆☆] printAssetInfo() 오버라이딩 (3분)
    // super.printAssetInfo() + printDepositDetail() + 만기상태 출력
	@Override
	public void printAssetInfo() {
		super.printAssetInfo();
		printDepositDetail();
		System.out.println("\t만기상태 : " + isMatured);
	}
	
    // TODO: 아래 주석 해제 → 컴파일 오류 확인 (final 클래스 상속 불가)
//     class SpecialTimeDeposit extends TimeDeposit { } // ❌

	public boolean isMatured() {
		return isMatured;
	}
}


// =====================================================
// StockFund — 주식형 펀드 (HIGH risk)
// =====================================================
class StockFund extends InvestmentProduct {

    private String fundManager; // 펀드매니저

    
    // TODO [★☆☆] 생성자 (3분)
    // 매개변수: assetId, ownerName, investAmount, expectedReturn, fundManager
    // 변동성 고정: 20.0 (HIGH 위험)
    // super(assetId, "주식형펀드", investAmount, ownerName, expectedReturn, 20.0)
    public StockFund(String assetId, String ownerName, int investAmount, double expectedReturn, String fundManager) {
		this(assetId, "주식형펀드", investAmount, ownerName, expectedReturn, 20.0, fundManager);
	}
	public StockFund(String assetId, String productName, int investAmount, String ownerName, double expectedReturn,
			double volatility, String fundManager) {
		super(assetId, productName, investAmount, ownerName, expectedReturn, volatility);
		this.fundManager = fundManager;
	}
    

    // TODO [★★☆] calculateProfit() 구현 (5분)
    // 수익 = calculateExpectedProfit() (부모 메서드 재사용)
    // 출력: "[주식펀드] ownerName 기대수익: N원 (펀드매니저: fundManager)"
	@Override
	public int calculateProfit() {
	    int result = super.calculateExpectedProfit();
	    System.out.println("[" + productName + "] " + ownerName + " 기대수익: " + result + "원 (펀드매니저: " + fundManager + ")");
	    return result;
	}


    // TODO [★☆☆] printAssetInfo() 오버라이딩 (3분)
    // super.printAssetInfo() + printInvestmentDetail()
	@Override
	public void printAssetInfo() {
		super.printAssetInfo();
		super.printInvestmentDetail();
	}
	
}


// =====================================================
// BondFund — 채권형 펀드 (LOW~MED risk)
// =====================================================
class BondFund extends InvestmentProduct {

    private String bondGrade; // 채권등급 (AAA/AA/A)


    // TODO [★☆☆] 생성자 (3분)
    // 매개변수: assetId, ownerName, investAmount, expectedReturn, volatility, bondGrade
    // super(assetId, "채권형펀드", investAmount, ownerName, expectedReturn, volatility)
	public BondFund(String assetId, String ownerName, int investAmount, double expectedReturn, double volatility, String bondGrade) {
		this(assetId, "채권형펀드", investAmount, ownerName, expectedReturn, volatility, bondGrade);
	}
	public BondFund(String assetId, String productName, int investAmount, String ownerName, double expectedReturn, double volatility, String bondGrade) {
		super(assetId, productName, investAmount, ownerName, expectedReturn, volatility);
		this.bondGrade = bondGrade;
	}


    // TODO [★★☆] calculateProfit() 구현 (5분)
    // 채권등급에 따른 수익 조정:
    //   "AAA" → calculateExpectedProfit() × 0.9  (안전하지만 수익 낮음)
    //   "AA"  → calculateExpectedProfit() × 1.0
    //   "A"   → calculateExpectedProfit() × 1.1  (리스크 있지만 수익 높음)
    // 출력: "[채권펀드] ownerName 채권등급: bondGrade | 수익: N원"
	@Override
	public int calculateProfit() {
		int result = 0;
		switch(bondGrade) {
			case "AAA" ->{
				result = (int)(calculateExpectedProfit() * 0.9);
				}
			case "AA" ->{
				result = (int)(calculateExpectedProfit() * 1.0);
				}
			case "A"->{
				result = (int)(calculateExpectedProfit() * 1.1);
			}
			default ->{
				System.err.println("BondFund.calculateProfit() -> bondGrade 오류");
			}
		}
		return result;
	}

    // TODO [★☆☆] printAssetInfo() 오버라이딩 (3분)
    // super.printAssetInfo() + printInvestmentDetail() + "채권등급: bondGrade" 출력
	@Override
	public void printAssetInfo() {
		super.printAssetInfo();
		printInvestmentDetail();
		System.out.println("\t채권등급: " + bondGrade);
	}
	
	public String getBondGrade() {
		return bondGrade;
	}
}


// =====================================================
// Portfolio — 포트폴리오 (다형성 핵심)
// =====================================================
class Portfolio {

    private String ownerName;
    private FinancialAsset[] assets;
    private int assetCount = 0;

    
    // TODO [★☆☆] 생성자 (3분)
    // 매개변수: ownerName, maxAssets
    // assets = new FinancialAsset[maxAssets]
	public Portfolio(String ownerName, int maxAssets) {
		this.ownerName = ownerName;
		this.assets = new FinancialAsset[maxAssets];
	}


    // TODO [★★☆] addAsset(FinancialAsset asset) (5분)
    // assets[assetCount++] = asset
    // 꽉 찼으면 "포트폴리오 한도 초과" 출력
	public boolean addAsset(FinancialAsset asset) {
		boolean result = false;
		if(assetCount >= assets.length) {
			System.err.println("포트폴리오 한도 초과");
			return result;
		}
		assets[assetCount++] = asset;
		result = true;
		return result;
	}


    // TODO [★★★] printPortfolioSummary() (15분)
    // 전체 포트폴리오 요약:
    //   "======================================"
    //   "포트폴리오 소유자: ownerName"
    //   "총 자산 수: N개"
    //   "총 투자금액: N원"
    //   "총 기대수익: N원"
    //   "평균 수익률: N.N%"
    //   "--------------------------------------"
    //   "위험도 분포: LOW N개 | MED N개 | HIGH N개"
    //   "자산분류: 예금 N개 | 투자 N개"
    //   "======================================"
    // 힌트: 각 자산의 getInvestAmount(), calculateProfit(),
    //       getRiskLevel(), getAssetCategory() 활용
	public void printPortfolioSummary() {
		int totalInvestAmount = 0;	//	투자금액
		int totalExpectedReturn = 0;	//	기대수익
		double avgExpectedReturn = 0.0;
		int lowRiskCount = 0;
		int medRiskCount = 0;
		int highRiskCount = 0;
		int depositCount = 0;
		int investmentCount = 0;
		
		for(FinancialAsset asset : assets) {
			if(asset == null)	break;
			totalInvestAmount += asset.getInvestAmount();
			totalExpectedReturn += asset.calculateProfit();
			lowRiskCount = asset.getRiskLevel().equals(RiskType.LOW) ? ++lowRiskCount : lowRiskCount;
			medRiskCount = asset.getRiskLevel().equals(RiskType.MED) ? ++medRiskCount : medRiskCount;
			highRiskCount = asset.getRiskLevel().equals(RiskType.HIGH) ? ++highRiskCount : highRiskCount;
			if (asset instanceof DepositProduct) {
			    depositCount++;
			} else if (asset instanceof InvestmentProduct) {
			    investmentCount++;
			}
		}
		avgExpectedReturn = (double)totalExpectedReturn / assetCount;
		
		System.out.println("======================================");
		System.out.println("포트폴리오 소유자: "+ownerName);
		System.out.println("총 자산 수: "+assetCount+"개");
		System.out.println("총 투자금액: "+totalInvestAmount+"원");
		System.out.println("총 기대수익: "+totalExpectedReturn+"원");
		System.out.printf("평균 수익률: %.1f%%\n", avgExpectedReturn);
		System.out.println("--------------------------------------");
		System.out.println("위험도 분포: LOW "+lowRiskCount+"개 | MED "+medRiskCount+"개 | HIGH "+highRiskCount+"개");
		System.out.println("자산분류: 예금 " + depositCount + "개 | 투자 " + investmentCount + "개");
	}


    // TODO [★★★] printDetailReport() (10분)
    // 모든 자산의 printAssetInfo() 호출
    // + instanceof로 타입별 추가 정보 출력:
    //   DepositProduct → printDepositDetail()
    //   InvestmentProduct → printInvestmentDetail()
    //   TimeDeposit td → "만기여부: " + td.isMatured (강제 타입 변환)
	public void printDetailReport() {
		for(FinancialAsset asset : assets) {
			if(asset == null)	break;
			asset.printAssetInfo();
//			if(asset instanceof DepositProduct dp) {
//				dp.printDepositDetail();
//			}
//			if(asset instanceof InvestmentProduct ip) {
//				ip.printInvestmentDetail();
//			}
			if(asset instanceof TimeDeposit td) {
				System.out.println("\t만기여부: "+td.isMatured());
			}
		}
	}
}


// =====================================================
// RiskAnalyzer — 위험도 분석기 (매개변수 다형성)
// =====================================================
class RiskAnalyzer {

    // TODO [★★★] analyze(FinancialAsset asset) (10분)
    // FinancialAsset 타입으로 받아서 분석
    // 출력:
    //   "[리스크분석] productName"
    //   "  분류: getAssetCategory() | 위험도: getRiskLevel()"
    //   "  투자금: N원 | 기대수익: N원 | 수익률: N.N%"
    //
    // instanceof 패턴 매칭으로 타입별 추가 분석:
    //   DepositProduct dp → "  예금 안전등급: SAFE (원금보장)"
    //   StockFund sf      → "  주의: 고위험 상품. 원금손실 가능"
    //   BondFund bf       → "  채권등급: " + bf.bondGrade (강제타입변환 활용)
	public void analyze(FinancialAsset asset) {
		System.out.println("[리스크분석] "+asset.productName);
		System.out.println("\t분류: "+asset.getAssetCategory()+" | 위험도: "+asset.getRiskLevel());
		System.out.println("\t투자금: "+asset.investAmount+"원 | 기대수익: "+asset.calculateProfit()+"원 | 수익률: "+asset.getProfitRate()+"%");
		if(asset instanceof DepositProduct) {
			System.out.println("\t예금 안전등급: SAFE (원금보장)");
		}
		if(asset instanceof StockFund) {
			System.out.println("\t주의: 고위험 상품. 원금손실 가능");
		}
		if(asset instanceof BondFund bf) {
			System.out.println("\t채권등급: " + bf.getBondGrade());
		}
	}

    // TODO [★★★] analyzePortfolio(FinancialAsset[] assets, int count) (8분)
    // 모든 자산을 analyze()로 분석 후
    // 위험도별 분포 출력:
    //   "[포트폴리오 위험도 분석]"
    //   "LOW: N개(N%) | MED: N개(N%) | HIGH: N개(N%)"
    //   "종합 위험등급: LOW/MED/HIGH" (HIGH가 하나라도 있으면 HIGH)
	public RiskType analyzePortfolio(FinancialAsset[] assets, int count) {
		RiskType result = null;
		int lowRiskCount = 0;
		int medRiskCount = 0;
		int highRiskCount = 0;
		int totalRiskCount = 0;
		for(FinancialAsset asset : assets) {
			if(asset == null) break;
			lowRiskCount = asset.getRiskLevel().equals(RiskType.LOW) ? ++lowRiskCount : lowRiskCount;
			medRiskCount = asset.getRiskLevel().equals(RiskType.MED) ? ++medRiskCount : medRiskCount;
			highRiskCount = asset.getRiskLevel().equals(RiskType.HIGH) ? ++highRiskCount : highRiskCount;
		}
		totalRiskCount = lowRiskCount + medRiskCount + highRiskCount;
		System.out.println("[포트폴리오 위험도 분석]");
		System.out.println("LOW: "+lowRiskCount+"개("+(double)lowRiskCount/totalRiskCount*100+"%) | MED: "+medRiskCount+"개("+(double)medRiskCount/totalRiskCount*100+"%) | HIGH: "+highRiskCount+"개("+(double)highRiskCount/totalRiskCount*100+"%)");
		if(highRiskCount > 0) {
			result = RiskType.HIGH;
		} else {
			result = lowRiskCount > medRiskCount ? RiskType.LOW : RiskType.MED;
		}
		System.out.println("종합 위험등급: "+result);
		return result;
	}
}


// =====================================================
// Lab5Main — 실행
// =====================================================
public class Lab5_InheritanceHierarchy {

    public static void main(String[] args) {

        System.out.println("========== [1] 자산 생성 (3단계 상속 확인) ==========");
        // TODO [★☆☆] 각 자산 클래스 인스턴스를 생성하세요 (5분)
        // d1: DemandDeposit  "AST001", "홍길동", 1_000_000, 6
        // t1: TimeDeposit    "AST002", "홍길동", 5_000_000, 4.5, 12
        // s1: StockFund      "AST003", "홍길동", 3_000_000, 8.0, "김펀드매니저"
        // b1: BondFund       "AST004", "홍길동", 2_000_000, 4.0, 3.0, "AAA"
        DemandDeposit d1 = new DemandDeposit("AST001", "홍길동", 1_000_000, 6);
        TimeDeposit t1 = new TimeDeposit("AST002", "홍길동", 5_000_000, 4.5, 12);
        StockFund s1 = new StockFund("AST003", "홍길동", 3_000_000, 8.0, "김펀드매니저");
        BondFund b1 = new BondFund("AST004", "홍길동", 2_000_000, 4.0, 3.0, "AAA");


        System.out.println("\n========== [2] 다형성 — FinancialAsset 타입으로 처리 ==========");
        // TODO [★★☆] 부모 타입 배열로 일괄 처리 (5분)
         FinancialAsset[] assets = { d1, t1, s1, b1 };
         for (FinancialAsset a : assets) {
             a.printAssetInfo();  // 각자 오버라이딩된 메서드 호출
         }


        System.out.println("\n========== [3] 정기예금 만기 처리 ==========");
        // TODO: t1.mature() 호출 후 calculateProfit() 재호출 → 이자 변화 확인
        t1.mature();
        t1.calculateProfit();

        System.out.println("\n========== [4] final 클래스 상속 불가 확인 ==========");
        // TimeDeposit는 final → 상속 불가 -> 컴파일 오류
//        class SpecialDeposit extends TimeDeposit { }

        System.out.println("\n========== [5] 포트폴리오 구성 ==========");
        // TODO [★★★] Portfolio에 자산 4개 추가 후 요약 출력 (5분)
         Portfolio portfolio = new Portfolio("홍길동", 10);
         portfolio.addAsset(d1);
         portfolio.addAsset(t1);
         portfolio.addAsset(s1);
         portfolio.addAsset(b1);
         portfolio.printPortfolioSummary();
         portfolio.printDetailReport();


        System.out.println("\n========== [6] 위험도 분석 ==========");
        // TODO [★★★] RiskAnalyzer로 각 자산 및 포트폴리오 분석 (5분)
         RiskAnalyzer analyzer = new RiskAnalyzer();
         FinancialAsset[] allAssets = { d1, t1, s1, b1 };
         for (FinancialAsset a : allAssets) {
             analyzer.analyze(a);
         }
         analyzer.analyzePortfolio(allAssets, allAssets.length);
    }
}
