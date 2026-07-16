package com.shinhan.bananaapp.multikey;

import jakarta.persistence.Embeddable;
import lombok.*;

// 2개의 column의 조합으로 pk키를 사용
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Embeddable
public class MultiKeyB {
  Integer id1;
  Integer id2;
}
