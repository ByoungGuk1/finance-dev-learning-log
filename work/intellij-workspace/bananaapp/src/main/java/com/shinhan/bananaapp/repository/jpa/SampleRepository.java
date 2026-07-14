package com.shinhan.bananaapp.repository.jpa;

import com.shinhan.bananaapp.entity1.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

//JPA 사용 : 개발자가 Reposityory 설계 , 구현은 Spring이 런타임시 실행
public interface SampleRepository
        extends CrudRepository<SampleEntity, Integer>,
        PagingAndSortingRepository<SampleEntity, Integer>,
        JpaRepository<SampleEntity, Integer> {
}
