package com.cyzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cyzy.bean.User;
import com.cyzy.util.DBUtil;

public class UserDaoImpl implements UserDao {
	
	
	//select a1.* from (select t.*,rownum rn from t_user t where rownum <=5 and role_id=2) a1 
	//where rn >=1;
	@Override
	public List<User> queryUsers(User user, int startIndex, int endIndex) {
		Statement st=null;
		ResultSet rs = null;
		Connection conn = null;
		List<User> userList = new ArrayList<User>();
		String sql="SELECT ROLE_ID, USER_ID, USER_NAME, PASSWORD, REAL_NAME, SEX, BIRTHDAY, ROLE_NAME, RN FROM ( ";
		sql+= " SELECT A.ROLE_ID, USER_ID, USER_NAME, PASSWORD, REAL_NAME, SEX, BIRTHDAY, B.ROLE_NAME, ROWNUM RN ";
		sql+=" FROM T_USER A, T_ROLE B WHERE A.ROLE_ID = B.ROLE_ID AND ROWNUM<= "+endIndex;
		try {
			conn = DBUtil.getConnection();
			st= conn.createStatement();
			// 这是动态拼接查询条件
			if (user!=null&&user.getUserName() != null && !user.getUserName().equals("")) {
				sql += " AND USER_NAME LIKE  '%"+user.getUserName()+"%'";
			}
			// 有多个条件+多个条件的判断
			if (user!=null &&user.getRoleId()!=0) {
				sql+=" AND A.ROLE_ID= "+user.getRoleId();
			}
			sql+=" )A1 WHERE RN > = "+startIndex;

			rs = st.executeQuery(sql);
			while (rs.next()) {
				int userId = rs.getInt("USER_ID");
				String userName = rs.getString("USER_NAME");
				String password = rs.getString("PASSWORD");
				String realName = rs.getString("REAL_NAME");	
				int sex = rs.getInt("SEX");
				String birthDay = rs.getString("BIRTHDAY");
				int roleId=rs.getInt("ROLE_ID");
				User temp = new User(userId,userName,password,realName,sex,birthDay,roleId);
				temp.setUserId(userId);
				temp.setUserName(userName);
				temp.setPassword(password);
				temp.setRealName(realName);
				temp.setSex(sex);
				temp.setBirthday(birthDay);
				userList.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(conn, st, rs);
		}
		return userList;
	}
	
	@Override
	public int queryCount(User user) {
		Statement st=null;
		ResultSet rs = null;
		Connection conn = null;
		String sql="SELECT COUNT(0) FROM T_USER WHERE 1=1 ";
		int count=0;
		
		try {
			conn=DBUtil.getConnection();
			st=conn.createStatement();
			// 这是动态拼接查询条件
			if (user!=null&&user.getUserName() != null && !user.getUserName().equals("")) {
				sql += " AND USER_NAME LIKE  '%"+user.getUserName()+"%'";
			}
			// 有多个条件+多个条件的判断
			if (user!=null &&user.getRoleId()!=0) {
				sql+=" AND ROLE_ID="+user.getRoleId();
			}
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
	public User login(String userName, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	    User user=null;
		String sql="SELECT USER_ID,USER_NAME,PASSWORD,REAL_NAME,SEX,BIRTHDAY,ROLE_ID FROM T_USER "
				+ "WHERE USER_NAME=? AND PASSWORD=?";
		
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
			    String realName=rs.getString("REAL_NAME");
			    int sex=rs.getInt("SEX");
			    String birthday=rs.getString("BIRTHDAY");
			    int roleId=rs.getInt("ROLE_ID");
			    user=new User(userId,username,passWord,realName,sex,birthday,roleId);
			   
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return user;
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
			ps.setString(3, user.getRealName());
			ps.setInt(4, user.getSex());
			ps.setString(5, user.getBirthday());
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
	public User updateUser(int userId) {
		Connection conn =null;
		PreparedStatement ps = null;	
		User user=null; 
		try {
			 conn=DBUtil.getConnection();
			 String sql = "UPDATE T_USER SET USER_NAME=?,PASSWORD,REAL_NAME=?,SEX=?,BIRTHDAY=TO_DATE(?,'YYYY-MM-DD'),ROLE_ID=? WHERE USER_ID=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getRealName());
			ps.setInt(4, user.getSex());
			ps.setString(5, user.getBirthday());
			ps.setInt(6, user.getRoleId());
			ps.setInt(7, user.getUserId());
			int result=ps.executeUpdate();
			if(result>0) {
				System.out.println("更新成功");
			}else {
				System.out.println("更新失败");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.closeConn(conn, ps, null);
		}

		return user;
	}
	@Override
	public User getUserById(int userId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user=null;
		String sql = "SELECT USER_ID,USER_NAME,PASSWORD,REAL_NAME,SEX,BIRTHDAY,ROLE_ID FROM T_USER WHERE USER_ID=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if(rs.next()) {
				user=new User();
				user.setUserId(rs.getInt("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setRealName(rs.getString("REAL_NAME"));
				user.setSex(rs.getInt("SEX"));
				String birthday=(rs.getString("BIRTHDAY")).substring(0,10);
				user.setBirthday(birthday);
				user.setRoleId(rs.getInt("ROLE_ID"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return user;
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
	public int addUser( User user) {
    	Connection conn=DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "INSERT INTO T_USER(USER_ID,USER_NAME,PASSWORD,REAL_NAME,SEX,BIRTHDAY,ROLE_ID) VALUES(SEQ_T_USER.NEXTVAL,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getRealName());
			ps.setInt(4, user.getSex());
			ps.setString(5, user.getBirthday());
			ps.setInt(6, user.getRoleId());
			return ps.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.closeConn(conn, ps, rs);
		}

		return 0;
	}

	@Override
	public User loginUser(User user) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User users = null;
		String sql = "SELECT * FROM T_USER WHERE USER_NAME=? AND PASSWORD=?";
		try {

			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();
			while (rs.next()) {
//				users = new User();
//				users.setUserId(rs.getInt("USER_ID"));
//				users.setUserName(rs.getString("USER_NAME"));
//				users.setPassword(rs.getString("PASSWORD"));
//				users.setRealName(rs.getString("REAL_NAME"));
//				users.setSex(rs.getInt("SEX"));
//				users.setBirthday(rs.getString("BIRTHDAY"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return users;
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
				User temp = new User(userId,userName,password,realName,sex,birthDay,roleId);
				temp.setUserId(userId);
				temp.setUserName(userName);
				temp.setPassword(password);
				temp.setRealName(realName);
				temp.setSex(sex);
				temp.setBirthday(birthDay);
				userList.add(temp);
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
