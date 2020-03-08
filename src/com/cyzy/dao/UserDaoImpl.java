package com.cyzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.cyzy.bean.User;
import com.cyzy.util.DBUtil;
import com.cyzy.util.JDBCUtil;

public class UserDaoImpl implements UserDao {
	private static QueryRunner runner=new QueryRunner(JDBCUtil.getDataSource());
	
	@Override
	public User login(String userName, String password) {//登录
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	    User user=null;
		String sql="SELECT USER_ID,USER_NAME,PASSWORD,USE_STATUS,DELETE_STATUS,PROFESSOR,ROLE_ID,BALANCE,SCHOOL,INTRO,PRE_EXPENSE,REAL_NAME FROM T_USER "
				+ " WHERE USER_NAME= ? AND PASSWORD= ? ";
		
		try {
			conn = DBUtil.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			rs=ps.executeQuery();
			while(rs.next()) {
				int userId=rs.getInt("USER_ID");
			    String username=rs.getString("USER_NAME");
			    String passWord=rs.getString("PASSWORD");
			    int useStatus=rs.getInt("USE_STATUS");
			    int deleteStatus=rs.getInt("DELETE_STATUS");
			    String professor=rs.getString("PROFESSOR");
			    int roleId=rs.getInt("ROLE_ID");
			    int balance=rs.getInt("BALANCE");
			    String school=rs.getString("SCHOOL");
			    String intro=rs.getString("INTRO");
			    int preExpense=rs.getInt("PRE_EXPENSE");
			    String realName=rs.getString("REAL_NAME");
			    
			    user=new User(userId,username,passWord,useStatus, deleteStatus, professor,roleId,balance, school,intro, preExpense,realName);
			   
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return user;
	}
	
	//检测用户名是否重复
	@Override
	public User checkUserName(String userName) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user=null;
		String sql="SELECT USER_NAME FROM T_USER WHERE USER_NAME = ? ";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, userName);
			rs=ps.executeQuery();
			while(rs.next()) {
				user=new User();
				user.setUserName(rs.getString("USER_NAME"));				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		
		return user;
	}
	
	@Override
	public int queryCount(User user) {
		Statement st=null;
		ResultSet rs = null;
		Connection conn = null;
		String sql="SELECT COUNT(0) FROM T_USER ";
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
	public List<User> queryUserList(User user, int startIndex, int endIndex) {
		Statement st=null;
		ResultSet rs = null;
		Connection conn = null;
		List<User> userList=new ArrayList<User>();
		String sql=
				" SELECT * FROM (\r\n " + 
				" SELECT T.USER_ID,T.USER_NAME,T.REAL_NAME,T.PASSWORD,T.PROFESSOR,T.SCHOOL,T.USE_STATUS,T.DELETE_STATUS,ROWNUM RN FROM T_USER T WHERE ROWNUM <= '"+endIndex+"' "; 
		
		try {
			conn = DBUtil.getConnection();
			st= conn.createStatement();
			sql+=" ORDER BY T.REAL_NAME )  WHERE RN >= "+startIndex;	
			 rs = st.executeQuery(sql);	
			 while(rs.next()) {
				 int userId=rs.getInt("USER_ID");
				 String userName=rs.getString("USER_NAME");
				 String realName=rs.getString("REAL_NAME");
				 String password=rs.getString("PASSWORD");
				 String professor=rs.getString("PROFESSOR");
				 String school=rs.getString("SCHOOL");
				 int useStatus=rs.getInt("USE_STATUS");
				 int deleteStatus=rs.getInt("DELETE_STATUS");
				 User temp=new User();
				 temp.setUserId(userId);
				 temp.setUserName(userName);
				 temp.setRealName(realName);
				 temp.setPassword(password);
				 temp.setProfessor(professor);
				 temp.setSchool(school);
				 temp.setUseStatus(useStatus);
				 temp.setDeleteStatus(deleteStatus);
				 userList.add(temp);
			 }
		
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, st, rs);
		}
		

     return userList;
	}
	
	
	@Override
	public User getUserById(int userId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user=null;
		String sql = "SELECT USER_ID,USER_NAME,PASSWORD,USE_STATUS,DELETE_STATUS,PROFESSOR,ROLE_ID,BALANCE,SCHOOL,INTRO,PRE_EXPENSE,REAL_NAME FROM T_USER WHERE USER_ID= ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if(rs.next()) {
				user=new User();
				user.setUserId(rs.getInt("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setPassword(rs.getString("PASSWORD"));
                user.setUseStatus(rs.getInt("USE_STATUS"));
                user.setDeleteStatus(rs.getInt("DELETE_STATUS"));
                user.setProfessor(rs.getString("PROFESSOR"));
                user.setRoleId(rs.getInt("ROLE_ID"));
                user.setBalance(rs.getInt("BALANCE"));
                user.setSchool(rs.getString("SCHOOL"));
                user.setIntro(rs.getString("INTRO"));
                user.setPreExpense(rs.getInt("PRE_EXPENSE"));
                user.setRealName(rs.getString("REAL_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return user;
	}
	
	@Override
	public int updateUseStatus(User user) {
		int count=0;
		String sql="UPDATE T_USER SET USE_STATUS=? WHERE USER_ID=? ";
		Object [] params= {user.getUseStatus(),user.getUserId()};
		try {
			count=runner.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	

	@Override
	public int updateDeleteStatus(User user) {
		int count=0;
		String sql="UPDATE T_USER SET DELETE_STATUS=? WHERE USER_ID=? ";
		Object [] params= {user.getDeleteStatus(),user.getUserId()};
		try {
			count=runner.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int resetPassword(User user) {
		int count=0;
		String sql="UPDATE T_USER SET PASSWORD=? WHERE USER_ID=? ";
		Object [] params= {user.getPassword(),user.getUserId()};
		try {
			count=runner.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public int updateUser(User user) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

       String sql = "UPDATE T_USER SET USER_NAME=?,PASSWORD=?,REAL_NAME=?,SEX=?,BIRTHDAY=TO_DATE(?,'YYYY-MM-DD'),ROLE_ID=?"
		+ " WHERE USER_ID=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
//			ps.setString(3, user.getRealName());
//			ps.setInt(4, user.getSex());
//			ps.setString(5, user.getBirthday());
			ps.setInt(6, user.getRoleId());
			ps.setInt(7,user.getUserId());
			return ps.executeUpdate();
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.closeConn(conn, ps, rs);
		}

		return 0;
	}

	


	@Override
	public int deleteUser(int userId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "DELETE FROM T_USER WHERE USER_ID=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			return ps.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.closeConn(conn, ps, rs);
		}

		return 0;
	}

	@Override
	public int addUser( User user) throws Exception {
		int result=0;
		String sql="INSERT INTO T_USER(USER_ID,USER_NAME,PASSWORD,REAL_NAME,SEX,BIRTHDAY,ROLE_ID) VALUES(SEQ_T_USER.NEXTVAL,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?)";
		//Object[] params= {user.getUserName(),user.getPassword(),user.getRealName(),user.getSex(),user.getBirthday(),user.getRoleId()};
		//result=runner.update(sql,params);
		return result;
//    	Connection conn=DBUtil.getConnection();
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		String sql = "INSERT INTO T_USER(USER_ID,USER_NAME,PASSWORD,REAL_NAME,SEX,BIRTHDAY,ROLE_ID) VALUES(SEQ_T_USER.NEXTVAL,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?)";
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, user.getUserName());
//			ps.setString(2, user.getPassword());
//			ps.setString(3, user.getRealName());
//			ps.setInt(4, user.getSex());
//			ps.setString(5, user.getBirthday());
//			ps.setInt(6, user.getRoleId());
//			return ps.executeUpdate();
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		} finally {
//			DBUtil.closeConn(conn, ps, rs);
//		}
//
//		return 0;
	}

		

	@Override
	public List<User> queryUsers(User user) {
		Statement st=null;
		ResultSet rs = null;
		Connection conn = null;
		List<User> userList = new ArrayList<User>();
		String sql = "SELECT A.ROLE_ID, USER_ID, USER_NAME, PASSWORD, REAL_NAME, SEX, BIRTHDAY, B.ROLE_NAME "
				+ " FROM T_USER A, T_ROLE B WHERE A.ROLE_ID=B.ROLE_ID ";
		try {
			conn = DBUtil.getConnection();
			st= conn.createStatement();
			// 这是动态拼接查询条件
			if (user!=null&&user.getUserName() != null && !user.getUserName().equals("")) {
				sql += "AND USER_NAME LIKE  '%"+user.getUserName()+"%'";
			}
			// 有多个条件+多个条件的判断
			if (user!=null &&user.getRoleId()!=0) {
				sql+="AND A.ROLE_ID="+user.getRoleId();
			}

			rs = st.executeQuery(sql);
			while (rs.next()) {
				int userId = rs.getInt("USER_ID");
				String userName = rs.getString("USER_NAME");
				String password = rs.getString("PASSWORD");
				String realName = rs.getString("REAL_NAME");	
				int sex = rs.getInt("SEX");
				String birthDay = rs.getString("BIRTHDAY");
				int roleId=rs.getInt("ROLE_ID");
				//User temp = new User(userId,userName,password,roleId, roleId, realName,sex,roleId, birthDay,birthDay, roleId);
				//userList.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(conn, st, rs);
		}
		return userList;
	}
	
	
	@Override
	public int createUserId() {

		return 0;
	}

}
