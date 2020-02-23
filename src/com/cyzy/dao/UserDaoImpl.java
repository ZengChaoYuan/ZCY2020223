package com.cyzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cyzy.bean.User;
import com.cyzy.util.DBUtil;

public class UserDaoImpl implements UserDao {

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
		List<User> users = new ArrayList<User>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT USER_ID,USER_NAME,PASSWORD,REAL_NAME,SEX,BIRTHDAY FROM T_USER WHERE 1=1";
		try {
			// 这是动态拼接查询条件
			if (user.getRealName() != null && user.getRealName().equals("")) {
				sql += "AND REAL_NAME LIKE ?";
			}
			ps = conn.prepareStatement(sql);
			// 有多个条件+多个条件的判断
			if (user.getRealName() != null && user.getRealName().equals("")) {
				ps.setString(1, "%" + user.getRealName() + "%");
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				int userId = rs.getInt("USER_ID");
				String userName = rs.getString("USER_NAME");
				String password = rs.getString("PASSWORD");
				String realName = rs.getString("REAL_NAME");	
				int sex = rs.getInt("SEX");
				String birthDay = rs.getString("BIRTHDAY");
				User temp = new User();
				temp.setUserId(userId);
				temp.setUserName(userName);
				temp.setPassword(password);
				temp.setRealName(realName);
				temp.setSex(sex);
				temp.setBirthday(birthDay);
				users.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return users;
	}

	@Override
	public int createUserId() {

		return 0;
	}
	
	

	
}
