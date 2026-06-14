package com.shinhan.day03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.util.DBUtil;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 11. 오후 12:23:19 설명 : BoardDAO
 */
// Repository
// 영속성 영역을 담당
// DAO (Data Access Object)
public class BoardDAO {
//	db 연결에 사용
	Connection conn;
//	sql 전송을 위해 사용
	Statement st;
	PreparedStatement pst;
//	sql 결과 수집을 위해 사용
	ResultSet rs;

//	public static void main(String[] args) {
//		new BoardDAO().insertBoard(new BoardDTO(0, "목요일", "밥", "송병국", null, null));
//		new BoardDAO().updateBoard(new BoardDTO(2, "a요일", "2", "송병국", null, null));
//		new BoardDAO().deleteBoard(1);
//		new BoardDAO().selectAll().stream().forEach(System.out::println);
//	}

	public int deleteBoard(int bno) {
		int resultCount = 0;
		String sql = "delete from board where bno = ?";
		conn = DBUtil.dbConnect();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, bno);
			resultCount = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, pst, rs);
		}

		return resultCount;
	}

	public int updateBoard(BoardDTO boardDTO) {
		int resultCount = 0;
		String sql = "update board set title=?, contants = ?, writer=?, updatedate = sysdate where bno = ?";
		conn = DBUtil.dbConnect();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, boardDTO.getTitle());
			pst.setString(2, boardDTO.getContants());
			pst.setString(3, boardDTO.getWriter());
			pst.setInt(4, boardDTO.getBno());
			resultCount = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, pst, rs);
		}

		return resultCount;
	}

	public int insertBoard(BoardDTO boardDTO) {
		int resultCount = 0;
		String sql = "insert into board values(seq_board_bno.nextval,?,?,?,sysdate,null)";
		conn = DBUtil.dbConnect();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, boardDTO.getTitle());
			pst.setString(2, boardDTO.getContants());
			pst.setString(3, boardDTO.getWriter());
			resultCount = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, pst, rs);
		}

		return resultCount;
	}

	public BoardDTO selectById(int id) {
		BoardDTO result = null;
		String sql = "select * from board where bno = ?";
		conn = DBUtil.dbConnect();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (!rs.next()) {
				System.err.println("단일 조회 실패");
			}
			result = makeBoard(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, pst, rs);
		}

		return result;
	}

	public List<BoardDTO> selectAll() {
		List<BoardDTO> result = new ArrayList<>();
		String sql = "select * from board";
		conn = DBUtil.dbConnect();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				BoardDTO board = makeBoard(rs);
				result.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, st, rs);
		}

		return result;
	}

	private BoardDTO makeBoard(ResultSet rs) throws SQLException {
		BoardDTO board = new BoardDTO();
		board.setBno(rs.getInt(1));
		board.setTitle(rs.getString("Title"));
		board.setContants(rs.getString("Contants"));
		board.setWriter(rs.getString("Writer"));
		board.setRegdate(rs.getDate("Regdate"));
		board.setUpdatedate(rs.getDate("Updatedate"));
		return board;
	}
}
