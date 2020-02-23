package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Role;

public interface RoleService {

	//增加
	public int addRole(Role role);
	
	//修改
	public int updateRole(Role role);
	
	//根据ID来获取一个role对象
	public Role getRoleById(int userId);
	
	//删除
	public int deleteUser(int userId);
	
	//查询
	public List<Role> queryRole(Role role);
}
