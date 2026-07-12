package com.shinhan.bananaapp.mapper;

import com.shinhan.bananaapp.dto.AccountDTO;
import com.shinhan.bananaapp.dto.AccountSearchDTO;
import com.shinhan.bananaapp.dto.AccountWithAttachmentDTO;
import com.shinhan.bananaapp.dto.AttachmentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

// MyBatis 프록시가 자동으로 구현체를 생성
@Mapper
public interface AccountMapper {
    List<AccountDTO> findAll();

    AccountDTO findById(Long id);

    // flat
    List<AccountWithAttachmentDTO> findAllWithAttachmentFlat(Long accountId);

    // collection
    AccountDTO findByIdWithAttachment(Long accountId);

    List<AccountDTO> findByCondition(AccountSearchDTO search);  // 동적 SQL

    int insert(AccountDTO account);

    int update(AccountDTO account);

    int delete(Long id);

    int deposit(Map<String, Object> map);

    int withdraw(Map<String, Object> map);

    void insertAttachment(AttachmentDTO dto);

    void deleteAttachment(Long id);

    AttachmentDTO findAttachmentById(Long id);
}