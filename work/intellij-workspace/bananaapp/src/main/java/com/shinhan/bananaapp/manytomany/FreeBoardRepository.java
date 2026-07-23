//package com.shinhan.bananaapp.manytomany;
//
//import org.springframework.data.jpa.repository.EntityGraph;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//
//public interface FreeBoardRepository extends JpaRepository<FreeBoardEntity, Integer> {
//  @EntityGraph(attributePaths = "replyList")
//  @Query("select b from FreeBoardEntity b")
//  List<FreeBoardEntity> findAllBoardAndReplies();
//}
