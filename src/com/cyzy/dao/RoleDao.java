package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.Role;

public interface RoleDao {//角色表的增删改查
	
	public int createRoleId();
	
	//没用上
	public int addRole(Role role);
	//删除
	public int deleteRole(int roleId);
	
	
	//查询
	public List<Role> queryRole(Role role);
	
	//通过角色ID来获取单个角色的信息
	public Role getRoleById(int roleId);
	//修改
	public int updateRole(Role role);
 
}
