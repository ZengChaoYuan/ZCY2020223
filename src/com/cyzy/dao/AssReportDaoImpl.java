package com.cyzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.cyzy.bean.AssReport;
import com.cyzy.util.DBUtil;
import com.cyzy.util.JDBCUtil;


public class AssReportDaoImpl implements AssReportDao {
	private static QueryRunner runner=new QueryRunner(JDBCUtil.getDataSource());
	
	@Override
	public List<AssReport> querymyReport(int customerId) {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<AssReport> assRepostList=new ArrayList<AssReport>();
		String sql="SELECT ASSREPORT_ID,ASS_TIME,STAND_ID,ASS_SCORE,CUSTOMER_ID FROM T_ASSREPORT WHERE CUSTOMER_ID=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, customerId);
			rs=ps.executeQuery();
			while(rs.next()) {
				int assreportId=rs.getInt("ASSREPORT_ID");
				String assTime=rs.getString("ASS_TIME").substring(0,10);
				int standId=rs.getInt("STAND_ID");
				int assScore=rs.getInt("ASS_SCORE");
				int customerid=rs.getInt("CUSTOMER_ID");
				AssReport temp=new AssReport(assreportId,
						assTime,standId,assScore,customerid);
				assRepostList.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		
		return assRepostList;
	}

	@Override
	public List<Map<String, Object>> queryCustomerReport(AssReport assReport) {
		String sql=" SELECT A.ASSREPORT_ID,A.ASS_TIME,B.CUSTOMER_NAME,D.AREA_NAME,A.ASS_SCORE FROM T_ASSREPORT A \r\n " + 
				" LEFT JOIN T_CUSTOMER B ON A.CUSTOMER_ID=B.CUSTOMER_ID\r\n " + 
				" LEFT JOIN T_STAND C ON A.STAND_ID=C.STAND_ID\r\n " + 
				" LEFT JOIN T_AREA D ON C.AREA_ID=D.AREA_ID";
	    List<Map<String, Object>> assReports=null;
	   try {
		   assReports=runner.query(sql, new MapListHandler());
	} catch (Exception e) {
		e.printStackTrace();
	}
		return assReports;
	}

	@Override
	public Map<String, Object> queryCustomerAss(int assReportId) {
		String sql=" SELECT B.ASS_RESULT,B.REPORT_CONTENT FROM T_ASSREPORT A \r\n" + 
				" LEFT JOIN T_STAND B ON A.STAND_ID=B.STAND_ID WHERE A.ASSREPORT_ID=?";
		Map<String,Object> assReport=null;
		Object[] params= {assReportId};
		try {
			assReport=runner.query(sql,params, new MapHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return assReport;
	}

}
