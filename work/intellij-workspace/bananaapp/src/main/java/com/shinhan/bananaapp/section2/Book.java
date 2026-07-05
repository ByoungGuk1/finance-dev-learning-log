package com.shinhan.bananaapp.section2;

/**
 * Book - setter injection 예제용
 * XML: <bean id="book1" class="com.demo.domain.Book"> => 기본 생성
 *        <property name="title" value="Clean Code"/> => setter로 값 입력
 *        <property name="price" value="28000"/>
 *        <property name="kind"  value="기술서"/>
 *      </bean>
 */
public class Book {
    private String title;
    private int    price;
    private String kind;   // 예) 소설, 기술서, 자기계발

    public Book() {}

    public Book(String title, int price, String kind) {
        this.title = title;
        this.price = price;
        this.kind  = kind;
    }

    /* ── setter ── */
    public void setTitle(String title) { this.title = title; }
    public void setPrice(int price)    { this.price = price; }
    public void setKind(String kind)   { this.kind  = kind;  }

    /* ── getter ── */
    public String getTitle() { return title; }
    public int    getPrice() { return price; }
    public String getKind()  { return kind;  }

    @Override
    public String toString() {
        return String.format("Book{title='%s', price=%,d원, kind='%s'}", title, price, kind);
    }
}
