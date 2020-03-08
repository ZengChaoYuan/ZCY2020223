package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Menu;

public interface MenuService {
    //靠用户名来查询角色所拥有的菜单
	public List<Menu> queryMenuByuserName(String userName);
	
	//查询菜单列表
	public List<Menu> queryAllMenu();
	
	//靠角色id来查询角色所拥有的菜单
	public List<Menu> queryMenuByRoleId(int roleId);
	
}
