package com.shinhan.bananaapp.entity1;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

//entity 설계 : JPA가 관리하는 대상 = 엔티티
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "userId")
@Builder
@Table(name = "tbl_sample")
public class SampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    @Column(nullable = false)
    private String name;
    @Column(unique = true)
    private String email;
    @Column(length = 50)
    private String title;
    @Column(updatable = false)
    private LocalDate createDate;
    @Column(precision = 10, scale = 2)
    private BigDecimal amount;
    @CreationTimestamp
    private Timestamp regDate;
    @UpdateTimestamp
    private Timestamp updateDate;
}
