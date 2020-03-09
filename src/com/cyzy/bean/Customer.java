package com.cyzy.bean;

public class Customer {
	private int customerId;
	private String customerName;
	private String password;
	private int sex;
	private String tel;
	private int age;
	private String address;
	private int useStatus;
	private int deleteStatus;
	private int balance;
	private String registerTime;

	public Customer(int customerId, String customerName, String password, int sex, String tel, int age, String address,
			int useStatus, int deleteStatus, int balance, String registerTime) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.password = password;
		this.sex = sex;
		this.tel = tel;
		this.age = age;
		this.address = address;
		this.useStatus = useStatus;
		this.deleteStatus = deleteStatus;
		this.balance = balance;
		this.registerTime = registerTime;
	}

	public Customer() {
		super();
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(int useStatus) {
		this.useStatus = useStatus;
	}

	public int getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(int deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

}
