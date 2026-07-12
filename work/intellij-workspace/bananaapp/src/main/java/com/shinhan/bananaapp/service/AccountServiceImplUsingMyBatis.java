package com.shinhan.bananaapp.service;

import com.shinhan.bananaapp.dto.AccountDTO;
import com.shinhan.bananaapp.dto.AccountSearchDTO;
import com.shinhan.bananaapp.dto.AccountWithAttachmentDTO;
import com.shinhan.bananaapp.dto.AttachmentDTO;
import com.shinhan.bananaapp.mapper.AccountMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImplUsingMyBatis implements AccountService {
    // 1. @Mapper 사용
    private final AccountMapper accRepo;

    // 2. SQLSession 사용
//    private final AccountRepositorySQLSession accRepo;

    private final Long MAX_TRANSFER_AMOUNT = 100_000L;

    @Getter // ← Lombok이 getUploadDir() 생성
    @Value("${file.upload-dir:D:/sandbox/upload/shinhan/}")
    private String uploadDir;

    @Transactional(readOnly = true)
    public List<AccountDTO> selectAllAccounts() {
        return accRepo.findAll();
    }

    @Transactional(readOnly = true)
    public AccountDTO selectById(long id) {
        return accRepo.findById(id);
    }

    // ── 방식 1: Flat ResultMap 조회 ──
    @Transactional(readOnly = true)
    public List<AccountWithAttachmentDTO> findAllWithAttachmentFlat(Long id) {
        return accRepo.findAllWithAttachmentFlat(id);
    }

    // ── 방식 2: collection ResultMap 조회 ──
    // detail 화면 — 계좌 1건 + 첨부파일 리스트
    @Transactional(readOnly = true)
    public AccountDTO findByIdWithAttachment(Long id) {
        AccountDTO account = accRepo.findByIdWithAttachment(id);
        if (account == null)
            throw new IllegalArgumentException("계좌를 찾을 수 없습니다. id=" + id);
        return account;
    }

    @Transactional
    public void uploadAttachment(Long accountId, MultipartFile file) throws IOException {
        if (file.isEmpty())
            throw new IllegalArgumentException("파일이 없습니다.");
        // 저장 디렉토리 생성
        Path dirPath = Paths.get(uploadDir);
        if (!Files.exists(dirPath)) Files.createDirectories(dirPath);
        // 원본 파일명 / 확장자 추출
        String originalFilename = file.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf('.') + 1).toLowerCase();
        // UUID 저장명 생성
        String savedFilename = UUID.randomUUID() + "." + ext;
        // 파일 저장
        Path savePath = dirPath.resolve(savedFilename);
        Files.copy(file.getInputStream(), savePath, StandardCopyOption.REPLACE_EXISTING);
        log.info("[Upload] {} → {}", originalFilename, savedFilename);
        // DB 저장
        AttachmentDTO dto = AttachmentDTO.builder()
                .accountId(accountId)
                .originalFilename(originalFilename)
                .savedFilename(savedFilename)
                .fileSize(file.getSize())
                .fileType(ext)
                .build();
        accRepo.insertAttachment(dto);
    }

    @Transactional
    public void deleteAttachment(Long attachmentId) throws IOException {
        AttachmentDTO att = accRepo.findAttachmentById(attachmentId);
        if (att == null)
            throw new IllegalArgumentException("첨부파일을 찾을 수 없습니다.");
        // 실제 파일 삭제
        Path filePath = Paths.get(uploadDir, att.getSavedFilename());
        Files.deleteIfExists(filePath);
        // DB 삭제
        accRepo.deleteAttachment(attachmentId);
        log.info("[Delete] 첨부파일 삭제: {}", att.getOriginalFilename());
    }


    @Transactional(readOnly = true)
    public AttachmentDTO findAttachmentById(Long attachmentId) {
        AttachmentDTO att = accRepo.findAttachmentById(attachmentId);
        if (att == null)
            throw new IllegalArgumentException("첨부파일을 찾을 수 없습니다. id=" + attachmentId);
        return att;
    }

    public Boolean insertAccount(AccountDTO accountDTO) {
        return accRepo.insert(accountDTO) >= 1;
    }

    public Boolean updateAccount(AccountDTO accountDTO) {
        return accRepo.update(accountDTO) >= 1;
    }

    public Boolean deleteAccount(long id) {
        return accRepo.delete(id) >= 1;
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public Boolean transaction(Long fromId, Long toId, Long amount) {
        Map<String, Object> req = new HashMap<>();

        validateTransfer(fromId, amount);
        validateTransfer(toId, amount);

        req.put("fromId", fromId);
        req.put("toId", toId);
        req.put("amount", amount);

        log.info("계정 {}번이 계정 {}번에게 {}원 이체", fromId, toId, amount);

        int sendResult = accRepo.withdraw(req);
        if (amount > 500L) {
            throw new IllegalArgumentException("그냥 던진 오류");
        }
        int receiveResult = accRepo.deposit(req);

        if (sendResult != receiveResult) {
            throw new RuntimeException("결과 불일치");
        }

        return true;
    }

    @Transactional(readOnly = true)
    public List<AccountDTO> selectByCondition(AccountSearchDTO accountSearchDTO) {
        return accRepo.findByCondition(accountSearchDTO);
    }

    public AccountDTO validateTransfer(Long id, Long amount) {
        AccountDTO foundAccountDTO = accRepo.findById(id);

        if (foundAccountDTO == null) {
            throw new RuntimeException("Account not found");
        }

        if (foundAccountDTO.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("잔액 부족");
        }

        if (amount > MAX_TRANSFER_AMOUNT) {
            throw new RuntimeException("1회 이체 한도 초과");
        }
        return foundAccountDTO;
    }
}








