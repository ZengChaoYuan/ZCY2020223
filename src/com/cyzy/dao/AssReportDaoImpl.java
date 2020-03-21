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
	
	@Override
	public int MyReportCounts(AssReport assReport) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql="SELECT COUNT(0) FROM T_ASSREPORT WHERE CUSTOMER_ID=?";
		int count=0;
		try {
			conn = DBUtil.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, assReport.getCustomerId());
			rs=ps.executeQuery();
			while(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return count;
	}

	@Override
	public List<Map<String, Object>> queryMyReports(AssReport assReport, int startIndex, int endIndex) {
		String sql="SELECT * FROM \r\n" + 
				" (SELECT ASSREPORT_ID,ASS_TIME,STAND_ID,ASS_SCORE,CUSTOMER_ID,ROWNUM AS RN FROM T_ASSREPORT WHERE CUSTOMER_ID=? AND ROWNUM<='"+endIndex+"' ORDER BY ASS_TIME DESC) B WHERE B.RN>="+startIndex;
		List<Map<String, Object>> myReports=null;
		Object [] params= {assReport.getCustomerId()};
		try {
			myReports=runner.query(sql,params, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myReports;
	}

	@Override
	public int AllReportCounts() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql="SELECT COUNT(0) FROM T_ASSREPORT ";
		int count=0;
		try {
			conn = DBUtil.getConnection();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return count;
	}

	@Override
	public List<Map<String, Object>> queryAllReports(int startIndex, int endIndex) {
	    String sql="SELECT * FROM (SELECT A.ASSREPORT_ID,A.ASS_TIME,B.CUSTOMER_NAME,D.AREA_NAME,A.ASS_SCORE,ROWNUM AS RN FROM T_ASSREPORT A \r\n" + 
	    		"         LEFT JOIN T_CUSTOMER B ON A.CUSTOMER_ID=B.CUSTOMER_ID\r\n" + 
	    		"        LEFT JOIN T_STAND C ON A.STAND_ID=C.STAND_ID\r\n" + 
	    		"         LEFT JOIN T_AREA D ON C.AREA_ID=D.AREA_ID AND ROWNUM<='"+endIndex+"' ORDER BY A.ASS_TIME DESC ) E WHERE E.RN>="+startIndex;
	    List<Map<String, Object>> allReports=null;
		try {
			allReports=runner.query(sql, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allReports;
	}

	@Override
	public int addAssReport(AssReport assReport) throws Exception {
		int result=0;
		String sql="INSERT INTO T_ASSREPORT(ASSREPORT_ID,ASS_TIME,STAND_ID,ASS_SCORE,CUSTOMER_ID) VALUES (SEQ_T_ASSREPORT.NEXTVAL,SYSDATE,?,?,?)";
		Object[] params= {assReport.getStandId(),assReport.getAssScore(),assReport.getCustomerId()};
		result=runner.update(sql,params);
		return result;
	}

}
