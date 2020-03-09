package com.cyzy.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cyzy.bean.Log;
import com.cyzy.bean.LogInf;
import com.cyzy.util.DBUtil;


public class LogDaoImpl implements LogDao {

	@Override
	public List<Log> queryAllLogByYear(Log log) {
		Statement st=null;
		ResultSet rs = null;
		Connection conn = null;
		List<Log> logList = new ArrayList<Log>();
		String sql ="SELECT X.LOG_CTIME,COUNT(ADMIN_NAME) AS COUNT FROM \r\n" + 
				" (SELECT TO_CHAR(ADD_MONTHS(SYSDATE,-LEVEL+1),'YYYY-MM') AS LOG_CTIME FROM DUAL CONNECT BY LEVEL <=6) X\r\n" + 
				" LEFT JOIN TEXT_LOG_INF T\r\n" + 
				" ON X.LOG_CTIME =TO_CHAR(T.LOG_CTIME,'YYYY-MM')\r\n" + 
				" GROUP BY X.LOG_CTIME ORDER BY X.LOG_CTIME";
		
		try {
			conn = DBUtil.getConnection();
			st= conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				String title=rs.getString("LOG_CTIME");
				int count=rs.getInt("COUNT");
				Log temp=new Log(title,count);
				logList.add(temp);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, st, rs);
		}
		return logList;
	}

	@Override
	public List<Log> queryAllLogByTime(Log log) {
		Statement st=null;
		ResultSet rs = null;
		Connection conn = null;
		List<Log> logList = new ArrayList<Log>();
		String sql =" SELECT X.TIME_DATE,DECODE(LOG_COUNT,NULL,0,LOG_COUNT) COUNT FROM \r\n" + 
				" (SELECT TO_CHAR((TRUNC(SYSDATE,'IW'))+ROWNUM-1,'YYYY-MM-DD') AS TIME_DATE FROM DUAL CONNECT BY ROWNUM <=7 ) X\r\n" + 
				" LEFT JOIN ( SELECT A.D,COUNT(*) LOG_COUNT FROM (SELECT TRUNC(LOG_CTIME,'DD') D FROM TEXT_LOG_INF \r\n" + 
				" WHERE LOG_CTIME >= TRUNC(SYSDATE,'D')+1 AND LOG_CTIME < TRUNC(SYSDATE,'D')+8) A GROUP BY A.D ) Y\r\n" + 
				" ON X.TIME_DATE=TO_CHAR(Y.D,'YYYY-MM-DD') ";
		
		try {
			conn = DBUtil.getConnection();
			st= conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				String title=rs.getString("TIME_DATE");
				int count=rs.getInt("COUNT");
				Log temp=new Log(title,count);
				logList.add(temp);				
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, st, rs);
		}
		return logList;
	}

	@Override
	public List<LogInf> queryAllLogByUser(String startTime, String endTime) {
		Statement st=null;
		ResultSet rs = null;
		Connection conn = null;
		List<LogInf> logInfList = new ArrayList<LogInf>();
		String sql="SELECT ADMIN_NAME , COUNT(*) AS COUNT FROM TEXT_LOG_INF WHERE TO_CHAR(LOG_CTIME,'YYYY-MM-DD') >='"+startTime+"' \r\n" + 
				" AND TO_CHAR(LOG_CTIME,'YYYY-MM-DD')<='"+endTime+"'  GROUP BY ADMIN_NAME";
		
		try {
			conn = DBUtil.getConnection();
			st= conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				String adminName=rs.getString("ADMIN_NAME");
				int count=rs.getInt("COUNT");
				LogInf temp=new LogInf(adminName,count);
				logInfList.add(temp);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, st, rs);
		}
		return logInfList;
	}

}
