package com.cyzy.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cyzy.bean.Stand;
import com.cyzy.util.DBUtil;



public class StandDaoImpl implements StandDao {

	@Override
	public Stand getStandByStandId(int standId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Stand stand=null;
		String sql="SELECT STAND_ID,ASS_RESULT,REPORT_CONTENT FROM T_STAND WHERE STAND_ID=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, standId);
			rs = ps.executeQuery();
			while(rs.next()) {
				stand=new Stand();
				stand.setStandId(rs.getInt("STAND_ID"));
				stand.setAssResult(rs.getString("ASS_RESULT"));
				stand.setReportContent(rs.getString("REPORT_CONTENT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		
		return stand;
	}



}
