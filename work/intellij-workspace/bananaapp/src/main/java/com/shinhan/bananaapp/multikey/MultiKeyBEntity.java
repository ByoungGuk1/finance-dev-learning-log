package com.shinhan.bananaapp.multikey;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "TBL_CHILD_B")
public class MultiKeyBEntity {
  @EmbeddedId
  private MultiKeyB id;

  private String name;
  private String phoneNumber;
}
