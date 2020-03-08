package com.cyzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cyzy.bean.Menu;
import com.cyzy.util.DBUtil;

public class MenuDaoImpl implements MenuDao {

	@Override
	public List<Menu> queryMenuByRoleId(int roleId) {
		List<Menu> menuList=new ArrayList<Menu>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="SELECT MENU_ID,MENU_NAME,MENU_PID,MENU_URL FROM T_MENU WHERE MENU_ID IN "
				+ "( SELECT MENU_ID FROM T_ROLE_MENU WHERE ROLE_ID IN "
				+ " ( SELECT ROLE_ID FROM T_ROLE WHERE ROLE_ID= ? ) )";
		
		try {
			conn = DBUtil.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, roleId);
			rs=ps.executeQuery();
			while(rs.next()) {
				int menuId=rs.getInt("MENU_ID");
				String menuName=rs.getString("MENU_NAME");
				int menuPId=rs.getInt("MENU_PID");
				String menuUrl=rs.getString("MENU_URL");
				Menu menu=new Menu(menuId,menuName,menuPId,menuUrl, false);
				menuList.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return menuList;
	}
	
	
	@Override
	public List<Menu> queryMenuByuserName(String userName) {
		List<Menu> menuList=new ArrayList<Menu>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="SELECT MENU_ID,MENU_NAME,MENU_PID,MENU_URL FROM T_MENU WHERE MENU_ID IN "
				+ "(SELECT MENU_ID FROM T_ROLE_MENU WHERE ROLE_ID ="
				+ "(SELECT ROLE_ID FROM T_USER WHERE USER_NAME=?))";
				
		try {
			conn = DBUtil.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, userName);
			rs=ps.executeQuery();
			while(rs.next()) {
				int menuId=rs.getInt("MENU_ID");
				String menuName=rs.getString("MENU_NAME");
				int menuPId=rs.getInt("MENU_PID");
				String menuUrl=rs.getString("MENU_URL");
				Menu menu=new Menu(menuId,menuName,menuPId,menuUrl, false);
				menuList.add(menu);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return menuList;
	}

	
	@Override
	public List<Menu> queryAllMenu() {
		Statement st=null;
		ResultSet rs = null;
		Connection conn = null;
		List<Menu> menuList=new ArrayList<Menu>();
		String sql="SELECT MENU_ID,MENU_NAME,MENU_PID,MENU_URL FROM T_MENU ";
		
		try {
			conn = DBUtil.getConnection();
			st= conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int menuId=rs.getInt("MENU_ID");
				String menuName=rs.getString("MENU_NAME");
				int menuPId=rs.getInt("MENU_PID");
				String menuUrl=rs.getString("MENU_URL");
				Menu temp=new Menu(menuId,menuName,menuPId,menuUrl, false);
				menuList.add(temp);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, st, rs);
		}
		return menuList;
	}

}
