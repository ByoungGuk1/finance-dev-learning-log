package com.shinhan.bananaapp.multikey;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MultiKeyRepository extends JpaRepository<MultiKeyEntity, MultiKeyA> {
}
