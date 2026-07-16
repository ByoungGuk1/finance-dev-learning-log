package com.shinhan.bananaapp.onetomany;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest
class OneToManyTest {
  @Autowired
  private PDSFileRepository fileRepository;
  @Autowired
  private PDSBoardRepository boardRepository;

  // 1(Board) -> N(File)
  @Test
  void insertBoardAndFiles() {
    // 영속성 전이
    // insert PDSBoard -> insert PDSFile -> update PDSFile
    List<PDSFileEntity> fileList = List.of(
        PDSFileEntity.builder().pdsfilename("file1.jpg").build(),
        PDSFileEntity.builder().pdsfilename("file2.jpg").build(),
        PDSFileEntity.builder().pdsfilename("file3.jpg").build(),
        PDSFileEntity.builder().pdsfilename("img1.jpg").build()
    );
    PDSBoardEntity board = PDSBoardEntity.builder()
        .pname("목요일")
        .pwriter("작성자 3번")
//        .files2(fileList)
        .build();

    boardRepository.save(board);
  }

  @Test
  @Transactional
  @Commit
  void updateBoardAndFiles() {
    // board 수정, board가 참조하는 file의 정보도 수정
    Long bno = 1L;
    PDSBoardEntity foundBoard = boardRepository.findById(bno).orElse(null);
    if (foundBoard == null) {
      return;
    }
    foundBoard.setPname("수정");
    foundBoard.setPwriter("작성자ㅏ");
    List<PDSFileEntity> fileList = foundBoard.getFiles2();
    log.info("삭제 할 file = {}", fileList.get(0));
    fileList.remove(0);
    fileList.add(PDSFileEntity.builder().pdsfilename("new-file.png").build());
    fileList.add(PDSFileEntity.builder().pdsfilename("new-file2.png").build());

    boardRepository.save(foundBoard);
  }

  @Test
  void deleteFile() {
    fileRepository.deleteById(1L);
  }

  // N+1 문제 해결 방법 3가지
  // 1. BatchSize
  // 2. Fetch Join
  // 3. EntityGraph

  @Test
  @Transactional
  void selectAll() {
//    List<PDSBoardEntity> foundBoardList = boardRepository.findAll();
//    List<PDSBoardEntity> foundBoardList = boardRepository.findAll2();
    List<PDSBoardEntity> foundBoardList = boardRepository.findAll3();
    foundBoardList.stream().map(d -> "file의 수 : " + d.getFiles2().size() + " / " + d).forEach(log::info);
  }

  @Test
  @Commit
  void updateFile() {
    PDSFileEntity foundFile = fileRepository.findById(10L).orElse(null);
    if (foundFile == null) {
      return;
    }
    foundFile.setPdsfilename("aa");
    fileRepository.save(foundFile);

    fileRepository.updateFileNameByFno("bb", 10L);
  }
}