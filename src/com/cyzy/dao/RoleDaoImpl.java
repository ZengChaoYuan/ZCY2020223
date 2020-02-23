package com.cyzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cyzy.bean.Role;

import com.cyzy.util.DBUtil;

public class RoleDaoImpl implements RoleDao {

	@Override
	public int addRole(Role role) {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="INSERT INTO T_ROLE(ROLE_ID,ROLE_NAME) VALUES(SEQ_T_MENU.NEXTVAL,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, role.getRoleName());
			return ps.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		
		return 0;
	}

	@Override
	public int deleteRole(int roleId) {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="DELETE FROM T_ROLE WHERE ROLE_ID=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, roleId);	
			return ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		
		return 0;
	}

	@Override
	public int updateRole(Role role) {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;

		 String sql = "UPDATE T_ROLE SET ROLE_NAME=? WHERE ROLE_ID=? ";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, role.getRoleName());
			ps.setInt(2, role.getRoleId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		
		return 0;
	}

	@Override
	public List<Role> queryRole(Role role) {
		List<Role> roles=new ArrayList<Role>();
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="SELECT ROLE_ID,ROLE_NAME FROM T_ROLE WHERE 1=1";
			try {
				//这是动态拼接查询条件
				if(role.getRoleName()!=null && role.getRoleName().equals("")) {
					sql+= "AND ROLE_NAME LIKE ?";
				}
				ps=conn.prepareStatement(sql);
				//有多个条件+多个条件的判断
				if(role.getRoleName()!=null && role.getRoleName().equals("")) {
					ps.setString(1, "%"+role.getRoleName()+"%");
				}
				
				rs=ps.executeQuery();
				while(rs.next()) {
					int roleId=rs.getInt("ROLE_ID");
					String roleName=rs.getString("ROLE_NAME");
					Role temp=new Role();
					temp.setRoleId(roleId);
                    temp.setRoleName(roleName);
					roles.add(temp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBUtil.closeConn(conn, ps, rs);
			}
		return roles;
	}

}
