package com.shinhan.bananaapp.controller.jpa;

import com.shinhan.bananaapp.entity1.BoardEntity;
import com.shinhan.bananaapp.service.jpa.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
  private final BoardService boardService;

  @GetMapping
  public ResponseEntity<List<BoardEntity>> getBoardList() {
    return ResponseEntity.ok().body(boardService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<BoardEntity> getBoard(@PathVariable int id) {
    return ResponseEntity.ok().body(boardService.findById(id));
  }

  @PostMapping
  public ResponseEntity<BoardEntity> insertBoard(@RequestBody BoardEntity boardEntity) {
    return ResponseEntity.ok().body(boardService.save(boardEntity));
  }

  @PutMapping
  public ResponseEntity<BoardEntity> updateBoard(@RequestBody BoardEntity boardEntity) {
    return ResponseEntity.ok().body(boardService.save(boardEntity));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BoardEntity> deleteBoard(@PathVariable int id) {
    return ResponseEntity.ok().body(boardService.delete(id));
  }

  @GetMapping("/writer")
  public ResponseEntity<List<BoardEntity>> getBoardByWriter(@RequestBody String writer) {
    return ResponseEntity.ok().body(boardService.findByWriter(writer));
  }

  @GetMapping("/between")
  public ResponseEntity<List<BoardEntity>> getBoardBetween(@RequestParam Integer bno1, @RequestParam Integer bno2) {
    return ResponseEntity.ok().body(boardService.findByBnoBetween(bno1, bno2));
  }

  @GetMapping("/condition")
  public ResponseEntity<?> getBoardCondition(@RequestParam String writer, @RequestParam Integer bno1, @RequestParam Integer bno2, @RequestParam String title) {
    return ResponseEntity.ok().body(boardService.findByWriterContainingAndBnoBetweenAndTitleContainingOrderByBnoDesc(writer, bno1, bno2, title));
  }

  @GetMapping("/after-date")
  public ResponseEntity<List<BoardEntity>> getBoardAfterDate(@RequestParam String stringDate) {
    stringDate += stringDate.split(" ").length <= 1 ? " 00:00:00" : "";
    System.out.println(stringDate);
    Timestamp date = !stringDate.isEmpty() ? Timestamp.valueOf(stringDate) : null;
    return ResponseEntity.ok().body(boardService.findByRegDateAfter(date));
  }

  @GetMapping("/custom")
  public ResponseEntity<List<BoardEntity>> getBoardCustom(@RequestParam Integer bno) {
    return ResponseEntity.ok().body(boardService.customQuery(bno));
  }
}
