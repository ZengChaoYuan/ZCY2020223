package com.cyzy.bean;

import java.util.ArrayList;
import java.util.List;

public class Role {

	private int roleId;
	private String roleName;
	private List<Menu> menuList=new ArrayList<Menu>();//此角色已经拥有的菜单
		
	public Role(int roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}
	
	public Role() {
		super();
	}	

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
