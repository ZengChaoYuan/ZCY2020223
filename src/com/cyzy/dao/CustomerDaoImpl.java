package com.cyzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.cyzy.bean.Customer;
import com.cyzy.bean.CustomerStatis;
import com.cyzy.service.CustomerService;
import com.cyzy.util.DBUtil;
import com.cyzy.util.JDBCUtil;

public class CustomerDaoImpl implements CustomerDao {
	private static QueryRunner runner=new QueryRunner(JDBCUtil.getDataSource());
	@Override
	public Customer login(String customerName, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Customer customer=null;
		String sql="SELECT CUSTOMER_ID,CUSTOMER_NAME,PASSWORD,SEX,TEL,AGE,ADDRESS,USE_STATUS,DELETE_STATUS,BALANCE,REGISTER_TIME FROM T_CUSTOMER "
				+ " WHERE CUSTOMER_NAME= ? AND PASSWORD= ? ";
		
		try {
			conn = DBUtil.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, customerName);
			ps.setString(2, password);
			rs=ps.executeQuery();
			while(rs.next()) {
				int customerId=rs.getInt("CUSTOMER_ID");
				String customername=rs.getString("CUSTOMER_NAME");
				String passWord=rs.getString("PASSWORD");
				int sex=rs.getInt("SEX");
				String tel=rs.getString("TEL");
				int age=rs.getInt("AGE");
				String address=rs.getString("ADDRESS");
				int useStatus=rs.getInt("USE_STATUS");
				int deleteStatus=rs.getInt("DELETE_STATUS");
				int balance=rs.getInt("BALANCE");
				String registerTime=rs.getString("REGISTER_TIME");
				customer=new Customer(customerId, customername, passWord, sex, tel, age, address, useStatus, deleteStatus, balance, registerTime);
				
				
			}
		} catch (SQLException e) {
	
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return customer;
	}

	@Override
	public int addCustomer(Customer customer) throws Exception {
		int result=0;
		String sql=" INSERT INTO T_CUSTOMER\r\n " + 
				" (CUSTOMER_ID,CUSTOMER_NAME,PASSWORD,SEX,TEL,AGE,ADDRESS,USE_STATUS,DELETE_STATUS,BALANCE,REGISTER_TIME)\r\n " + 
				" VALUES (SEQ_T_CUSTOMER.NEXTVAL,?,?,?,?,?,?,1,1,0,SYSDATE) ";
		Object[] params= {customer.getCustomerName(),customer.getPassword(),customer.getSex(),customer.getTel(),customer.getAge(),
				customer.getAddress()};
		result=runner.update(sql,params);
		return result;
	}

	@Override
	public int queryCount(Customer customer) {
		Statement st=null;
		ResultSet rs = null;
		Connection conn = null;
		String sql="SELECT COUNT(0) FROM T_CUSTOMER ";
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
	public List<Customer> queryCustomerLike(Customer customer, int startIndex, int endIndex) {
		Statement st=null;
		ResultSet rs = null;
		Connection conn = null;
		List<Customer> customerList=new ArrayList<Customer>();
		String sql="SELECT A.CUSTOMER_ID, A.CUSTOMER_NAME,A.PASSWORD, A.SEX,A.AGE,A.TEL, A.USE_STATUS,A.DELETE_STATUS FROM "
				+ " (SELECT T.CUSTOMER_ID, T.CUSTOMER_NAME, T.PASSWORD, T.SEX, T.TEL, T.AGE, T.USE_STATUS, T.DELETE_STATUS , ROWNUM RN FROM T_CUSTOMER T  "
				+ " WHERE ROWNUM<='"+endIndex+"'";
		
		try {
			conn = DBUtil.getConnection();
			st= conn.createStatement();
			if(customer!=null&&customer.getCustomerName()!=null&&!customer.getCustomerName().equals("")) {
				sql+=" AND T.CUSTOMER_NAME LIKE '%"+customer.getCustomerName()+"%' ";
			}
			if(customer!=null) {
				sql+=" AND T.USE_STATUS=" +customer.getUseStatus();
			}
			sql+=" ORDER BY T.CUSTOMER_NAME ) A WHERE RN >= "+startIndex;
		    rs = st.executeQuery(sql);	
		    while (rs.next()) {
		    	int customerId=rs.getInt("CUSTOMER_ID");
		    	String customerName=rs.getString("CUSTOMER_NAME");
		    	String password=rs.getString("PASSWORD");
		    	int sex=rs.getInt("SEX");
		    	int age=rs.getInt("AGE");
		    	String tel=rs.getString("TEL");
		    	int useStatus=rs.getInt("USE_STATUS");
		    	int deleteStatus=rs.getInt("DELETE_STATUS");
		    	Customer temp=new Customer();
		    	temp.setCustomerId(customerId);
		    	temp.setCustomerName(customerName);
		        temp.setPassword(password);
		        temp.setSex(sex);
		        temp.setAge(age);
		        temp.setTel(tel);
		    	temp.setUseStatus(useStatus);
		    	temp.setDeleteStatus(deleteStatus);
		    	customerList.add(temp);
		    }
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, st, rs);
		}
		return customerList;
	}

	@Override
	public List<Customer> queryCustomerList(Customer customer, int startIndex, int endIndex) {
		Statement st=null;
		ResultSet rs = null;
		Connection conn = null;
		List<Customer> customerList=new ArrayList<Customer>();
		String sql="SELECT A.CUSTOMER_ID, A.CUSTOMER_NAME,A.PASSWORD, A.SEX,A.AGE,A.TEL, A.USE_STATUS,A.DELETE_STATUS FROM "
				+ " (SELECT T.CUSTOMER_ID, T.CUSTOMER_NAME, T.PASSWORD, T.SEX, T.TEL, T.AGE, T.USE_STATUS, T.DELETE_STATUS , ROWNUM RN FROM T_CUSTOMER T  "
				+ " WHERE ROWNUM<='"+endIndex+"'";
		
		try {
			conn = DBUtil.getConnection();
			st= conn.createStatement();
			sql+=" ORDER BY T.CUSTOMER_NAME ) A WHERE RN >= "+startIndex;
		    rs = st.executeQuery(sql);	
		    while (rs.next()) {
		    	int customerId=rs.getInt("CUSTOMER_ID");
		    	String customerName=rs.getString("CUSTOMER_NAME");
		    	String password=rs.getString("PASSWORD");
		    	int sex=rs.getInt("SEX");
		    	int age=rs.getInt("AGE");
		    	String tel=rs.getString("TEL");
		    	int useStatus=rs.getInt("USE_STATUS");
		    	int deleteStatus=rs.getInt("DELETE_STATUS");
		    	Customer temp=new Customer();
		    	temp.setCustomerId(customerId);
		    	temp.setCustomerName(customerName);
		        temp.setPassword(password);
		        temp.setSex(sex);
		        temp.setAge(age);
		        temp.setTel(tel);
		    	temp.setUseStatus(useStatus);
		    	temp.setDeleteStatus(deleteStatus);
		    	customerList.add(temp);
		    }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, st, rs);
		}
		return customerList;
	}
	
	@Override
	public List<CustomerStatis> queryAllCustomerByMonths(CustomerStatis customerStatis) {
		Statement st=null;
		ResultSet rs = null;
		Connection conn = null;
		List<CustomerStatis> customerList=new ArrayList<CustomerStatis>();
		String sql=" select NUM,NAME,ord from (\r\n" + 
				" select count(0) NUM,'第一周' as NAME,1 as ord from t_customer t where t.register_time between \r\n" + 
				" trunc(sysdate,'mm') and trunc(sysdate,'mm')+7\r\n" + 
				" union\r\n" + 
				" select count(0) NUM,'第二周' as NAME,2 as ord from t_customer t where t.register_time between \r\n" + 
				" trunc(sysdate,'mm')+7 and trunc(sysdate,'mm')+14\r\n" + 
				" union\r\n" + 
				" select count(0) NUM,'第三周' as NAME,3 as ord from t_customer t where t.register_time between \r\n" + 
				" trunc(sysdate,'mm')+14 and trunc(sysdate,'mm')+21\r\n" + 
				" union\r\n" + 
				" select count(0) NUM,'第四周' as NAME,4 as ord from t_customer t where t.register_time between \r\n" + 
				" trunc(sysdate,'mm')+21 and trunc(sysdate,'mm')+28\r\n" + 
				" union\r\n" + 
				" select count(0) NUM,'第五周' as NAME,5 as ord from t_customer t where t.register_time between \r\n" + 
				" trunc(sysdate,'mm')+28 and trunc(last_day(sysdate),'dd')+1\r\n" + 
				" )order by ord";
		
		try {
			conn = DBUtil.getConnection();
			st= conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				String title=rs.getString("NAME");
				int count=rs.getInt("NUM");
				CustomerStatis temp=new CustomerStatis(title,count);
				customerList.add(temp);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, st, rs);
		}
		
		return customerList;
	}
	
	@Override
	public List<CustomerStatis> queryAllCustomerByWeek(CustomerStatis customerStatis) {
		Statement st=null;
		ResultSet rs = null;
		Connection conn = null;
		List<CustomerStatis> customerList=new ArrayList<CustomerStatis>();
		String sql=" SELECT X.TIME_DATE,DECODE(LOG_COUNT,NULL,0,LOG_COUNT) COUNT FROM \r\n " + 
				" (SELECT TO_CHAR((TRUNC(SYSDATE,'IW'))+ROWNUM-1,'YYYY-MM-DD') AS TIME_DATE FROM DUAL CONNECT BY ROWNUM <=7 ) X\r\n " + 
				" LEFT JOIN ( SELECT A.D,COUNT(*) LOG_COUNT FROM (SELECT TRUNC(REGISTER_TIME,'DD') D FROM T_CUSTOMER \r\n " + 
				" WHERE REGISTER_TIME >= TRUNC(SYSDATE,'D')+1 AND REGISTER_TIME < TRUNC(SYSDATE,'D')+8) A GROUP BY A.D ) Y\r\n " + 
				" ON X.TIME_DATE=TO_CHAR(Y.D,'YYYY-MM-DD') ORDER BY X.TIME_DATE ";
	
		try {
			conn = DBUtil.getConnection();
			st= conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				String title=rs.getString("TIME_DATE");
				int count=rs.getInt("COUNT");
				CustomerStatis temp=new CustomerStatis(title,count);
				customerList.add(temp);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, st, rs);
		}
		
		return customerList;
	}


	@Override
	public List<CustomerStatis> queryAllCustomerByYear(CustomerStatis customerStatis) {
		Statement st=null;
		ResultSet rs = null;
		Connection conn = null;
		List<CustomerStatis> customerList=new ArrayList<CustomerStatis>();
		String sql=" SELECT X.REGISTER_TIME,COUNT(CUSTOMER_NAME) AS COUNT FROM \r\n " + 
				" (SELECT TO_CHAR(ADD_MONTHS(SYSDATE,-LEVEL+1),'YYYY-MM') AS REGISTER_TIME FROM DUAL CONNECT BY LEVEL <=6 ORDER BY REGISTER_TIME) X\r\n " + 
				" LEFT JOIN T_CUSTOMER T ON X.REGISTER_TIME =TO_CHAR(T.REGISTER_TIME,'YYYY-MM')\r\n " + 
				" GROUP BY X.REGISTER_TIME ORDER BY X.REGISTER_TIME ";
		
		try {
			conn = DBUtil.getConnection();
			st= conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				String title=rs.getString("REGISTER_TIME");
				int count=rs.getInt("COUNT");
				CustomerStatis temp=new CustomerStatis(title,count);
				customerList.add(temp);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, st, rs);
		}
		
		return customerList;
	}

	@Override
	public Customer getCustomerById(int customerId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Customer customer=null;
		String sql="SELECT CUSTOMER_ID,CUSTOMER_NAME,PASSWORD,SEX,TEL,AGE,ADDRESS,USE_STATUS,DELETE_STATUS,BALANCE,REGISTER_TIME FROM T_CUSTOMER WHERE CUSTOMER_ID = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, customerId);
			rs = ps.executeQuery();
			while(rs.next()) {
				customer=new Customer();
				customer.setCustomerId(rs.getInt("CUSTOMER_ID"));
				customer.setCustomerName(rs.getString("CUSTOMER_NAME"));
				customer.setPassword(rs.getString("PASSWORD"));
				customer.setSex(rs.getInt("SEX"));
				customer.setTel(rs.getString("TEL"));
				customer.setAge(rs.getInt("AGE"));
				customer.setAddress(rs.getString("ADDRESS"));
				customer.setUseStatus(rs.getInt("USE_STATUS"));
				customer.setDeleteStatus(rs.getInt("DELETE_STATUS"));
				customer.setBalance(rs.getInt("BALANCE"));
				customer.setRegisterTime(rs.getString("REGISTER_TIME"));
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		
		return customer;
	}

	@Override
	public int updateUseStatus(Customer customer) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="UPDATE T_CUSTOMER SET USE_STATUS=? WHERE CUSTOMER_ID=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, customer.getUseStatus());
			ps.setInt(2, customer.getCustomerId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return 0;
	}

	@Override
	public int updateDeleteStatus(Customer customer)  {
		int count=0;
		String sql="UPDATE T_CUSTOMER SET DELETE_STATUS=? WHERE CUSTOMER_ID=?";
		Object [] params= {customer.getDeleteStatus(),customer.getCustomerId()};
			try {
				count=runner.update(sql,params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return count;
	}

	@Override
	public int resetPassword(Customer customer) {
		int count=0;
		String sql="UPDATE T_CUSTOMER SET PASSWORD=? WHERE CUSTOMER_ID=?";
		Object [] params= {customer.getPassword(),customer.getCustomerId()};
			try {
				count=runner.update(sql,params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return count;
	}

	@Override
	public int updateBalance(Customer customer) {
		int count=0;
		String sql="UPDATE T_CUSTOMER SET BALANCE=? WHERE CUSTOMER_ID=?";
		Object [] params= {customer.getBalance(),customer.getCustomerId()};
		try {
			count=runner.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	return count;
	}
   
	
}
