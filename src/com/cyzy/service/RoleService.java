package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Role;

public interface RoleService {

	//这步是用来做权限管理
	public Role getRoleById(int roleId);
	
	
	//查询
	public List<Role> queryRole(Role role);
	
	//增加角色,增加角色名称和菜单数组
	public int addRole(String roleName,String[] menuArr);	
	
	//增加
	public int addRole(Role role);
	
	//修改
	public int updateRole(Role role);
	
	//删除
	public int deleteRole(int roleId);
		
	
	
	
	
    

}
