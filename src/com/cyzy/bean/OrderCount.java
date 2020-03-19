package com.cyzy.bean;

public class OrderCount {//查询咨询师预约量
	private String userName;
	private int count;
	
	public OrderCount(String userName, int count) {
		super();
		this.userName = userName;
		this.count = count;
	}

	public OrderCount() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
