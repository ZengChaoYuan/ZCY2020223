package com.cyzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.cyzy.bean.RoleMenu;
import com.cyzy.util.DBUtil;
import com.cyzy.util.JDBCUtil;

public class RoleMenuDaoImpl implements RoleMenuDao {
	private static QueryRunner runner=new QueryRunner(JDBCUtil.getDataSource());
	
	@Override
	public int addRoleMenu(int roleId, int[] menuIds) throws Exception {
		//增加角色菜单中间表
		String sql="INSERT INTO T_ROLE_MENU(ROLE_ID,MENU_ID) VALUES(?,?)";
		Object[][]params=new Object[menuIds.length][];
		for(int i=0;i<menuIds.length;i++) {
			params[i]=new Object[2];
			params[i][0]=roleId;
			params[i][1]=Integer.valueOf(menuIds[i]);
		}
		return runner.batch(JDBCUtil.getConnection(), sql, params).length;
	}
	
	@Override
	public int deleteRoleMenu(int roleId) {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps = null;
		String sql="DELETE FROM T_ROLE_MENU WHERE ROLE_ID= ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, roleId);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, null);
		}	
		return 0;
	}	

	@Override
	public int addRoleMenu(RoleMenu roleMenu) {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps = null;
		String sql="INSERT INTO T_ROLE_MENU(ROLE_ID,MENU_ID) VALUES(?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, roleMenu.getRoleId());
			ps.setInt(2, roleMenu.getMenuId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, null);
		}
		return 0;
	}

	

	@Override
	public int addRoleMenu(int roleId) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	

}
