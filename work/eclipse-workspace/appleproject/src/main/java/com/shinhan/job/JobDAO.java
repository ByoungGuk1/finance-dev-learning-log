package com.shinhan.job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.util.DBUtil;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 7. 2. 오후 4:55:28 설명 : JabDAO
 */
public class JobDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	Statement st = null;
	ResultSet rs = null;

//	부서, 직원 조인 조회

	public List<JobDTO> selectAll() {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<JobDTO> jobList = new ArrayList<>();

		conn = DBUtil.dbConnect();
		String query = "select * from jobs";

		try {
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				JobDTO dto = JobDTO.builder().jobId(rs.getString("job_id")).jobTitle(rs.getString("job_title"))
						.minSalary(rs.getInt("min_salary")).maxSalary(rs.getInt("max_salary")).build();
				jobList.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, pst, rs);
		}
		return jobList;
	}
}
