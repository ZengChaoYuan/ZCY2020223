package com.cyzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.cyzy.bean.MyAccount;
import com.cyzy.util.DBUtil;
import com.cyzy.util.JDBCUtil;

public class MyAccountDaoImpl implements MyAccountDao {
	private static QueryRunner runner=new QueryRunner(JDBCUtil.getDataSource());
	
	@Override
	public List<Map<String, Object>> queryCustomerAccount(int customerId) {
		String sql="SELECT A.MYACCOUNT_ID,A.HAPPEN_TIME,A.HAPPEN_THING,A.CUSTOMER_ID,A.CONSUMP_TYPE,A.CONSUMP_MONEY,A.USER_ID,B.USER_NAME FROM T_MYACCOUNT A \r\n" + 
				" LEFT JOIN T_USER B ON A.USER_ID=B.USER_ID WHERE CUSTOMER_ID=? ";
		List<Map<String, Object>> customerAccount=null;
		Object[] params= {customerId};
		try {
			customerAccount=runner.query(sql, params,new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerAccount;
	}

	
	//可以使用
	@Override
	public List<MyAccount> queryMyAccount(int customerId) {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<MyAccount> accountList=new ArrayList<MyAccount>();
		String sql="SELECT MYACCOUNT_ID, HAPPEN_TIME,HAPPEN_THING,CUSTOMER_ID,USER_ID,CONSUMP_TYPE,CONSUMP_MONEY FROM T_MYACCOUNT WHERE CUSTOMER_ID= ?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, customerId);
			rs=ps.executeQuery();
			while(rs.next()) {
				int myaccountId=rs.getInt("MYACCOUNT_ID");
				String happenTime=rs.getString("HAPPEN_TIME").substring(0,10);
				String happenThing=rs.getString("HAPPEN_THING");
				int customerid=rs.getInt("CUSTOMER_ID");
				String consumpType=rs.getString("CONSUMP_TYPE");
				int consumpMoney=rs.getInt("CONSUMP_MONEY");
				int userId=rs.getInt("USER_ID");
				MyAccount temp=new MyAccount(myaccountId,happenTime,happenThing,customerid,consumpType,consumpMoney,userId);
				accountList.add(temp);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return accountList;
	}
	
	@Override
	public List<MyAccount> queryfundAccount(int userId) {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<MyAccount> accountList=new ArrayList<MyAccount>();
		String sql="SELECT MYACCOUNT_ID, HAPPEN_TIME,HAPPEN_THING,CUSTOMER_ID,USER_ID,CONSUMP_TYPE,CONSUMP_MONEY FROM T_MYACCOUNT WHERE USER_ID=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs=ps.executeQuery();
			while(rs.next()) {
				int myaccountId=rs.getInt("MYACCOUNT_ID");
				String happenTime=rs.getString("HAPPEN_TIME").substring(0,10);
				String happenThing=rs.getString("HAPPEN_THING");
				int customerid=rs.getInt("CUSTOMER_ID");
				String consumpType=rs.getString("CONSUMP_TYPE");
				int consumpMoney=rs.getInt("CONSUMP_MONEY");
				int userid=rs.getInt("USER_ID");
				MyAccount temp=new MyAccount(myaccountId,happenTime,happenThing,customerid,consumpType,consumpMoney,userid);
				accountList.add(temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return accountList;
	}

	@Override
	public int addCustomerAccount(MyAccount myAccount) throws Exception {
		int result=0;
		String sql="INSERT INTO T_MYACCOUNT(MYACCOUNT_ID,HAPPEN_TIME,HAPPEN_THING,CUSTOMER_ID,CONSUMP_TYPE,CONSUMP_MONEY,USER_ID)"
				+ " VALUES(SEQ_T_MYACCOUNT.NEXTVAL, SYSDATE,?,?,?,?,?)";
		Object[] params= {myAccount.getHappenThing(),myAccount.getCustomerId(),myAccount.getConsumpType(),
				myAccount.getConsumpMoney(),myAccount.getUserId()};
		result=runner.update(sql,params);
		return result;
	}


	@Override
	public int queryCustomerCount(MyAccount myAccount) {
		Statement st=null;
		ResultSet rs = null;
		Connection conn = null;
		String sql="SELECT COUNT(0) FROM T_MYACCOUNT ";
         int count=0;
		
		try {
			conn=DBUtil.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, st, rs);
		}
		
		return count;
	}


	@Override
	public List<Map<String, Object>> queryCustomerAccounts(MyAccount myAccount, int startIndex, int endIndex) {
		String sql="SELECT * FROM  (\r\n" + 
				" SELECT  A.MYACCOUNT_ID,A.HAPPEN_TIME,A.HAPPEN_THING,A.CUSTOMER_ID,A.CONSUMP_TYPE,A.CONSUMP_MONEY,A.USER_ID,B.USER_NAME,ROWNUM AS RN  FROM T_MYACCOUNT A\r\n " + 
				" LEFT JOIN T_USER B ON A.USER_ID=B.USER_ID WHERE CUSTOMER_ID=? AND  ROWNUM <='"+endIndex+"')  C WHERE C.RN >= "+startIndex;
		List<Map<String, Object>> customerAccounts=null;
		Object [] params= {myAccount.getCustomerId()};
		try {
			customerAccounts=runner.query(sql,params, new MapListHandler());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return customerAccounts;
	}

	
	

}
