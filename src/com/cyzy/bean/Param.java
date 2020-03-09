package com.cyzy.bean;

public class Param {
	private int paramId;
	private String resetPassword;

	public Param(int paramId, String resetPassword) {
		super();
		this.paramId = paramId;
		this.resetPassword = resetPassword;
	}

	public Param() {
		super();
	}

	public int getParamId() {
		return paramId;
	}

	public void setParamId(int paramId) {
		this.paramId = paramId;
	}

	public String getResetPassword() {
		return resetPassword;
	}

	public void setResetPassword(String resetPassword) {
		this.resetPassword = resetPassword;
	}

}
