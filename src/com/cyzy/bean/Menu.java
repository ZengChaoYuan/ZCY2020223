package com.cyzy.bean;

public class Menu {
	private int menuId;
	private String menuName;
	private int menuPId;
	private String menuUrl;

	public Menu(int menuId, String menuName, int menuPId, String menuUrl) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuPId = menuPId;
		this.menuUrl = menuUrl;
	}

	public Menu() {
		super();
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getMenuPId() {
		return menuPId;
	}

	public void setMenuPId(int menuPId) {
		this.menuPId = menuPId;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

}
