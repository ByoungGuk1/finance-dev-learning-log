package com.shinhan.department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.util.DBUtil;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 24. 오후 12:05:51 설명 : DepartmentDAO
 */
public class DepartmentDAO {
	public List<DepartmentDTO> selectAll() {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<DepartmentDTO> deptList = new ArrayList<>();

		conn = DBUtil.dbConnect();
		String query = "select * from departments";

		try {
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				DepartmentDTO dto = DepartmentDTO.builder().departmentId(rs.getInt("department_id"))
						.departmentName(rs.getString("department_name")).managerId(rs.getInt("manager_id"))
						.locationId(rs.getInt("location_id")).build();
				deptList.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, pst, rs);
		}
		return deptList;
	}
}
