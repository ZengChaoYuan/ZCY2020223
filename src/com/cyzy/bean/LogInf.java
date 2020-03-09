package com.cyzy.bean;

public class LogInf {//≤È—Ø”√ªß
	private String adminName;
	private int count;

	public LogInf(String adminName, int count) {
		super();
		this.adminName = adminName;
		this.count = count;
	}

	public LogInf() {
		super();
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
