package com.cyzy.bean;

public class PreOrderCount {//咨询师预约数量表
	private int countId;
	private String countEven;
	private String userName;
	private String countTime;
	
	public PreOrderCount(int countId, String countEven, String userName, String countTime) {
		super();
		this.countId = countId;
		this.countEven = countEven;
		this.userName = userName;
		this.countTime = countTime;
	}

	public PreOrderCount() {
		super();
	}

	public int getCountId() {
		return countId;
	}

	public void setCountId(int countId) {
		this.countId = countId;
	}

	public String getCountEven() {
		return countEven;
	}

	public void setCountEven(String countEven) {
		this.countEven = countEven;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCountTime() {
		return countTime;
	}

	public void setCountTime(String countTime) {
		this.countTime = countTime;
	}
	
}
