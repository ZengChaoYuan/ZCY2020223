package com.cyzy.bean;

public class MyAccount {
	private int myaccountId;
	private String happenTime;
	private String happenThing;
	private int customerId;
	private String consumpType;
	private int consumpMoney;
	private int userId;

	

	public MyAccount(int myaccountId, String happenTime, String happenThing, int customerId, String consumpType,
			int consumpMoney, int userId) {
		super();
		this.myaccountId = myaccountId;
		this.happenTime = happenTime;
		this.happenThing = happenThing;
		this.customerId = customerId;
		this.consumpType = consumpType;
		this.consumpMoney = consumpMoney;
		this.userId = userId;
	}

	public MyAccount() {
		super();
	}

	public int getMyaccountId() {
		return myaccountId;
	}

	public void setMyaccountId(int myaccountId) {
		this.myaccountId = myaccountId;
	}

	public String getHappenTime() {
		return happenTime;
	}

	public void setHappenTime(String happenTime) {
		this.happenTime = happenTime;
	}

	public String getHappenThing() {
		return happenThing;
	}

	public void setHappenThing(String happenThing) {
		this.happenThing = happenThing;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getConsumpType() {
		return consumpType;
	}

	public void setConsumpType(String consumpType) {
		this.consumpType = consumpType;
	}

	public int getConsumpMoney() {
		return consumpMoney;
	}

	public void setConsumpMoney(int consumpMoney) {
		this.consumpMoney = consumpMoney;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}	
}
