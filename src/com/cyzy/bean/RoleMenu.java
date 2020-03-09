package com.cyzy.bean;

public class RoleMenu {
	private int roleId;
	private int menuId;

	public RoleMenu(int roleId, int menuId) {
		super();
		this.roleId = roleId;
		this.menuId = menuId;
	}

	public RoleMenu() {
		super();
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

}
