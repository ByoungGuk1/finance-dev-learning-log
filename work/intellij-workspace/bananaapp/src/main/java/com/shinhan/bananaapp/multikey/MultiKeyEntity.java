package com.shinhan.bananaapp.multikey;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@IdClass(value = MultiKeyA.class)
@Entity
@Table(name = "TBL_CHILD_A")
public class MultiKeyEntity {
  @Id
  Integer id1;
  @Id
  Integer id2;

  String name;
  String phoneNumber;
}
