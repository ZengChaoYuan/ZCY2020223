package com.cyzy.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cyzy.bean.Param;
import com.cyzy.util.DBUtil;

public class ParamDaoImpl implements ParamDao {

	@Override
	public Param getParamById(int paramId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Param param=null;

	    String sql="SELECT PARAM_ID,RESET_PASSWORD FROM T_PARAM WHERE PARAM_ID=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paramId);
			rs = ps.executeQuery();
			while(rs.next()) {
				param=new Param();
				param.setParamId(rs.getInt("PARAM_ID"));
				param.setResetPassword(rs.getString("RESET_PASSWORD"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}   
		return param;
	}

	

}
