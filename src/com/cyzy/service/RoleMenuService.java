package com.cyzy.service;

import com.cyzy.bean.RoleMenu;

public interface RoleMenuService {
	//删除角色菜单中间表
	public int deleteRoleMenu(int roleId);
	
	//增加角色菜单中间表
	public int addRoleMenu(int roleId, int[] menuIds) throws Exception;
	
	//增加
	public int addRoleMenu(RoleMenu roleMenu);
}
