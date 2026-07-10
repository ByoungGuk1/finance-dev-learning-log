package com.shinhan.bananaapp.repository;

import com.shinhan.bananaapp.dto.AccountDTO;
import com.shinhan.bananaapp.dto.AccountSearchDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AccountRepositorySQLSession {
    private static final String NAMESPACE = "com.shinhan.bananaapp.mapper.AccountMapper.";
    private final SqlSession sqlSession;

    public List<AccountDTO> findAll() {
        return sqlSession.selectList(NAMESPACE + "findAll");
    }

    public AccountDTO findById(Long id) {
        return sqlSession.selectOne(NAMESPACE + "findById", id);
    }

    public List<AccountDTO> findByCondition(AccountSearchDTO search) {
        return sqlSession.selectList(NAMESPACE + "findByCondition", search);
    }

    public int count() {
        return sqlSession.selectOne(NAMESPACE + "count");
    }

    public int insert(AccountDTO account) {
        return sqlSession.insert(NAMESPACE + "insert", account);
    }

    public int update(AccountDTO account) {
        return sqlSession.update(NAMESPACE + "update", account);
    }

    public int delete(Long id) {
        return sqlSession.delete(NAMESPACE + "delete", id);
    }

    public int deposit(Map<String, Object> map) {
        return sqlSession.insert(NAMESPACE + "deposit", map);
    }

    public int withdraw(Map<String, Object> map) {
        return sqlSession.insert(NAMESPACE + "withdraw", map);
    }
}
