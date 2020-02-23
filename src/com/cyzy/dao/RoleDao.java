package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.Role;

public interface RoleDao {//角色表的增删改查
	//增加
	public int addRole(Role role);
	//删除
	public int deleteRole(int roleId);
	//修改
	public int updateRole(Role role);
	
	//查询
	public List<Role> queryRole(Role role);
 
}
