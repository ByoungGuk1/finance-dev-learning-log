package com.shinhan.bananaapp.multikey;

import lombok.*;

// 2개의 column의 조합으로 pk키를 사용
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MultiKeyA {
  Integer id1;
  Integer id2;
}
