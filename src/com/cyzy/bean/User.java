package com.cyzy.bean;

public class User {// 后台用户表
	private int userId;
	private String userName;
	private String password;
	private int useStatus;
	private int deleteStatus;
	private String professor;
	private int roleId;// 一个用户对应一个角色
	private int balance;
	private String school;
	private String intro;
	private int preExpense;
	private String realName;

	public User(int userId, String userName, String password, int useStatus, int deleteStatus, String professor,
			int roleId, int balance, String school, String intro, int preExpense, String realName) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.useStatus = useStatus;
		this.deleteStatus = deleteStatus;
		this.professor = professor;
		this.roleId = roleId;
		this.balance = balance;
		this.school = school;
		this.intro = intro;
		this.preExpense = preExpense;
		this.realName = realName;
	}

	public User() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getPreExpense() {
		return preExpense;
	}

	public void setPreExpense(int preExpense) {
		this.preExpense = preExpense;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

}
